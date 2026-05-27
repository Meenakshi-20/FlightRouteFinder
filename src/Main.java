// ============================================================
//  Main.java  —  Flight Route Finder
//  Run this file. It starts a web server on port 8080
//  and opens your browser automatically.
//  Pure Java — no extra libraries needed.
// ============================================================
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static FlightGraph graph = new FlightGraph();

    public static void main(String[] args) throws Exception {
        DataLoader.load(graph);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/",       new PageHandler());
        server.createContext("/search", new SearchHandler());
        server.createContext("/airports", new AirportHandler());
        server.start();

        System.out.println("====================================");
        System.out.println("  Flight Route Finder is running!");
        System.out.println("  Open: http://localhost:8080");
        System.out.println("  Press Ctrl+C to stop.");
        System.out.println("====================================");

        // Auto-open browser
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win"))       Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://localhost:8080");
            else if (os.contains("mac"))  Runtime.getRuntime().exec("open http://localhost:8080");
            else                          Runtime.getRuntime().exec("xdg-open http://localhost:8080");
        } catch (Exception ignored) {}
    }

    // ── Serve the main HTML page ──────────────────────────────
    static class PageHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            String html = buildHTML();
            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            ex.sendResponseHeaders(200, bytes.length);
            ex.getResponseBody().write(bytes);
            ex.getResponseBody().close();
        }
    }

    // ── Handle flight search (AJAX call from browser) ─────────
    static class SearchHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            Map<String,String> params = parseQuery(ex.getRequestURI().getQuery());

            String from     = params.getOrDefault("from", "").toUpperCase();
            String to       = params.getOrDefault("to",   "").toUpperCase();
            int    maxStops = Integer.parseInt(params.getOrDefault("stops", "2"));
            int    maxPrice = Integer.parseInt(params.getOrDefault("price", "9999999"));
            String airline  = params.getOrDefault("airline", "").toLowerCase();

            String json;
            if (graph.getAirport(from) == null || graph.getAirport(to) == null || from.equals(to)) {
                json = "{\"error\":\"Invalid airport codes.\"}";
            } else {
                List<RouteResult> routes = graph.findAllRoutes(from, to, maxStops);

                // Apply filters
                if (maxPrice < 9999999)
                    routes = routes.stream().filter(r -> r.totalPrice <= maxPrice).collect(Collectors.toList());
                if (!airline.isEmpty())
                    routes = routes.stream()
                        .filter(r -> r.flights.stream().anyMatch(f -> f.airline.toLowerCase().contains(airline)))
                        .collect(Collectors.toList());

                json = routesToJson(routes);
            }

            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            ex.sendResponseHeaders(200, bytes.length);
            ex.getResponseBody().write(bytes);
            ex.getResponseBody().close();
        }
    }

    // ── Return all airport codes as JSON ──────────────────────
    static class AirportHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            StringBuilder sb = new StringBuilder("[");
            graph.getAllAirports().stream()
                .sorted(Comparator.comparing(a -> a.code))
                .forEach(a -> sb.append(String.format(
                    "{\"code\":\"%s\",\"city\":\"%s\",\"country\":\"%s\",\"type\":\"%s\"},",
                    a.code, a.city, a.country, a.type)));
            if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
            sb.append("]");

            byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, bytes.length);
            ex.getResponseBody().write(bytes);
            ex.getResponseBody().close();
        }
    }

    // ── Build routes JSON ─────────────────────────────────────
    static String routesToJson(List<RouteResult> routes) {
        if (routes.isEmpty()) return "{\"routes\":[],\"count\":0}";

        StringBuilder sb = new StringBuilder("{\"count\":" + routes.size() + ",\"routes\":[");
        int limit = Math.min(routes.size(), 10);
        for (int i = 0; i < limit; i++) {
            RouteResult r = routes.get(i);
            sb.append("{");
            sb.append("\"stops\":").append(r.stops).append(",");
            sb.append("\"totalPrice\":").append(r.totalPrice).append(",");
            sb.append("\"totalDuration\":\"").append(Flight.formatMins(r.totalDuration)).append("\",");
            sb.append("\"legs\":[");
            for (int j = 0; j < r.flights.size(); j++) {
                Flight f = r.flights.get(j);
                sb.append("{");
                sb.append("\"fn\":\"").append(f.flightNumber).append("\",");
                sb.append("\"airline\":\"").append(f.airline).append("\",");
                sb.append("\"from\":\"").append(f.from.code).append("\",");
                sb.append("\"fromCity\":\"").append(f.from.city).append("\",");
                sb.append("\"to\":\"").append(f.to.code).append("\",");
                sb.append("\"toCity\":\"").append(f.to.city).append("\",");
                sb.append("\"dep\":\"").append(f.departureTime).append("\",");
                sb.append("\"arr\":\"").append(f.arrivalTime).append("\",");
                sb.append("\"dur\":\"").append(Flight.formatMins(f.durationMins)).append("\",");
                sb.append("\"price\":").append(f.price).append(",");
                sb.append("\"class\":\"").append(f.seatClass).append("\",");
                sb.append("\"seats\":").append(f.seatsAvailable).append(",");
                sb.append("\"type\":\"").append(f.type).append("\"");
                sb.append("}").append(j < r.flights.size()-1 ? "," : "");
            }
            sb.append("]}").append(i < limit-1 ? "," : "");
        }
        sb.append("]}");
        return sb.toString();
    }

    // ── Parse URL query string ────────────────────────────────
    static Map<String,String> parseQuery(String query) {
        Map<String,String> map = new HashMap<>();
        if (query == null) return map;
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=");
            if (kv.length == 2)
                try { map.put(kv[0], URLDecoder.decode(kv[1], "UTF-8")); }
                catch (Exception ignored) {}
        }
        return map;
    }

    // ── Build the HTML page ───────────────────────────────────
    static String buildHTML() {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Flight Route Finder</title>
<style>
  * { box-sizing: border-box; margin: 0; padding: 0; }

  body {
    font-family: 'Segoe UI', Arial, sans-serif;
    background: #f0f4f8;
    color: #1a1a2e;
  }

  /* ── Header ── */
  header {
    background: linear-gradient(135deg, #1a1a2e 0%, #16213e 60%, #0f3460 100%);
    color: white;
    padding: 20px 40px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-shadow: 0 2px 12px rgba(0,0,0,0.3);
  }
  header h1 { font-size: 1.6rem; font-weight: 700; letter-spacing: 1px; }
  header p  { font-size: 0.8rem; color: #a0b4cc; margin-top: 3px; }
  .badge {
    background: #e94560;
    color: white;
    padding: 4px 12px;
    border-radius: 20px;
    font-size: 0.75rem;
    font-weight: 600;
  }

  /* ── Search Card ── */
  .search-card {
    max-width: 900px;
    margin: 30px auto;
    background: white;
    border-radius: 12px;
    padding: 30px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  }
  .search-card h2 {
    font-size: 1rem;
    color: #555;
    text-transform: uppercase;
    letter-spacing: 1px;
    margin-bottom: 20px;
    border-left: 4px solid #e94560;
    padding-left: 10px;
  }

  .form-row {
    display: flex;
    gap: 15px;
    flex-wrap: wrap;
    margin-bottom: 15px;
  }
  .form-group {
    flex: 1;
    min-width: 140px;
    display: flex;
    flex-direction: column;
    gap: 6px;
  }
  label {
    font-size: 0.75rem;
    font-weight: 600;
    color: #888;
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }
  input, select {
    border: 1.5px solid #ddd;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 0.95rem;
    transition: border-color 0.2s;
    background: #fafafa;
    width: 100%;
  }
  input:focus, select:focus {
    outline: none;
    border-color: #0f3460;
    background: white;
  }

  .btn-search {
    background: linear-gradient(135deg, #e94560, #c62a47);
    color: white;
    border: none;
    padding: 12px 36px;
    border-radius: 8px;
    font-size: 1rem;
    font-weight: 700;
    cursor: pointer;
    letter-spacing: 0.5px;
    transition: transform 0.1s, box-shadow 0.2s;
  }
  .btn-search:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 14px rgba(233,69,96,0.4);
  }
  .btn-search:active { transform: translateY(0); }

  /* ── Tabs ── */
  .tabs {
    max-width: 900px;
    margin: 0 auto 15px;
    display: flex;
    gap: 8px;
  }
  .tab {
    padding: 8px 20px;
    border-radius: 20px;
    border: 2px solid #ddd;
    background: white;
    cursor: pointer;
    font-size: 0.85rem;
    font-weight: 600;
    color: #666;
    transition: all 0.2s;
  }
  .tab.active {
    border-color: #0f3460;
    background: #0f3460;
    color: white;
  }

  /* ── Results ── */
  #results, #airports-panel {
    max-width: 900px;
    margin: 0 auto 40px;
  }

  .status-msg {
    text-align: center;
    padding: 30px;
    color: #888;
    font-size: 0.95rem;
  }

  .result-count {
    font-size: 0.85rem;
    color: #666;
    margin-bottom: 12px;
    padding: 8px 15px;
    background: #e8f0fe;
    border-radius: 6px;
    border-left: 3px solid #0f3460;
  }

  /* ── Route Card ── */
  .route-card {
    background: white;
    border-radius: 12px;
    margin-bottom: 16px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.06);
    overflow: hidden;
    border: 1.5px solid #eee;
    transition: box-shadow 0.2s;
  }
  .route-card:hover { box-shadow: 0 4px 18px rgba(0,0,0,0.12); }

  .route-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 14px 20px;
    background: #f8f9fc;
    border-bottom: 1px solid #eee;
  }
  .route-header .price {
    font-size: 1.4rem;
    font-weight: 800;
    color: #e94560;
  }
  .route-header .price span {
    font-size: 0.8rem;
    color: #888;
    font-weight: 400;
  }
  .route-header .meta { display: flex; gap: 12px; align-items: center; }

  .pill {
    padding: 3px 10px;
    border-radius: 12px;
    font-size: 0.75rem;
    font-weight: 700;
  }
  .pill-direct   { background: #d4edda; color: #155724; }
  .pill-one      { background: #fff3cd; color: #856404; }
  .pill-two      { background: #f8d7da; color: #721c24; }
  .pill-intl     { background: #cce5ff; color: #004085; }
  .pill-dom      { background: #e2d9f3; color: #4a1e80; }

  .duration { font-size: 0.85rem; color: #555; }

  /* ── Flight Leg ── */
  .legs { padding: 0 20px 15px; }
  .leg {
    display: flex;
    align-items: center;
    gap: 15px;
    padding: 12px 0;
    border-bottom: 1px dashed #eee;
  }
  .leg:last-child { border-bottom: none; }

  .leg-airline {
    width: 130px;
    font-size: 0.8rem;
    font-weight: 700;
    color: #0f3460;
  }
  .leg-airline small {
    display: block;
    font-weight: 400;
    color: #888;
    font-size: 0.72rem;
  }

  .leg-route {
    flex: 1;
    display: flex;
    align-items: center;
    gap: 10px;
    font-size: 0.88rem;
  }
  .leg-route .airport { font-weight: 700; font-size: 1rem; color: #1a1a2e; }
  .leg-route .city    { font-size: 0.75rem; color: #888; }
  .leg-route .arrow   { color: #e94560; font-size: 1.1rem; }
  .leg-route .time    { font-size: 0.8rem; color: #555; }

  .leg-price { font-weight: 700; color: #333; font-size: 0.9rem; }
  .leg-class { font-size: 0.72rem; color: #888; }

  /* ── Airport Table ── */
  .airport-table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 2px 10px rgba(0,0,0,0.06);
    font-size: 0.88rem;
  }
  .airport-table th {
    background: #0f3460;
    color: white;
    padding: 12px 16px;
    text-align: left;
    font-size: 0.78rem;
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }
  .airport-table td { padding: 10px 16px; border-bottom: 1px solid #f0f0f0; }
  .airport-table tr:hover td { background: #f8f9fc; }
  .airport-table tr:last-child td { border-bottom: none; }

  .filter-input {
    width: 100%;
    padding: 10px 14px;
    border: 1.5px solid #ddd;
    border-radius: 8px;
    margin-bottom: 14px;
    font-size: 0.9rem;
  }

  /* ── Spinner ── */
  .spinner {
    display: inline-block;
    width: 22px; height: 22px;
    border: 3px solid #ddd;
    border-top-color: #e94560;
    border-radius: 50%;
    animation: spin 0.8s linear infinite;
    vertical-align: middle;
    margin-right: 8px;
  }
  @keyframes spin { to { transform: rotate(360deg); } }

  footer {
    text-align: center;
    padding: 20px;
    color: #aaa;
    font-size: 0.78rem;
    border-top: 1px solid #eee;
  }
</style>
</head>
<body>

<header>
  <div>
    <h1>&#9992;&#65039; Flight Route Finder</h1>
    <p>Domestic &amp; International &ndash; 30 Airports &bull; 200+ Flights</p>
  </div>
  <span class="badge">Java Powered</span>
</header>

<!-- Tabs -->
<div class="tabs" style="margin-top:24px;">
  <button class="tab active" onclick="showTab('search')">Search Flights</button>
  <button class="tab" onclick="showTab('airports')">All Airports</button>
</div>

<!-- Search Panel -->
<div id="search-panel">
  <div class="search-card">
    <h2>Find Your Route</h2>
    <div class="form-row">
      <div class="form-group">
        <label>From (Airport Code)</label>
        <input id="from" type="text" placeholder="e.g. DEL" maxlength="3"
               oninput="this.value=this.value.toUpperCase()">
      </div>
      <div class="form-group">
        <label>To (Airport Code)</label>
        <input id="to" type="text" placeholder="e.g. LHR" maxlength="3"
               oninput="this.value=this.value.toUpperCase()">
      </div>
      <div class="form-group">
        <label>Max Stops</label>
        <select id="stops">
          <option value="0">Direct Only</option>
          <option value="1">Up to 1 Stop</option>
          <option value="2" selected>Up to 2 Stops</option>
        </select>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group">
        <label>Max Budget (Rs.)</label>
        <input id="price" type="number" placeholder="e.g. 50000 (blank = any)">
      </div>
      <div class="form-group">
        <label>Airline (optional)</label>
        <input id="airline" type="text" placeholder="e.g. IndiGo, Emirates">
      </div>
      <div class="form-group" style="justify-content:flex-end;">
        <button class="btn-search" onclick="searchFlights()">Search Flights</button>
      </div>
    </div>
  </div>

  <div id="results">
    <div class="status-msg">Enter airports above and click Search Flights.</div>
  </div>
</div>

<!-- Airports Panel -->
<div id="airports-panel" style="display:none;">
  <div class="search-card" style="padding-bottom:10px;">
    <h2>All Airports</h2>
    <input class="filter-input" id="airport-filter" placeholder="Filter by code, city or country..."
           oninput="filterAirports()">
    <table class="airport-table" id="airport-table">
      <thead>
        <tr>
          <th>Code</th><th>City</th><th>Country</th><th>Type</th>
        </tr>
      </thead>
      <tbody id="airport-body"></tbody>
    </table>
  </div>
</div>

<footer>Flight Route Finder &mdash; Built with Java HttpServer + HTML/CSS</footer>

<script>
  let allAirports = [];

  // ── Tab switching ──────────────────────────────────────────
  function showTab(name) {
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    event.target.classList.add('active');
    document.getElementById('search-panel').style.display   = name === 'search'   ? '' : 'none';
    document.getElementById('airports-panel').style.display = name === 'airports' ? '' : 'none';
    if (name === 'airports' && allAirports.length === 0) loadAirports();
  }

  // ── Load airports ──────────────────────────────────────────
  function loadAirports() {
    fetch('/airports')
      .then(r => r.json())
      .then(data => {
        allAirports = data;
        renderAirports(data);
      });
  }

  function renderAirports(list) {
    const tbody = document.getElementById('airport-body');
    tbody.innerHTML = list.map(a => `
      <tr>
        <td><strong>${a.code}</strong></td>
        <td>${a.city}</td>
        <td>${a.country}</td>
        <td><span class="pill ${a.type==='DOMESTIC'?'pill-dom':'pill-intl'}">${a.type}</span></td>
      </tr>`).join('');
  }

  function filterAirports() {
    const q = document.getElementById('airport-filter').value.toLowerCase();
    renderAirports(allAirports.filter(a =>
      a.code.toLowerCase().includes(q) ||
      a.city.toLowerCase().includes(q) ||
      a.country.toLowerCase().includes(q)));
  }

  // ── Search flights ─────────────────────────────────────────
  function searchFlights() {
    const from    = document.getElementById('from').value.trim();
    const to      = document.getElementById('to').value.trim();
    const stops   = document.getElementById('stops').value;
    const price   = document.getElementById('price').value || 9999999;
    const airline = document.getElementById('airline').value.trim();

    if (!from || !to) {
      showError('Please enter both From and To airport codes.');
      return;
    }

    document.getElementById('results').innerHTML =
      '<div class="status-msg"><span class="spinner"></span> Searching routes...</div>';

    const url = `/search?from=${from}&to=${to}&stops=${stops}&price=${price}&airline=${encodeURIComponent(airline)}`;

    fetch(url)
      .then(r => r.json())
      .then(data => {
        if (data.error) { showError(data.error); return; }
        renderResults(data);
      })
      .catch(() => showError('Server error. Make sure the Java server is running.'));
  }

  function showError(msg) {
    document.getElementById('results').innerHTML =
      `<div class="status-msg" style="color:#e94560;">${msg}</div>`;
  }

  // ── Render results ─────────────────────────────────────────
  function renderResults(data) {
    if (data.count === 0) {
      showError('No routes found. Try more stops or remove filters.');
      return;
    }

    let html = `<div class="result-count">Found <strong>${data.count}</strong> route(s). Showing top ${data.routes.length} sorted by lowest price.</div>`;

    data.routes.forEach((r, i) => {
      const stopPill = r.stops === 0
        ? '<span class="pill pill-direct">Direct</span>'
        : r.stops === 1
          ? '<span class="pill pill-one">1 Stop</span>'
          : '<span class="pill pill-two">2 Stops</span>';

      const firstLeg = r.legs[0];
      const lastLeg  = r.legs[r.legs.length - 1];
      const routeType = r.legs.some(l => l.type === 'INTERNATIONAL')
        ? '<span class="pill pill-intl">International</span>'
        : '<span class="pill pill-dom">Domestic</span>';

      html += `
      <div class="route-card">
        <div class="route-header">
          <div>
            <div class="price">Rs.${r.totalPrice.toLocaleString('en-IN')} <span>total</span></div>
            <div style="font-size:0.8rem;color:#888;margin-top:2px;">
              ${firstLeg.fromCity} (${firstLeg.from}) &rarr; ${lastLeg.toCity} (${lastLeg.to})
            </div>
          </div>
          <div class="meta">
            ${stopPill}
            ${routeType}
            <span class="duration">&#128336; ${r.totalDuration}</span>
          </div>
        </div>
        <div class="legs">`;

      r.legs.forEach(leg => {
        html += `
          <div class="leg">
            <div class="leg-airline">
              ${leg.fn}<br>
              <small>${leg.airline}</small>
              <small class="leg-class">${leg.class}</small>
            </div>
            <div class="leg-route">
              <div style="text-align:center">
                <div class="airport">${leg.from}</div>
                <div class="city">${leg.fromCity}</div>
                <div class="time">${leg.dep}</div>
              </div>
              <div class="arrow">&#10145;</div>
              <div style="text-align:center">
                <div class="airport">${leg.to}</div>
                <div class="city">${leg.toCity}</div>
                <div class="time">${leg.arr}</div>
              </div>
              <div style="margin-left:10px;font-size:0.78rem;color:#aaa;">${leg.dur}</div>
            </div>
            <div style="text-align:right">
              <div class="leg-price">Rs.${leg.price.toLocaleString('en-IN')}</div>
              <div style="font-size:0.72rem;color:#aaa;">${leg.seats} seats left</div>
            </div>
          </div>`;
      });

      html += `</div></div>`;
    });

    document.getElementById('results').innerHTML = html;
  }

  // Enter key triggers search
  document.addEventListener('keydown', e => {
    if (e.key === 'Enter') searchFlights();
  });
</script>
</body>
</html>
""";
    }
}

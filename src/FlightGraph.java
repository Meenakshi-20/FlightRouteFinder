import java.util.*;

public class FlightGraph {
    private Map<String, Airport>      airports  = new HashMap<>();
    private Map<String, List<Flight>> adjacency = new HashMap<>();

    public void addAirport(Airport a) {
        airports.put(a.code, a);
        adjacency.put(a.code, new ArrayList<>());
    }

    public void addFlight(Flight f) { adjacency.get(f.from.code).add(f); }
    public Airport getAirport(String code) { return airports.get(code.toUpperCase()); }
    public Collection<Airport> getAllAirports() { return airports.values(); }

    public List<RouteResult> findAllRoutes(String from, String to, int maxStops) {
        from = from.toUpperCase(); to = to.toUpperCase();
        List<RouteResult> results = new ArrayList<>();
        Queue<List<Flight>> queue = new LinkedList<>();

        for (Flight f : adjacency.getOrDefault(from, Collections.emptyList())) {
            queue.add(new ArrayList<>(List.of(f)));
        }

        while (!queue.isEmpty()) {
            List<Flight> path = queue.poll();
            Flight last = path.get(path.size() - 1);
            if (path.size() > maxStops + 1) continue;

            Set<String> visited = new HashSet<>();
            for (Flight f : path) visited.add(f.from.code);

            if (last.to.code.equals(to)) {
                results.add(new RouteResult(new ArrayList<>(path)));
            } else if (path.size() <= maxStops + 1) {
                for (Flight next : adjacency.getOrDefault(last.to.code, Collections.emptyList())) {
                    if (!visited.contains(next.to.code)) {
                        List<Flight> newPath = new ArrayList<>(path);
                        newPath.add(next);
                        queue.add(newPath);
                    }
                }
            }
        }

        results.sort(Comparator.comparingInt((RouteResult r) -> r.totalPrice)
                               .thenComparingInt(r -> r.totalDuration));
        return results;
    }
}

import java.util.List;

public class RouteResult {
    public List<Flight> flights;
    public int totalPrice, totalDuration, stops;

    public RouteResult(List<Flight> flights) {
        this.flights = flights;
        this.stops = flights.size() - 1;
        this.totalPrice = flights.stream().mapToInt(f -> f.price).sum();
        this.totalDuration = flights.stream().mapToInt(f -> f.durationMins).sum();
    }
}

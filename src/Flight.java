public class Flight {
    public String flightNumber, airline, departureTime, arrivalTime, seatClass, type;
    public Airport from, to;
    public int durationMins, price, seatsAvailable;

    public Flight(String flightNumber, String airline, Airport from, Airport to,
                  String departureTime, String arrivalTime, int durationMins,
                  int price, String seatClass, int seatsAvailable, String type) {
        this.flightNumber = flightNumber; this.airline = airline;
        this.from = from; this.to = to;
        this.departureTime = departureTime; this.arrivalTime = arrivalTime;
        this.durationMins = durationMins; this.price = price;
        this.seatClass = seatClass; this.seatsAvailable = seatsAvailable;
        this.type = type;
    }

    public static String formatMins(int mins) {
        return (mins / 60) + "h " + (mins % 60) + "m";
    }
}

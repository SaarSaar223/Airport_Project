import java.util.List;

public interface IAirportBack {

    public List<Airport> findLowestCostRoutePath(String departure, String destination);

    public double findLowestCostRouteCost(String departure, String destination);
    //find the lowest money cost
    public List<Airport> findLowestTimeCostRoutePath(String departure, String destination);

    public double findLowestTimeCostRouteCost(String departure, String destination);

    //find the lowest time spanning tree
    public CS400Graph<Airport,Double> findLowestTimeSpanningRoute(String departureAirports);

    //find the lowest money cost spanning tree
    public CS400Graph<Airport,Double> findLowestCostSpanningRoute(String departureAirports);
}

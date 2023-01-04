import java.util.List;

public class AirportBack implements IAirportBack {
    protected CS400Graph<Airport,Double> graphCost;
    protected CS400Graph<Airport,Double> graphTime;

    public AirportBack(AirportLoader loader){
    this.graphCost = loader.costGraph;
    this.graphTime = loader.timeGraph;

    }
    @Override public List<Airport> findLowestCostRoutePath(String departure, String destination) {
        List<Airport> test = graphCost.shortestPath(new Airport(departure),new Airport(destination));
        return test;
    }

    @Override public double findLowestCostRouteCost(String departure, String destination) {
        return graphCost.getPathCost(new Airport(departure), new Airport(destination));
    }

    @Override public List<Airport> findLowestTimeCostRoutePath(String departure, String destination) {
        List<Airport> test = graphTime.shortestPath(new Airport(departure),new Airport(destination));
        return test;
    }

    @Override public double findLowestTimeCostRouteCost(String departure, String destination) {
        return graphTime.getPathCost(new Airport(departure), new Airport(destination));
    }

    @Override public CS400Graph<Airport, Double> findLowestTimeSpanningRoute(String startAirport) {
        return graphTime.getMinSpanningTree(new Airport(startAirport));
    }

    @Override public CS400Graph<Airport, Double> findLowestCostSpanningRoute(String startAirport) {
         return graphCost.getMinSpanningTree(new Airport(startAirport));
    }


}

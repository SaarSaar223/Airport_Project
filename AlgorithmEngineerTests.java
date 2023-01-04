import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlgorithmEngineerTests {
  private CS400Graph<Integer,Integer> graph;
  
  /**
   * Instantiate graph from GeeksForGeeks
   */
  @BeforeEach
  public void createGraph() {
      graph = new CS400Graph<>();
      // insert vertices A-F
      graph.insertVertex(0);
      graph.insertVertex(1);
      graph.insertVertex(2);
      graph.insertVertex(3);
      graph.insertVertex(4);
      graph.insertVertex(5);
      graph.insertVertex(6);
      graph.insertVertex(7);
      graph.insertVertex(8);
      
      graph.insertEdge(0,1,4);
      graph.insertEdge(1,0,4);
      
      graph.insertEdge(0,7,8);
      graph.insertEdge(7,0,8);
      
      graph.insertEdge(1,2,8);
      graph.insertEdge(2,1,8);
      
      graph.insertEdge(2,3,7);
      graph.insertEdge(3,2,7);
      
      graph.insertEdge(3,4,9);
      graph.insertEdge(4,3,9);
      
      graph.insertEdge(4,5,10);
      graph.insertEdge(4,5,10);
      
      graph.insertEdge(5,6,2);
      graph.insertEdge(6,5,2);
      
      graph.insertEdge(6,7,1);
      graph.insertEdge(7,6,1);
      
      graph.insertEdge(1,7,11);
      graph.insertEdge(7,1,11);
      
      graph.insertEdge(7,8,7);
      graph.insertEdge(8,7,7);
      
      graph.insertEdge(6,8,6);
      graph.insertEdge(8,6,6);
      
      graph.insertEdge(2,8,2);
      graph.insertEdge(8,2,2);
      
      graph.insertEdge(2,5,4);
      graph.insertEdge(5,2,4);
      
      graph.insertEdge(3,5,14);
      graph.insertEdge(5,3,14);
  }
  
  /**
   * Checks the correct vertices in the minimum spanning tree when starting node is 0
   */
  @Test
  public void testMSTVerticesStarting0() {
    CS400Graph<Integer,Integer> graphMST = graph.getMinSpanningTree(0);
    assertTrue(graphMST.containsVertex(0));
    assertTrue(graphMST.containsVertex(1));
    assertTrue(graphMST.containsVertex(2));
    assertTrue(graphMST.containsVertex(3));
    assertTrue(graphMST.containsVertex(4));
    assertTrue(graphMST.containsVertex(5));
    assertTrue(graphMST.containsVertex(6));
    assertTrue(graphMST.containsVertex(7));
    assertTrue(graphMST.containsVertex(8));
  }
  
  /**
   * Checks the correct vertices in the minimum spanning tree when starting node is 8
   */
  @Test
  public void testMSTVerticesStarting8() {
    CS400Graph<Integer,Integer> graphMST = graph.getMinSpanningTree(8);
    assertTrue(graphMST.containsVertex(0));
    assertTrue(graphMST.containsVertex(1));
    assertTrue(graphMST.containsVertex(2));
    assertTrue(graphMST.containsVertex(3));
    assertTrue(graphMST.containsVertex(4));
    assertTrue(graphMST.containsVertex(5));
    assertTrue(graphMST.containsVertex(6));
    assertTrue(graphMST.containsVertex(7));
    assertTrue(graphMST.containsVertex(8));
  }


  /**
   * Checks the correct edges in the minimum spanning tree when starting node is 0
   */
  @Test
  public void testMSTEdgesStarting0() {
    CS400Graph<Integer, Integer> graphMST = graph.getMinSpanningTree(0);
    assertTrue(graphMST.containsEdge(0, 1));
    assertTrue(graphMST.containsEdge(1, 2));
    assertTrue(graphMST.containsEdge(2, 8));
    assertTrue(graphMST.containsEdge(2, 5));
    assertTrue(graphMST.containsEdge(2, 3));
    assertTrue(graphMST.containsEdge(3, 4));
    assertTrue(graphMST.containsEdge(5, 6));
    assertTrue(graphMST.containsEdge(6, 7));
  }
  

  /**
   * Checks the correct edges in the minimum spanning tree when starting node is 8
   */
  @Test
  public void testMSTEdgesStarting8() {
    CS400Graph<Integer, Integer> graphMST = graph.getMinSpanningTree(8);
    assertTrue(graphMST.containsEdge(0, 1));
    assertTrue(graphMST.containsEdge(2, 5));
    assertTrue(graphMST.containsEdge(2, 3));
    assertTrue(graphMST.containsEdge(3, 4));
    assertTrue(graphMST.containsEdge(5, 6));
    assertTrue(graphMST.containsEdge(6, 7));
    assertTrue(graphMST.containsEdge(7, 0));
    assertTrue(graphMST.containsEdge(8, 2));
  }
  
  /**
   * Checks the correct cost in the minimum spanning tree when the starting node is 0
   */
  @Test
  public void testMSTCostStarting0() {
    assertTrue(graph.getMinSpanningTreeCost(0) == 37.0);
    
  }
  
  /**
   * Checks the correct cost in the minimum spanning tree when the starting node is 8
   */
  @Test
  public void testMSTCostStarting8() {
    assertTrue(graph.getMinSpanningTreeCost(8) == 37.0);
  }
  
  
  /**
   * Checks if the Data Wrangler has inputted the correct vertices into the cost and time graphs.
   */
  @Test
  public void CodeReviewofDataWranglerVertexCheckOfTime() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphTime = loader.timeGraph;
    CS400Graph<Airport,Double> graphCost = loader.costGraph;
    
    
    
    Airport a1 = new Airport("Denver International Airport");
    Airport a2 = new Airport("Dallas Fort/Worth");
    Airport a3 = new Airport("Orlando International Airport");
    Airport a4 = new Airport("Salt Lake City International");
    Airport a5 = new Airport("O'Hare International Airport");
    Airport a6 = new Airport("Dane County Regional Airport");
    assertTrue(graphTime.containsVertex(a1));
    assertTrue(graphTime.containsVertex(a2));
    assertTrue(graphTime.containsVertex(a3));
    assertTrue(graphTime.containsVertex(a4));
    assertTrue(graphTime.containsVertex(a5));
    assertTrue(graphTime.containsVertex(a6));
    
    assertTrue(graphCost.containsVertex(a1));
    assertTrue(graphCost.containsVertex(a2));
    assertTrue(graphCost.containsVertex(a3));
    assertTrue(graphCost.containsVertex(a4));
    assertTrue(graphCost.containsVertex(a5));
    assertTrue(graphCost.containsVertex(a6));
  }
  
  /**
   * Checks if the Data Wrangler has correctly inputted the edges into the Time Graph
   */
  @Test
  public void CodeReviewofDataWranglerEdgeCheckOfTime() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphTime = loader.timeGraph;
    
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    assertTrue(graphTime.containsEdge(A, B));
    assertTrue(graphTime.containsEdge(A, C));
    assertTrue(graphTime.containsEdge(A, D));
    assertTrue(graphTime.containsEdge(A, E));
    assertTrue(graphTime.containsEdge(A, F));
    assertTrue(graphTime.containsEdge(B, F));
    assertTrue(graphTime.containsEdge(B, D));
    assertTrue(graphTime.containsEdge(B, C));
    assertTrue(graphTime.containsEdge(C, F));
    assertTrue(graphTime.containsEdge(C, E));
    assertTrue(graphTime.containsEdge(C, D));
    assertTrue(graphTime.containsEdge(D, F));
    assertTrue(graphTime.containsEdge(D, E));
    assertTrue(graphTime.containsEdge(E, F));
    
  }
  
  /**
   * Checks if the data wrangler has correctly inputted the edges into Cost Graph
   */
  @Test
  public void CodeReviewofDataWranglerEdgeCheckOfCost() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphCost = loader.costGraph;
    
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    assertTrue(graphCost.containsEdge(A, B));
    assertTrue(graphCost.containsEdge(A, C));
    assertTrue(graphCost.containsEdge(A, D));
    assertTrue(graphCost.containsEdge(A, E));
    assertTrue(graphCost.containsEdge(A, F));
    assertTrue(graphCost.containsEdge(B, F));
    assertTrue(graphCost.containsEdge(B, D));
    assertTrue(graphCost.containsEdge(B, C));
    assertTrue(graphCost.containsEdge(C, F));
    assertTrue(graphCost.containsEdge(C, E));
    assertTrue(graphCost.containsEdge(C, D));
    assertTrue(graphCost.containsEdge(D, F));
    assertTrue(graphCost.containsEdge(D, E));
    assertTrue(graphCost.containsEdge(E, F));
    
  }
  
  /**
   * Checks the correct lowest cost and the route from Denver International Airport to Dane County
   */
  @Test
  public void IntegrationofDataWranglerShortestPathEtoFCost() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphCost = loader.costGraph;
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    
    assertEquals(graphCost.shortestPath(E, F).toString(), "[O'Hare International Airport, Orlando International Airport, Dane County Regional Airport]");
    assertEquals(graphCost.getPathCost(E, F), 98.0);
  }
  

  
  /**
   * Checks the correct lowest time and route of getting from O'Hare Airport to Dane County
   */
  @Test
  public void IntegrationofDataWranglerShortestPathEtoFTime() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphTime = loader.timeGraph;
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    
    assertEquals(graphTime.shortestPath(E, F).toString(), "[O'Hare International Airport, Dane County Regional Airport]");
    assertEquals(graphTime.getPathCost(E, F), 1.0);
  }
  
  
  /**
   * Checks the correct time cost and MST in the minimum spanning tree when the starting node is Denver 
   * International Airport
   */
  @Test
  public void IntegrationofDataWranglerMSTTime() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphTime = loader.timeGraph;
    
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    
    CS400Graph<Airport,Double> graphTimeMST = graphTime.getMinSpanningTree(A);
    assertTrue(graphTimeMST.containsVertex(A));
    assertTrue(graphTimeMST.containsVertex(B));
    assertTrue(graphTimeMST.containsVertex(C));
    assertTrue(graphTimeMST.containsVertex(D));
    assertTrue(graphTimeMST.containsVertex(E));
    assertTrue(graphTimeMST.containsVertex(F));

    assertEquals(graphTime.getMinSpanningTreeCost(A), 9.3);
    
  }
  
  /**
   * Checks the correct cost and MST in the minimum spanning tree when the starting node is Denver International 
   * Airport
   */
  @Test
  public void IntegrationofDataWranglerMSTCost() {
    AirportLoader loader = new AirportLoader();
    String times = "airportsTime.gv";
    String costs = "airports.gv";
    
    try {
      loader.loadAirport(times, costs);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    CS400Graph<Airport,Double> graphCost = loader.costGraph;
    
    Airport A = new Airport("Denver International Airport");
    Airport B = new Airport("Dallas Fort/Worth");
    Airport C = new Airport("Orlando International Airport");
    Airport D = new Airport("Salt Lake City International");
    Airport E = new Airport("O'Hare International Airport");
    Airport F = new Airport("Dane County Regional Airport");
    
    CS400Graph<Airport,Double> graphCostMST = graphCost.getMinSpanningTree(A);
    assertTrue(graphCostMST.containsVertex(A));
    assertTrue(graphCostMST.containsVertex(B));
    assertTrue(graphCostMST.containsVertex(C));
    assertTrue(graphCostMST.containsVertex(D));
    assertTrue(graphCostMST.containsVertex(E));
    assertTrue(graphCostMST.containsVertex(F));

    assertEquals(graphCost.getMinSpanningTreeCost(A), 198.0);
  }
  
  
  
  
  
  
  


}

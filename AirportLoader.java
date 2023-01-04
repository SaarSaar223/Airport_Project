import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;

/**
 * An Airport loader class for laoding in .gv extended files
 * Stores the resultant time/cost graphs into two Dijkstra structures called costGraph and
 * timeGraph, respectively, and these can be publicly accessed
 */
public class AirportLoader implements IAirportLoader {
  protected CS400Graph<Airport, Double> costGraph,
                                      timeGraph;
  protected Hashtable<String, Airport> vLabels;


  /**
   * Initializes the AirportLoader
   */
  AirportLoader() {
    costGraph = new CS400Graph<>();
    timeGraph = new CS400Graph<>();
    vLabels = new Hashtable<>();
  }

  /**
   * Parses a line.
   * Vertex format: "CHAR [label="AIRPORT NAME"]"
   * Edge format: "CHAR1->CHAR2 [weight=WEIGHT]"
   * @param line Line2Parse
   * @param isCost Indicates if we should save result into the cost graph
   */
  protected void parseLine(String line, boolean isCost) {
    if((line = line.trim()).equals("")) return;
    String[] parsed = line.split(" ");
    if(line.contains("weight")) {
      Pattern pattern = Pattern.compile("=(.*?)]");
      Matcher matcher = pattern.matcher(line);
      if(matcher.find()) {
        Airport a1 = vLabels.get(Character.toString(parsed[0].charAt(0))),
                a2 = vLabels.get(Character.toString(parsed[0].charAt(3)));
        if(a1==null || a2 ==null) throw new IllegalStateException("Did not " +
                "receive a node");
        try {
          double edgeWeight =
                  Double.parseDouble(matcher.group().substring(1,matcher.group().length()-1));
          if(isCost) {
            costGraph.insertEdge(a1, a2, edgeWeight);
            //System.out.println(costGraph.containsEdge(a1, a2));
          }
          else timeGraph.insertEdge(a1, a2, edgeWeight);
        } catch(NumberFormatException e) {
          throw new IllegalStateException("Bad edge weight format");
        }
      } else {
        throw new IllegalStateException("Improper file format");
      }
    } else if(line.contains("label")) {
      Pattern pattern = Pattern.compile("\"(.*?)\"");
      Matcher matcher = pattern.matcher(line);
      if(matcher.find()) {
        Airport a = new Airport();
        String airportName = matcher.group();
        String first = Character.toString(line.charAt(0));
        a.name = airportName.substring(1, airportName.length()-1);
        vLabels.put(first, a);
        if(isCost) costGraph.insertVertex(a);
        else timeGraph.insertVertex(a);
      }
    }
  }

  /**
   * Loads in the airport edge datasets
   *
   * @param filePathTime The path to the time weights
   * @param filePathCost Path to the cost weights
   * @throws FileNotFoundException if any file path is not found or does not end in .gv
   */
  @Override
  @SuppressWarnings("all")
  public void loadAirport(String filePathTime, String filePathCost) throws FileNotFoundException {
    Scanner s = null;

    if(filePathCost==null || filePathTime == null ||
            !filePathCost.contains(".gv") || !filePathTime.contains(".gv"))
      throw new FileNotFoundException("Could not find graph file");

    try {
      s = new Scanner(new File(filePathTime));

      // Going first through the time graph
      while(s.hasNextLine()) {
        parseLine(s.nextLine(), false);
      }

      s = new Scanner(new File(filePathCost));
      while(s.hasNextLine()) {
        parseLine(s.nextLine(), true);
      }
    } finally {
      if(s != null) try {
        s.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }

}

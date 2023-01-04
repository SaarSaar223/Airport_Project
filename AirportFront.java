import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

public class AirportFront implements IAirportFront {
    private Scanner scanner;
    private AirportBack airportBackPlaceholder;

    public AirportFront(Scanner scanner, AirportBack airportBackPlaceholder) {
        this.scanner = scanner;
        this.airportBackPlaceholder = airportBackPlaceholder;
    }

    @Override public void runCommandLoop() {
        boolean keepRunning = true;
        int userInput = 0;
        System.out.println("Welcome to the Airport Shortest Path Application!");
        System.out.println("*-**-**-**-**-**-**-**-**-**-**-**-**-**-**-**-**-*");
        do {
            displayMainMenu();
            while (true) {
                System.out.println("Please,entering a number between 1 - 5!");
                System.out.print("> ");
                try {
                    userInput = Integer.parseInt(scanner.nextLine());
                    if (userInput > 0 && userInput < 6) {
                        break;
                    } else {
                        System.out.println("Please,entering a valid value!");
                        System.out.print(">");
                    }
                } catch (Exception e) {
                    System.out.println("Please,entering a valid value!");
                }
            }
            switch (userInput) {
                case 1:
                    displayLowestCostRoute();
                    break;
                case 2:
                    displayLowestTimeRoute();
                    break;
                case 3:
                    displayLowestCostSpanningRoute();
                    break;
                case 4:
                    displayLowestTimeSpanningRoute();
                    break;
                case 5:
                    System.out.println("Thanks for using this Application!");
                    keepRunning = false;
                    break;
            }
        } while (keepRunning);
    }

    @Override public void displayMainMenu() {
        System.out.println();
        System.out.println("You are in the Main Menu:");
        System.out.println("1. Find the Lowest Cost Path");
        System.out.println("2. Find the Lowest Time Path");
        System.out.println("3. Find the Lowest Cost Path for all Airports");
        System.out.println("4. Find the Lowest Time Path for all Airports");
        System.out.println("5. Exit the Application");

    }

    @Override public void displayLowestTimeRoute() {
        System.out.println();
        System.out.println("-**-Looking for Lowest Time Path-**-");
        String pathDisplay = "";
        System.out.println("Please Enter the Departure Airport");
        System.out.print("> ");
        String departureAirport = scanner.nextLine();
        System.out.println("Please Enter the Destination Airport");
        System.out.print("> ");
        String destinationAirport = scanner.nextLine();
        double shortestPathCost =
            airportBackPlaceholder.findLowestTimeCostRouteCost(departureAirport,
                destinationAirport);
        List<Airport> path = airportBackPlaceholder.findLowestTimeCostRoutePath(departureAirport,
            destinationAirport);

        try {
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    pathDisplay += path.get(i).name;
                } else {
                    pathDisplay += path.get(i).name + " -> ";
                }
            }
            System.out.println(
                "The lowest time path from " + departureAirport + " to " + destinationAirport
                    + " is:");
            System.out.println(pathDisplay);
            System.out.println(
                "The total time from " + departureAirport + " to " + destinationAirport + " is "
                    + shortestPathCost);
        } catch (Exception e) {
            System.out.println("Sorry, Path from " + departureAirport + " to " + destinationAirport
                + " cannot be found....");
        }
    }

    @Override public void displayLowestCostRoute() {
        System.out.println();
        System.out.println("-**-Looking for Lowest Cost Path-**-");
        String pathDisplay = "";
        System.out.println("Please Enter the Departure Airport");
        System.out.print("> ");
        String departureAirport = scanner.nextLine();
        System.out.println("Please Enter the Destination Airport");
        System.out.print("> ");
        String destinationAirport = scanner.nextLine();
        double shortestPathCost =
            airportBackPlaceholder.findLowestCostRouteCost(departureAirport, destinationAirport);
        List<Airport> path =
            airportBackPlaceholder.findLowestCostRoutePath(departureAirport, destinationAirport);
        try {
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    pathDisplay += path.get(i).name;
                } else {
                    pathDisplay += path.get(i).name + " -> ";
                }
            }

            System.out.println(
                "The lowest cost path from " + departureAirport + " to " + destinationAirport
                    + " is:");
            System.out.println(pathDisplay);
            System.out.println(
                "The total cost from " + departureAirport + " to " + destinationAirport + " is "
                    + shortestPathCost);
        } catch (Exception e) {
            System.out.println("Sorry, Path from " + departureAirport + " to " + destinationAirport
                + " cannot be found....");
        }
    }


    @Override public void displayLowestTimeSpanningRoute() {
        System.out.println();
        System.out.println("-**-Looking for Lowest Time Spanning Path-**-");
        System.out.println("Please Enter the Departure Airport");
        System.out.print("> ");
        String departureAirport = scanner.nextLine();
        try {

            Airport startNode = new Airport(departureAirport);
            CS400Graph<Airport, Double> front =
                airportBackPlaceholder.findLowestTimeSpanningRoute(departureAirport);
            ArrayList<String> string = this.getAirportList("airportsTime.gv");
            ArrayList<Airport> temp = new ArrayList<>();
            String[] toReturn = new String[string.size()];
            for (int i = 0; i < string.size(); i++){
                temp.add(new Airport(string.get(i)));
            }

            for (int i = 0; i < temp.size(); i++){
                if(temp.get(i).name.equals(startNode.name)){
                    continue;
                }
                List l = front.shortestPath(startNode,temp.get(i));
                for (int j = 0; j < l.size(); j++){
                    if(j == l.size()-1) {
                        toReturn[i] += l.get(j).toString();
                    }
                    else
                    toReturn[i] += l.get(j).toString() + " -> ";
                }
            }

            System.out.println("The Lowest Time Spanning Path from " + departureAirport + " is:");
//            System.out.println("The Total Time Spanning the Path is " + front.getMinSpanningTreeCost(startNode));
               for (String s: toReturn){
                   if(s != null) {
                       System.out.println(s.substring(4));
                   }
               }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Oops, something wrong......");
        }

    }

    public ArrayList<String> getAirportList(String filename) throws FileNotFoundException {
        ArrayList<String> airportNames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.contains("label=")) {
               String[] x = s.split("=");
               airportNames.add(x[1].substring(1, x[1].length()-2));
            }
        }
        scanner.close();
        return airportNames;

    }

    @Override public void displayLowestCostSpanningRoute() {
        System.out.println();
        System.out.println("-**-Looking for Lowest Cost Spanning Path-**-");
        System.out.println("Please Enter the Departure Airport");
        System.out.print("> ");
        String departureAirport = scanner.nextLine();
        try {

            Airport startNode = new Airport(departureAirport);
            CS400Graph<Airport, Double> front =
                airportBackPlaceholder.findLowestTimeSpanningRoute(departureAirport);
            ArrayList<String> string = this.getAirportList("airports.gv");
            ArrayList<Airport> temp = new ArrayList<>();
            String[] toReturn = new String[string.size()];
            for (int i = 0; i < string.size(); i++){
                temp.add(new Airport(string.get(i)));
            }

            for (int i = 0; i < temp.size(); i++){
                if(temp.get(i).name.equals(startNode.name)){
                    continue;
                }
                List l = front.shortestPath(startNode,temp.get(i));
                for (int j = 0; j < l.size(); j++){
                    if(j == l.size()-1) {
                        toReturn[i] += l.get(j).toString();
                    }
                    else
                        toReturn[i] += l.get(j).toString() + " -> ";
                }
            }

            System.out.println("The Lowest Cost Spanning Path from " + departureAirport + " is:");
//            System.out.println("The Total Cost Spanning the Path is " + front.getMinSpanningTreeCost(new Airport(departureAirport)));

            for (String s: toReturn){
                if(s != null) {
                    System.out.println(s.substring(4));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Oops, something wrong......");
        }

    }
}


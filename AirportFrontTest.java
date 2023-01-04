import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirportFrontTest {

    @Test void runCommandLoop() throws FileNotFoundException {
        TextUITester tester = new TextUITester("5\n");
        Scanner scanner = new Scanner(System.in);
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        AirportFront front = new AirportFront(scanner, back);

        front.runCommandLoop();
        String output = tester.checkOutput();
        //        System.out.println(output);
        assertTrue(output.contains("Welcome to the Airport Shortest Path Application!"));
        assertTrue(output.contains("*-**-**-**-**-**-**-**-**-**-**-**-**-**-**-**-**-*"));
        assertTrue(output.contains("You are in the Main Menu:\n" + "1. Find the Lowest Cost Path\n"
            + "2. Find the Lowest Time Path\n" + "3. Find the Lowest Cost Path for all Airports\n"
            + "4. Find the Lowest Time Path for all Airports\n" + "5. Exit the Application\n"));
        assertTrue(output.contains("Please,entering a number between 1 - 5!"));
        assertTrue(output.contains("Thanks for using this Application!"));

    }

    @Test void displayMainMenu() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        AirportFront front = new AirportFront(scanner, back);
        TextUITester tester = new TextUITester("");

        front.displayMainMenu();
        String output = tester.checkOutput();
        assertTrue(output.contains("You are in the Main Menu:"));
        assertTrue(output.contains("1. Find the Lowest Cost Path"));
        assertTrue(output.contains("2. Find the Lowest Time Path"));
        assertTrue(output.contains("3. Find the Lowest Cost Path for all Airports"));
        assertTrue(output.contains("4. Find the Lowest Time Path for all Airports"));
        assertTrue(output.contains("5. Exit the Application"));
    }

    @Test void displayLowestTimeRoute() throws FileNotFoundException {

        TextUITester tester = new TextUITester("Denver International Airport\nDallas Fort/Worth\n");
        Scanner scanner = new Scanner(System.in);
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        AirportFront front = new AirportFront(scanner, back);

        front.displayLowestTimeRoute();
        String output = tester.checkOutput();
        assertTrue(output.contains("-**-Looking for Lowest Time Path-**-"));
        assertTrue(output.contains("Please Enter the Departure Airport"));
        assertTrue(output.contains("Please Enter the Destination Airport"));
        assertTrue(output.contains(
            "The lowest time path from Denver International Airport to Dallas Fort/Worth is:"));
        assertTrue(output.contains("Denver International Airport -> Dallas Fort/Worth"));
        assertTrue(output.contains(
            "The total time from Denver International Airport to Dallas Fort/Worth is 2.0"));

    }

    @Test void displayLowestCostRoute() throws FileNotFoundException {
        TextUITester tester = new TextUITester("Denver International Airport\nDallas Fort/Worth\n");
        Scanner scanner = new Scanner(System.in);
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        AirportFront front = new AirportFront(scanner, back);

        front.displayLowestCostRoute();
        String output = tester.checkOutput();

        assertTrue(output.contains("-**-Looking for Lowest Cost Path-**-"));
        assertTrue(output.contains("Please Enter the Departure Airport"));
        assertTrue(output.contains("Please Enter the Destination Airport"));
        assertTrue(output.contains("Denver International Airport -> Dallas Fort/Worth"));
        assertTrue(output.contains(
            "The total cost from Denver International Airport to Dallas Fort/Worth is 30.0"));
    }

    @Test void displayLowestTimeSpanningRoute() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        TextUITester tester = new TextUITester("Orlando International Airport\n");
        Scanner scanner = new Scanner(System.in);
        AirportFront test = new AirportFront(scanner, back);
        test.displayLowestTimeSpanningRoute();
        String output = tester.checkOutput();
        assertTrue(output.contains(
            "Orlando International Airport -> Dallas Fort/Worth -> Denver International Airport\n"
                + "Orlando International Airport -> Dallas Fort/Worth\n"
                + "Orlando International Airport -> Dallas Fort/Worth -> Denver International Airport -> Salt Lake City International\n"
                + "Orlando International Airport -> Dallas Fort/Worth -> Denver International Airport -> Dane County Regional Airport -> O'Hare International Airport\n"
                + "Orlando International Airport -> Dallas Fort/Worth -> Denver International Airport -> Dane County Regional Airport\n"));
    }

    @Test void displayLowestCostSpanningRoute() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        TextUITester tester = new TextUITester("O'Hare International Airport\n");
        Scanner scanner = new Scanner(System.in);
        AirportFront test = new AirportFront(scanner, back);
        test.displayLowestTimeSpanningRoute();
        String output = tester.checkOutput();
        assertTrue(output.contains(
            "O'Hare International Airport -> Dane County Regional Airport -> Denver International Airport\n"
                + "O'Hare International Airport -> Dane County Regional Airport -> Denver International Airport -> Dallas Fort/Worth\n"
                + "O'Hare International Airport -> Dane County Regional Airport -> Denver International Airport -> Dallas Fort/Worth -> Orlando International Airport\n"
                + "O'Hare International Airport -> Dane County Regional Airport -> Denver International Airport -> Salt Lake City International\n"
                + "O'Hare International Airport -> Dane County Regional Airport\n"));
    }

    @Test void CodeReviewOfBackend1() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        //        System.out.println(back.findLowestCostRouteCost("O'Hare International Airport",
        //            "Dane County Regional Airport"));
        assertEquals(98.0, back.findLowestCostRouteCost("O'Hare International Airport",
            "Dane County Regional Airport"));
        //        System.out.println(back.findLowestCostRouteCost("O'Hare International Airport",
        //            "Denver International Airport"));
        assertEquals(69.0, back.findLowestCostRouteCost("O'Hare International Airport",
            "Denver International Airport"));
        //        System.out.println(back.findLowestCostRouteCost("O'Hare International Airport",
        //            "Salt Lake City International"));
        assertEquals(68.0, back.findLowestCostRouteCost("O'Hare International Airport",
            "Salt Lake City International"));
    }

    @Test void CodeReviewOfBackend2() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        //                System.out.println(back.findLowestTimeCostRouteCost("O'Hare International Airport",
        //                    "Dane County Regional Airport"));
        assertEquals(1.0, back.findLowestTimeCostRouteCost("O'Hare International Airport",
            "Dane County Regional Airport"));
        //                System.out.println(back.findLowestTimeCostRouteCost("O'Hare International Airport",
        //                    "Denver International Airport"));
        assertEquals(2.5, back.findLowestTimeCostRouteCost("O'Hare International Airport",
            "Denver International Airport"));
        //                System.out.println(back.findLowestTimeCostRouteCost("O'Hare International Airport",
        //                    "Salt Lake City International"));
        assertEquals(3.3, back.findLowestTimeCostRouteCost("O'Hare International Airport",
            "Salt Lake City International"));
    }

    @Test void IntegrationTest1() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        Airport a1 = new Airport("Denver International Airport"), a2 =
            new Airport("Dallas Fort/Worth"), a3 = new Airport("Orlando International Airport"),
            a4 = new Airport("Salt Lake City International"), a5 =
            new Airport("O'Hare International Airport"), a6 =
            new Airport("Dane County Regional Airport");

        Airport[] list = {a1, a2, a3, a4, a5, a6};
        for (Airport a : list) {
            Assertions.assertTrue(loader.costGraph.containsVertex(a));
            Assertions.assertTrue(loader.timeGraph.containsVertex(a));
            for (Airport b : list) {
                if (!a.equals(b))
                    Assertions.assertTrue(loader.timeGraph.containsEdge(a, b));
            }
        }
    }

    @Test void IntegrationTest2() {
        CS400Graph graph = new CS400Graph();
        Airport a1 = new Airport("Denver International Airport"), a2 =
            new Airport("Dallas Fort/Worth"), a3 = new Airport("Orlando International Airport"),
            a4 = new Airport("Salt Lake City International"), a5 =
            new Airport("O'Hare International Airport"), a6 =
            new Airport("Dane County Regional Airport");
        graph.insertVertex(a1);
        graph.insertVertex(a2);
        graph.insertVertex(a3);
        graph.insertVertex(a4);
        graph.insertVertex(a5);
        graph.insertVertex(a6);
        Airport[] airport = new Airport[] {a1, a2, a3, a5, a4, a6};

        for (int i = 0; i < airport.length; i++) {
            assertTrue(graph.containsVertex(airport[i]));
        }
    }
}

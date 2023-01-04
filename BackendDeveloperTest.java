import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class BackendDeveloperTest {
    @BeforeEach
    public void creatInstance() {
        AirportBack backend = new AirportBack();
        CS400Graph airportGraph = new CS400Graph();
        airportGraph.insertVertex(0);
        airportGraph.insertVertex(1);
        airportGraph.insertVertex(2);
        airportGraph.insertVertex(3);
        airportGraph.insertVertex(4);
        airportGraph.insertVertex(5);
    }

    /**
     * test Lowest Cost
     */
    @Test void test1() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        AirportBack backend = new AirportBack(loader);
        loader.loadAirport("airportsTime.gv", "airports.gv");

        assertEquals(30.0, backend.findLowestCostRouteCost("Denver International Airport",
                "Dallas Fort/Worth"));

    }

    /**
     * test lowest time
     */
    @Test
    public void test2() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        AirportBack backend = new AirportBack(loader);
        loader.loadAirport("airportsTime.gv", "airports.gv");

        assertEquals(2.0, backend.findLowestTimeCostRouteCost("Denver International Airport",
                "Dallas Fort/Worth"));
        assertEquals(2.5, backend.findLowestTimeCostRouteCost("Dallas Fort/Worth",
                "Orlando International Airport"));
    }

    /**
     * test lowest time
     */
    @Test
    public void test3() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        AirportBack backend = new AirportBack(loader);
        loader.loadAirport("airportsTime.gv", "airports.gv");

        assertEquals(2.5, backend.findLowestTimeCostRouteCost("Dallas Fort/Worth",
                "Orlando International Airport"));
    }


    /**
     * test Lowest Cost
     */
    @Test void test4() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        AirportBack backend = new AirportBack(loader);
        loader.loadAirport("airportsTime.gv", "airports.gv");

        assertEquals(41.0, backend.findLowestCostRouteCost("Dallas Fort/Worth",
                "Orlando International Airport"));
    }
    /**
     * test Lowest Cost
     */
    @Test void test5() throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        AirportBack backend = new AirportBack(loader);
        loader.loadAirport("airportsTime.gv", "airports.gv");

        assertEquals(84.0, backend.findLowestCostRouteCost("Orlando International Airport",
                "Salt Lake City International"));
    }

    /**
     * test DisplayLowestCost
     */
    @Test void CodeReviewOfFrontend1() throws FileNotFoundException {
        TextUITester tester = new TextUITester("Orlando International Airport\nSalt Lake City International\n");
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        Scanner scanner = new Scanner(System.in);
        AirportBack backend = new AirportBack(loader);
        AirportFront frontend = new AirportFront(scanner, backend);

        frontend.displayLowestCostRoute();
        String output = tester.checkOutput();

        assertTrue(output.contains("-**-Looking for Lowest Cost Path-**-"));
        assertTrue(output.contains("Please Enter the Departure Airport"));
        assertTrue(output.contains("Please Enter the Destination Airport"));
        assertTrue(output.contains("The lowest cost path from Orlando International Airport to Salt Lake City International is:"));
        assertTrue(output.contains("Orlando International Airport -> Salt Lake City International"));
        assertTrue(output.contains(
                "The total cost from Orlando International Airport to Salt Lake City International is 84.0"));
    }

    @Test void CodeReviewOfFrontend2() throws FileNotFoundException {
        TextUITester tester = new TextUITester("Denver International Airport\nSalt Lake City International\n");
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        Scanner scanner = new Scanner(System.in);
        AirportBack backend = new AirportBack(loader);
        AirportFront frontend = new AirportFront(scanner, backend);

        frontend.displayLowestTimeRoute();
        String output = tester.checkOutput();

        assertTrue(output.contains("-**-Looking for Lowest Time Path-**-"));
        assertTrue(output.contains("Please Enter the Departure Airport"));
        assertTrue(output.contains("Please Enter the Destination Airport"));
        assertTrue(output.contains(
                "The lowest time path from Denver International Airport to Salt Lake City International is:"));
        assertTrue(output.contains("Denver International Airport -> Salt Lake City International"));
        assertTrue(output.contains(
                "The total time from Denver International Airport to Salt Lake City International is 1.6"));
    }

    @Test void IntegrationTest1() {
        CS400Graph graph = new CS400Graph();
        Airport a = new Airport("Denver International Airport");
        Airport b = new Airport("Dallas Fort/Worth");
        Airport c = new Airport("Orlando International Airport");
        Airport d = new Airport("Salt Lake City International");
        Airport e = new Airport("O'Hare International Airport");
        Airport f = new Airport("Dane County Regional Airport");

        graph.insertVertex(a);
        graph.insertVertex(b);
        graph.insertVertex(c);
        graph.insertVertex(d);
        graph.insertVertex(e);
        graph.insertVertex(f);
        Airport[] airports = new Airport[] {a, b, c, d, e, f};

        for (int i = 0; i < airports.length; i++) {
            assertTrue(graph.containsVertex(airports[i]));
        }
    }

    @Test void IntegrationTest2() {
        CS400Graph graph = new CS400Graph();
        Airport a = new Airport("Denver International Airport");
        Airport b = new Airport("Dallas Fort/Worth");
        Airport c = new Airport("Orlando International Airport");
        Airport d = new Airport("Salt Lake City International");
        Airport e = new Airport("O'Hare International Airport");
        Airport f = new Airport("Dane County Regional Airport");

        graph.insertVertex(a);
        graph.insertVertex(b);
        graph.insertVertex(c);
        graph.insertVertex(d);
        graph.insertVertex(e);
        graph.insertVertex(f);
        Airport[] airports = new Airport[] {a, b, c, d, e, f};

        for (int i = 0; i < airports.length; i++) {
            assertTrue(assertEquals(graph.getMinSpanningTreeCost(a), 198.0););
        }
    }
}


import java.io.FileNotFoundException;
import java.util.Scanner;

public class AirportApplication {
    public static void main(String[] args) throws FileNotFoundException {
        AirportLoader loader = new AirportLoader();
        loader.loadAirport("airportsTime.gv", "airports.gv");
        AirportBack back = new AirportBack(loader);
        Scanner scanner = new Scanner(System.in);
        AirportFront test = new AirportFront(scanner, back);
        test.runCommandLoop();
    }
}

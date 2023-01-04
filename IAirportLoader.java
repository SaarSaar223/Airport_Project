import java.io.FileNotFoundException;

public interface IAirportLoader {

  void loadAirport(String filePathTime, String filePathCost) throws FileNotFoundException;
}

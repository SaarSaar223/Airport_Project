public interface IAirportFront {

    // run Command Loop according to the user input
    public void runCommandLoop();

    // displaying the main menu for this application
    public void displayMainMenu();

    // print out the LowestTimeRoute
    public void displayLowestTimeRoute();

    // print out the LowestCostRoute
    public void displayLowestCostRoute();

    // print out the Lowest Time Spanning Route over all airport in the Graph
    public void displayLowestTimeSpanningRoute();

    // print out the Lowest Cost Spanning Route over all airports in the Graph
    public void displayLowestCostSpanningRoute();
}

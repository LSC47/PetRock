package petrock;

public class Main {
    public static void main(String[] args) {
        // Initialize the Model, View, and Controller
        PetRockModel rock = new PetRockModel("Pebbles");
        PetRockView view = new PetRockView();
        PetRockController controller = new PetRockController(rock);

        // Register the view as an observer of the controller
        controller.addObserver(view);

        // Start the game loop
        controller.runGameLoop();
    }
}
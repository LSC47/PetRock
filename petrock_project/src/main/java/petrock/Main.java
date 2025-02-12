package petrock;

public class Main {
    public static void main(String[] args) {
        // Load existing data if available
        PetRockModel rock = PetRockModel.loadFromFile();

        // Initialize View and Controller
        PetRockView view = new PetRockView();
        PetRockController controller = new PetRockController(rock, view);

        // Start the game loop
        controller.runGameLoop();
    }
    
}

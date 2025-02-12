package petrock;

public class PetRockController {
    private PetRockModel rock;
    private PetRockView view;
    private RandomEvent randomEvent;

    public PetRockController(PetRockModel rock, PetRockView view) {
        this.rock = rock;
        this.view = view;
        this.randomEvent = new RandomEvent();
    }

    // Run the game loop
    public void runGameLoop() {
        while (true) {
            view.displayMenu();
            int choice = view.getUserChoice();

            switch (choice) {
                case 1:
                    handleFeed();
                    break;
                case 2:
                    handlePlay();
                    break;
                case 3:
                    handlePolish();
                    break;
                case 4:
                    view.displayStatus(rock);
                    continue;
                case 5:
                    view.displayQuitGame();
                    rock.saveToFile();  // Save game before exiting
                    return;
                default:
                    view.displayInvalidChoice();
                    continue;
            }

            // Update game state
            rock.saveToFile(); // Save progress after every action
        }
    }

    public void handleFeed() {
        try {
            rock.feed();
            view.displayFeed();
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void handlePlay() {
        try {
            rock.play();
            view.displayPlay();
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    public void handlePolish() {
        rock.polish();
        view.displayPolish();
    }
}

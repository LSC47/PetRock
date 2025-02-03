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
                    continue; // Skip the rest of the loop and show the menu again
                case 5:
                    view.displayMessage("Goodbye!");
                    return; // Exit the game
                default:
                    view.displayMessage("Invalid choice. Please try again.");
                    continue; // Skip the rest of the loop and show the menu again
            }

            // Trigger a random event after each action
            randomEvent.triggerEvent(rock);

            // Update the rock's state for the next turn
            rock.increaseHungerAndBoredom();
            rock.restoreEnergy();
            rock.updateMood();

            // Reset cooldowns
            rock.resetCooldowns();

            // Check for game over conditions
            if (rock.isEnergyDepleted() || rock.isHungerOrBoredomMaxed()) {
                view.displayMessage("Game over! Your rock has rolled away.");
                break; // End the game loop
            }
        }
    }

    // Handle the feed action
    public void handleFeed() {
        try {
            rock.feed();
            view.displayMessage("You fed the rock. Hunger decreased, boredom increased.");
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    // Handle the play action
    public void handlePlay() {
        try {
            rock.play();
            view.displayMessage("You played with the rock. Boredom decreased, hunger increased.");
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    // Handle the polish action
    public void handlePolish() {
        rock.polish();
        view.displayMessage("You polished the rock. Hunger and boredom decreased, energy restored.");
    }
}
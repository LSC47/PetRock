package petrock;

public class PetRockController {
    private PetRock petRock;
    private PetRockView view;
    private RandomEvent randomEvent;

    public PetRockController(PetRock petRock, PetRockView view, RandomEvent randomEvent) {
        this.petRock = petRock;
        this.view = view;
        this.randomEvent = randomEvent;
    }

    public void runGameLoop() {
        boolean running = true;

        while (running) {
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
                    view.showStatus(petRock);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    view.displayMessage("Invalid choice. Please try again.");
            }

            String eventMessage = randomEvent.triggerEvent(petRock);
            if (!eventMessage.isEmpty()) {
                view.displayMessage(eventMessage);
            }

            checkGameOver();
        }
    }

    private void handleFeed() {
        try {
            petRock.feed();
            view.displayMessage(petRock.getName() + " has been fed.");
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    private void handlePlay() {
        try {
            petRock.play();
            view.displayMessage(petRock.getName() + " enjoyed playing!");
        } catch (IllegalStateException e) {
            view.displayMessage(e.getMessage());
        }
    }

    private void handlePolish() {
        petRock.polish();
        view.displayMessage(petRock.getName() + " is now shiny and polished!");
    }

    private void checkGameOver() {
        if (petRock.getHunger() >= 10 || petRock.getEnergy() <= 0) {
            view.displayMessage(petRock.getName() + " is not feeling well. Game over!");
            System.exit(0);
        }
    }
}
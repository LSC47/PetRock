package petrock;

public class Main  {
    public static void main( String[] args ) {
        Main game = new Main(); // Create the game object


    }

    // Game loop
    public void runGameLoop(PetRock rock) { // Continuously prompts the user for actions until they choose to quit. Handles cooldowns and random events.

    }

    // Manu display
    public void displayMenu(){ // Displays the menu options to the user.

    }

    // Action handling
    public void handleFeed(PetRock rock){ // Handles the "Feed the Rock" action.

    }

    public void handlePlay(PetRock rock) { // Handles the "Play with the Rock" action.

    } 

    public void handlePolish(PetRock rock) { // Handles the "Polish the Rock" action.

    } 

    public void handleCheckStatus(PetRock rock){ // Displays the rock's current status.

    }

    // Cooldown management
    public void applyCooldowns(PetRock rock){ // Tracks and applies cooldowns for feeding and playing actions.

    }

    // File Handling
    public PetRock loadRockState(){ // Loads the rock's state from the JSON file (if it exists) or creates a new rock.
        PetRock result = new PetRock();
        return result;
    }

    public void saveRockState(PetRock rock){ // Saves the rock's state to the JSON file.

    }

    // Game over handling
    public void checkGameOver(PetRock rock){ // Checks if the game is over (e.g., hunger or boredom reaches 10, or energy reaches 0) and displays the appropriate message.

    }







}

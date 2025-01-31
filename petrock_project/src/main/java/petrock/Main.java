package petrock;

import java.util.Scanner;

public class Main {
    private RandomEvent randomEvent = new RandomEvent(); // Random event handler
    private boolean feedOnCooldown = false; // Tracks if feeding is on cooldown
    private boolean playOnCooldown = false; // Tracks if playing is on cooldown
    private Scanner scanner = new Scanner(System.in); // For user input

    public static void main(String[] args) {
        Main game = new Main(); // Create the game object
        PetRock rock = game.loadRockState(); // Load or create a new rock
        game.runGameLoop(rock); // Start the game loop
    }

    // Game loop
    public void runGameLoop(PetRock rock) {
        while (true) {
            displayMenu(); // Display the menu
            int choice = getUserChoice(); // Get the user's choice

            // Handle the user's choice
            switch (choice) {
                case 1:
                    handleFeed(rock);
                    break;
                case 2:
                    handlePlay(rock);
                    break;
                case 3:
                    handlePolish(rock);
                    break;
                case 4:
                    handleCheckStatus(rock);
                    continue; // Skip the rest of the loop and show the menu again
                case 5:
                    saveRockState(rock); // Save the rock's state
                    System.out.println("Goodbye!");
                    return; // Exit the game
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Skip the rest of the loop and show the menu again
            }

            // Trigger a random event after each action
            randomEvent.triggerEvent(rock);

            // Update the rock's state for the next turn
            rock.increaseHungerAndBoredom();
            rock.restoreEnergy();
            rock.updateMood();

            // Apply cooldowns
            applyCooldowns(rock);

            // Check for game over conditions
            checkGameOver(rock);
            if (rock.isEnergyDepleted() || rock.isHungerOrBoredomMaxed()) {
                break; // End the game loop if the game is over
            }
        }
    }

    // Menu display
    public void displayMenu() {
        System.out.println("\n--- Pet Rock Management ---");
        System.out.println("1. Feed the Rock");
        System.out.println("2. Play with the Rock");
        System.out.println("3. Polish the Rock");
        System.out.println("4. Check the Rock's Status");
        System.out.println("5. Quit");
    }

    // Get user choice
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    // Action handling
    public void handleFeed(PetRock rock) {
        if (feedOnCooldown) {
            System.out.println("You cannot feed the rock again so soon!");
            return;
        }
        rock.feed(); // Perform the feed action
        feedOnCooldown = true; // Put feeding on cooldown
        System.out.println("You fed the rock. Hunger decreased, boredom increased.");
    }

    public void handlePlay(PetRock rock) {
        if (playOnCooldown) {
            System.out.println("You cannot play with the rock again so soon!");
            return;
        }
        rock.play(); // Perform the play action
        playOnCooldown = true; // Put playing on cooldown
        System.out.println("You played with the rock. Boredom decreased, hunger increased.");
    }

    public void handlePolish(PetRock rock) {
        rock.polish(); // Perform the polish action
        System.out.println("You polished the rock. Hunger and boredom decreased, energy restored.");
    }

    public void handleCheckStatus(PetRock rock) {
        System.out.println("\n--- Rock Status ---");
        System.out.println("Name: " + rock.getName());
        System.out.println("Mood: " + rock.getMood());
        System.out.println("Hunger: " + rock.getHunger());
        System.out.println("Boredom: " + rock.getBoredom());
        System.out.println("Energy: " + rock.getEnergy());
    }

    // Cooldown management
    public void applyCooldowns(PetRock rock) {
        if (feedOnCooldown) feedOnCooldown = false; // Reset feeding cooldown
        if (playOnCooldown) playOnCooldown = false; // Reset playing cooldown
    }

    // File handling
    public PetRock loadRockState() {
        PetRock rock = new PetRock("Pebbles"); // Default name if no file exists
        rock.loadState("PetRock.json"); // Load state from JSON file
        return rock;
    }

    public void saveRockState(PetRock rock) {
        rock.saveState("PetRock.json"); // Save state to JSON file
    }

    // Game over handling
    public void checkGameOver(PetRock rock) {
        if (rock.isEnergyDepleted()) {
            System.out.println("Your rock has crumbled into gravel! Game over.");
        } else if (rock.isHungerOrBoredomMaxed()) {
            System.out.println("Your rock has rolled away in protest! Game over.");
        }
    }
}
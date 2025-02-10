package petrock;

import java.util.Scanner;

public class PetRockView implements Observer {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void update(String event) {
        switch (event) {
            case "displayMenu":
                displayMenu();
                break;
            case "displayStatus":
                displayStatus();
                break;
            case "displayFeed":
                displayFeed();
                break;
            case "displayPlay":
                displayPlay();
                break;
            case "displayPolish":
                displayPolish();
                break;
            case "displayQuitGame":
                displayQuitGame();
                break;
            case "displayInvalidChoice":
                displayInvalidChoice();
                break;
            case "displayGameOver":
                displayGameOver();
                break;
            case "displayShinyPebbleEvent":
                displayShinyPebbleEvent();
                break;
            case "displayExtraSleepEvent":
                displayExtraSleepEvent();
                break;
            case "displaySuddenNoiseEvent":
                displaySuddenNoiseEvent();
                break;
            case "displayGrumpyEvent":
                displayGrumpyEvent();
                break;
            default:
                if (event.startsWith("displayMessage:")) {
                    displayMessage(event.substring("displayMessage:".length()));
                }
                break;
        }
    }

    // Display the main menu
    public void displayMenu() {
        System.out.println("\n--- Pet Rock Management ---");
        System.out.println("1. Feed the Rock");
        System.out.println("2. Play with the Rock");
        System.out.println("3. Polish the Rock");
        System.out.println("4. Check the Rock's Status");
        System.out.println("5. Quit");
    }

    // Get the user's choice
    public String getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return String.valueOf(scanner.nextInt());
    }

    // Display the rock's status
    public void displayStatus() {
        System.out.println("\n--- Rock Status ---");
        System.out.println("Name: " + rock.getName());
        System.out.println("Mood: " + rock.getMood());
        System.out.println("Hunger: " + rock.getHunger());
        System.out.println("Boredom: " + rock.getBoredom());
        System.out.println("Energy: " + rock.getEnergy());
    }

    // Display messages
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayGameOver() {
        System.out.println("Game over! Your rock has rolled away.");
    }

    public void displayQuitGame() {
        System.out.println("Goodbye!");
    }

    public void displayInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }

    public void displayFeed() {
        System.out.println("You fed the rock. Hunger decreased, boredom increased.");
    }

    public void displayPlay() {
        System.out.println("You played with the rock. Boredom decreased, hunger increased.");
    }

    public void displayPolish() {
        System.out.println("You polished the rock. Hunger and boredom decreased, energy restored.");
    }

    // Display random event messages
    public void displayShinyPebbleEvent() {
        System.out.println("Your rock found a shiny pebble! It’s happier now!");
    }

    public void displayExtraSleepEvent() {
        System.out.println("Your rock got some extra sleep! Energy restored!");
    }

    public void displaySuddenNoiseEvent() {
        System.out.println("Your rock is scared by a sudden noise! Boredom increased!");
    }

    public void displayGrumpyEvent() {
        System.out.println("Your rock is grumpy today. Hunger increased!");
    }
}
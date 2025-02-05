package petrock;

import java.util.Scanner;

public class PetRockView {
    private Scanner scanner = new Scanner(System.in);

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
    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    // Display the rock's status
    public void displayStatus(PetRockModel rock) {
        System.out.println("\n--- Rock Status ---");
        System.out.println("Name: " + rock.getName());
        System.out.println("Mood: " + rock.getMood());
        System.out.println("Hunger: " + rock.getHunger());
        System.out.println("Boredom: " + rock.getBoredom());
        System.out.println("Energy: " + rock.getEnergy());
    }

    // Display a message
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
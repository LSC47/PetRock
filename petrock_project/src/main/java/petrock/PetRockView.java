package petrock;

import java.util.Scanner;

public class PetRockView {
    private Scanner scanner;

    public PetRockView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n1. Feed your rock");
        System.out.println("2. Play with your rock");
        System.out.println("3. Polish your rock");
        System.out.println("4. Check rock status");
        System.out.println("5. Exit");
        System.out.print("Choose an action: ");
    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

    public void showStatus(PetRock petRock) {
        System.out.println("\n" + petRock.getName() + "'s Status:");
        System.out.println("Mood: " + petRock.getMood());
        System.out.println("Hunger: " + petRock.getHunger());
        System.out.println("Boredom: " + petRock.getBoredom());
        System.out.println("Energy: " + petRock.getEnergy());
        System.out.println("Last Meal: " + petRock.getLastMeal());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

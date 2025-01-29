package petrock;

import java.util.Scanner;

import java.util.Scanner;
public class PetRock {
 

   Scanner scanner = new Scanner(System.in);


   while(true){
   
        System.out.println("\nMenu:");
        System.out.println("1. Add Player");
        System.out.println("2. Update Player Score");
        System.out.println("3. Remove Player");
        System.out.println("4. View Players");
        System.out.println("q. Quit");
        System.out.print("Enter your choice: ");

        // Read user input
       String choice = scanner.nextLine();

        if (choice.equals("q")) {
            System.out.println("Exiting program.");
            break;  // Exit the loop and the program
        }
    }

}

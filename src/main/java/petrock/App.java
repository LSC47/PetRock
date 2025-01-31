package petrock;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class App {
    private static final String SAVE_FILE = "rock.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveRock(PetRock rock) {
        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(rock, writer);
        } catch (IOException e) {
            System.out.println("Error saving rock data.");
        }
    }

    public static PetRock loadRock() {
        try (Reader reader = new FileReader(SAVE_FILE)) {
            return gson.fromJson(reader, PetRock.class);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Error loading rock data.");
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetRock rock = loadRock();

        if (rock == null) {
            System.out.print("Enter your pet rock's name: ");
            String name = scanner.nextLine();
            rock = new PetRock(name);
        }

        while (true) {
            rock.status();
            System.out.println("\nMenu:");
            System.out.println("1. Feed");
            System.out.println("2. Play");
            System.out.println("3. Polish");
            System.out.println("4. Check Status");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    rock.feedTheRock();
                    break;
                case 2:
                    rock.playWithTheRock();
                    break;
                case 3:
                    rock.polishTheRock();
                    break;
                case 4:
                    rock.status();
                    break;
                case 5:
                    saveRock(rock);
                    System.out.println("Game saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            rock.randomEvent();

            if (rock.isGameOver()) {
                System.out.println("Your rock has rolled away in protest! Game over.");
                break;
            }

            saveRock(rock);
        }

        scanner.close();
    }
}

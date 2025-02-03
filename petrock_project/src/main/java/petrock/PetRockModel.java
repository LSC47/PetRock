package petrock;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PetRockModel {

    // Saves the PetRock state to a JSON file
    public void saveState(PetRock rock, String filePath) {
        JSONObject state = rock.toJson(); // Convert PetRock to JSON

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(state.toString()); // Write JSON to file
            System.out.println("Rock state saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving rock state: " + e.getMessage());
        }
    }

    // Loads the PetRock state from a JSON file
    public PetRock loadState(String filePath) {
        try {
            // Read the file content
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject state = new JSONObject(content); // Parse JSON from file

            // Create a new PetRock from the JSON data
            return PetRock.fromJson(state);
        } catch (IOException e) {
            System.out.println("Error loading rock state: " + e.getMessage());
            return null; // Return null if the file cannot be loaded
        }
    }
}
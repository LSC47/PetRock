package petrock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PetRockModel {
    private String name;
    private String mood;
    private int hunger;
    private int boredom;
    private int energy;
    private int polishCount;
    private String lastMeal;
    private boolean feedCooldown;
    private boolean playCooldown;

    private static final String FILE_NAME = "PetRock.json";


    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Constructors
    public PetRockModel() {
        this("Unknown");
    }

    public PetRockModel(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.mood = "Neutral";
        this.hunger = 0;
        this.boredom = 0;
        this.energy = 10;
        this.polishCount = 0;
        this.lastMeal = "None";
        this.feedCooldown = false;
        this.playCooldown = false;
    }

// Convert the object to JSON
public String toJson() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(this);
}

// Save JSON to a file
public void saveToFile() {
    try (FileWriter writer = new FileWriter(FILE_NAME)) {
        writer.write(this.toJson());
        System.out.println("Game saved to " + FILE_NAME);
    } catch (IOException e) {
        System.err.println("Error saving game: " + e.getMessage());
    }
}

// Load from JSON file
public static PetRockModel loadFromFile() {
    File file = new File(FILE_NAME);
    if (!file.exists()) {
        System.out.println("No saved game found. Starting new game.");
        return new PetRockModel("Pebbles"); // Default name if no file exists
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        Gson gson = new Gson();
        return gson.fromJson(reader, PetRockModel.class);
    } catch (IOException e) {
        System.err.println("Error loading game: " + e.getMessage());
        return new PetRockModel("Pebbles"); // Default instance if load fails
    }
}


    public static PetRockModel fromJson(String json) {
        return gson.fromJson(json, PetRockModel.class);
    }

    // Getters
    public String getName() { return name; }
    public String getMood() { return mood; }
    public int getHunger() { return hunger; }
    public int getBoredom() { return boredom; }
    public int getEnergy() { return energy; }
    public int getPolishCount() { return polishCount; }
    public String getLastMeal() { return lastMeal; }

    // Setters
    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        this.name = name;
    }

    public void setMood(String mood) { this.mood = mood; }

    public void setHunger(int hunger) {
        if (hunger < 0 || hunger > 10)
            throw new IllegalArgumentException("Hunger must be between 0 and 10");
        this.hunger = hunger;
    }

    public void setBoredom(int boredom) {
        if (boredom < 0 || boredom > 10)
            throw new IllegalArgumentException("Boredom must be between 0 and 10");
        this.boredom = boredom;
    }

    public void setEnergy(int energy) {
        if (energy < 0 || energy > 10)
            throw new IllegalArgumentException("Energy must be between 0 and 10");
        this.energy = energy;
    }

    public void setPolishCount(int polishCount) {
        if (polishCount < 0)
            throw new IllegalArgumentException("Polish count cannot be negative");
        this.polishCount = polishCount;
    }

    public void setLastMeal(String lastMeal) { this.lastMeal = lastMeal; }

    // Actions
    public void feed() {
        if (feedCooldown) throw new IllegalStateException("You cannot feed the rock again so soon!");

        this.hunger = Math.max(this.hunger - 2, 0);
        this.boredom = Math.min(this.boredom + 1, 10);
        this.energy = Math.max(this.energy - 1, 0);
        this.lastMeal = "nom nom";
        this.feedCooldown = true;

        updateMood();
    }

    public void play() {
        if (playCooldown) throw new IllegalStateException("You cannot play with the rock again so soon!");

        this.boredom = Math.max(this.boredom - 3, 0);
        this.hunger = Math.min(this.hunger + 1, 10);
        this.energy = Math.max(this.energy - 2, 0);
        this.playCooldown = true;

        updateMood();
    }

    public void polish() {
        this.hunger = Math.max(this.hunger - (polishCount < 3 ? 1 : 0), 0);
        this.boredom = Math.max(this.boredom - 1, 0);
        this.energy = Math.min(this.energy + 1, 10);
        this.polishCount++;

        updateMood();
    }

    // Mood and State Management
    public void updateMood() {
        if (this.energy <= 2) {
            this.mood = "Tired";
        } else if (this.hunger > 7 || this.boredom > 7 || this.energy <= 3) {
            this.mood = "Sad";
        } else if (this.hunger >= 4 || this.boredom >= 4) {
            this.mood = "Bored";
        } else {
            this.mood = "Happy";
        }
    }

    public void increaseHungerAndBoredom() {
        this.hunger = Math.min(this.hunger + 1, 10);
        this.boredom = Math.min(this.boredom + 1, 10);
        updateMood();
    }

    public void restoreEnergy() {
        this.energy = Math.min(this.energy + 1, 10);
        updateMood();
    }

    public void resetCooldowns() {
        this.feedCooldown = false;
        this.playCooldown = false;
    }

    // Validation Methods
    public boolean isEnergyDepleted() { return this.energy == 0; }
    public boolean isHungerOrBoredomMaxed() { return this.hunger == 10 || this.boredom == 10; }
    public boolean isHappy() { return "Happy".equals(this.mood); }
}

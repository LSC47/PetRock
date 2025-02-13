package petrock;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PetRockModel {
    private String name;
    private String mood;
    private int hunger;
    private int boredom;
    private int energy;
    private int polishCount;
    private String lastMeal;
    private boolean feedCooldown = false;
    private boolean playCooldown = false;

    // Constructors
    public PetRockModel() {
        this.lastMeal = "None";
    }

    public PetRockModel(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
        this.mood = "None";
        this.hunger = 0;
        this.boredom = 0;
        this.energy = 10;
        this.polishCount = 0;
        this.lastMeal = "None";
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getMood() {
        return mood;
    }

    public int getHunger() {
        return hunger;
    }

    public int getBoredom() {
        return boredom;
    }

    public int getEnergy() {
        return energy;
    }

    public int getPolishCount() {
        return polishCount;
    }

    public String getLastMeal() {
        return lastMeal;
    }

    public boolean isFeedCooldown() {
        return feedCooldown;
    }

    public boolean isPlayCooldown() {
        return playCooldown;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setHunger(int hunger) {
        if (hunger < 0 || hunger > 10) {
            throw new IllegalArgumentException("Hunger must be between 0 and 10");
        }
        this.hunger = hunger;
    }

    public void setBoredom(int boredom) {
        if (boredom < 0 || boredom > 10) {
            throw new IllegalArgumentException("Boredom must be between 0 and 10");
        }
        this.boredom = boredom;
    }

    public void setEnergy(int energy) {
        if (energy < 0 || energy > 10) {
            throw new IllegalArgumentException("Energy must be between 0 and 10");
        }
        this.energy = energy;
    }

    public void setPolishCount(int polishCount) {
        if (polishCount < 0) {
            throw new IllegalArgumentException("Polish count cannot be negative");
        }
        this.polishCount = polishCount;
    }

    public void setLastMeal(String lastMeal) {
        this.lastMeal = lastMeal;
    }

    public void setFeedCooldown(boolean feedCooldown) {
        this.feedCooldown = feedCooldown;
    }

    public void setPlayCooldown(boolean playCooldown) {
        this.playCooldown = playCooldown;
    }

    // Method to save the current state to a file using Gson
    public void saveToFile(String filename) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static method to load a PetRockModel from a file using Gson
    public static PetRockModel loadFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, PetRockModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
package petrock;

import org.json.JSONObject;

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

    // JSON serialization (for use by PetRockRepository)
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("mood", this.mood);
        json.put("hunger", this.hunger);
        json.put("boredom", this.boredom);
        json.put("energy", this.energy);
        json.put("lastMeal", this.lastMeal);
        json.put("polishCount", this.polishCount);
        return json;
    }

    public static PetRockModel fromJson(JSONObject json) {
        PetRockModel rock = new PetRockModel(json.getString("name"));
        rock.setMood(json.getString("mood"));
        rock.setHunger(json.getInt("hunger"));
        rock.setBoredom(json.getInt("boredom"));
        rock.setEnergy(json.getInt("energy"));
        rock.setLastMeal(json.getString("lastMeal"));
        rock.setPolishCount(json.getInt("polishCount"));
        return rock;
    }
}
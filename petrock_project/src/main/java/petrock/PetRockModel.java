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

    // Action methods
    public void feed() {
        if (feedCooldown) {
            throw new IllegalStateException("You cannot feed the rock again so soon!");
        }
        this.hunger = Math.max(this.hunger - 2, 0);
        this.boredom = Math.min(this.boredom + 1, 10);
        this.energy = Math.max(this.energy - 1, 0);
        this.lastMeal = "nom nom";
        this.feedCooldown = true;
        updateMood();
    }

    public void play() {
        if (playCooldown) {
            throw new IllegalStateException("You cannot play with the rock again so soon!");
        }
        this.boredom = Math.max(this.boredom - 3, 0);
        this.hunger = Math.min(this.hunger + 1, 10);
        this.energy = Math.max(this.energy - 2, 0);
        this.playCooldown = true;
        updateMood();
    }

    public void polish() {
        if (polishCount < 3) {
            this.hunger = Math.max(this.hunger - 1, 0);
            this.boredom = Math.max(this.boredom - 1, 0);
            this.energy = Math.min(this.energy + 1, 10);
        } else if (polishCount < 6) {
            this.hunger = Math.max(this.hunger - 0, 0);
            this.boredom = Math.max(this.boredom - 1, 0);
            this.energy = Math.min(this.energy + 1, 10);
        } else {
            this.hunger = Math.max(this.hunger - 0, 0);
            this.boredom = Math.max(this.boredom - 0, 0);
            this.energy = Math.min(this.energy + 1, 10);
        }
        this.mood = "Happy";
        this.polishCount++;
        updateMood();
    }

    // State management
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

    // Cooldown management
    public void resetCooldowns() {
        this.feedCooldown = false;
        this.playCooldown = false;
    }

    // Validation methods
    public boolean isEnergyDepleted() {
        return this.energy == 0;
    }

    public boolean isHungerOrBoredomMaxed() {
        return this.hunger == 10 || this.boredom == 10;
    }

    public boolean isHappy() {
        return this.getMood().equals("Happy");
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
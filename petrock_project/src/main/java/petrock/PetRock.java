package petrock;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class PetRock {
	private String name;
	private String mood;
	private int hunger;
	private int boredom;
	private int energy;
	private int polishCount; // Tracks how many times the rock has been polished (for diminishing returns)

	// Constructors
	public PetRock() {
	}

	public PetRock(String n) {
		name = n;
		mood = "Happy";
		hunger = 0;
		boredom = 0;
		energy = 10;
		polishCount = 0;
	}

	// Getters
	public String getName() {
		return this.name;
	}

	public String getMood() {
		return this.mood;
	}

	public int getHunger() {
		return this.hunger;
	}

	public int getBoredom() {
		return this.boredom;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getPolishCount() {
		return this.polishCount;
	}

	// Setters
	public void setName(String n) {
		this.name = n;
	}

	public void setMood(String m) {
		this.mood = m;
	}

	public void setHunger(int h) {
		this.hunger = h;
	}

	public void setBoredom(int b) {
		this.boredom = b;
	}

	public void setEnergy(int e) {
		this.energy = e;
	}

	public void setPolishCount(int p) {
		this.polishCount = p;
	}

	// Action methods
	public void feed() { // Reduces hunger by 2, increases boredom by 1, and costs 1 energy.
		this.hunger = Math.max(this.hunger - 2, 0); // Ensure hunger doesn't go below 0
		this.boredom = Math.min(this.boredom + 1, 10); // Ensure boredom doesn't exceed 10
		this.energy = Math.max(this.energy - 1, 0); // Ensure energy doesn't go below 0
		updateMood(); // Update mood after feeding
	}

	public void play() { // Reduces boredom by 3, increases hunger by 1, and costs 2 energy.
		this.boredom = Math.max(this.boredom - 3, 0); 
		this.hunger = Math.min(this.hunger + 1, 10); 
		this.energy = Math.max(this.energy - 2, 0); 
		updateMood(); 
	}

	public void polish() { // Reduces hunger and boredom by 1, restores 1 energy, and sets mood to "Happy".
							// Implements diminishing returns if overused.
		// Implements diminishing returns if overused
		if (polishCount < 3) { // Full effect for the first 3 polishes
			this.hunger = Math.max(this.hunger - 1, 0);
			this.boredom = Math.max(this.boredom - 1, 0);
			this.energy = Math.min(this.energy + 1, 10);
		}
		else if (polishCount < 6){ // Diminishing returns after 3 polishes
			this.hunger = Math.max(this.hunger - 0, 0); // No effect on hunger
			this.boredom = Math.max(this.boredom - 1, 0); // Still restores boredom
			this.energy = Math.min(this.energy + 1, 10); // Still restores energy
		}
		else { // Diminishing returns after 6 polishes
			this.hunger = Math.max(this.hunger - 0, 0); // No effect on hunger
			this.boredom = Math.max(this.boredom - 0, 0); // No effect on boredom
			this.energy = Math.min(this.energy + 1, 10); // Still restores energy
		}
		this.mood = "Happy"; // Always sets mood to "Happy"
		this.polishCount++; // Increment polish count
		updateMood(); // Update mood after polishing
	}

	// State management
	public void updateMood() { // Dynamically calculates the mood based on hunger, boredom, and energy levels.
		// Dynamically calculates the mood based on hunger, boredom, and energy levels
		if (this.energy <= 2) {
			this.mood = "Tired";
		} 
		else if (this.hunger > 7 || this.boredom > 7 || this.energy <= 3) {
			this.mood = "Sad";
		} 
		else if (this.hunger >= 4 || this.boredom >= 4) {
			this.mood = "Bored";
		} 
		else {
			this.mood = "Happy";
		}
	}

	public void increaseHungerAndBoredom() { // Increases hunger and boredom by 1 (called after each turn).
		this.hunger = Math.min(this.hunger + 1, 10);
		this.boredom = Math.min(this.boredom + 1, 10);
		updateMood();
	}

	public void restoreEnergy() { // Restores 1 energy if no action is taken during a turn.
		this.energy = Math.min(this.energy + 1, 10);
		updateMood();
	}

	// Validation methods
	public boolean isEnergyDepleted() { // Returns true if energy is 0.
		return this.energy == 0;
	}

	public boolean isHungerOrBoredomMaxed() { // Returns true if hunger or boredom reaches 10.
		return this.hunger == 10 || this.boredom == 10;
	}

	// File persistance
	public void saveState(String filePath) { // Saves the rock's state to a JSON file.
		JSONObject state = new JSONObject();
		state.put("name", this.name);
		state.put("mood", this.mood);
		state.put("hunger", this.hunger);
		state.put("boredom", this.boredom);
		state.put("energy", this.energy);

		try (FileWriter file = new FileWriter(filePath)) {
			file.write(state.toString());
			System.out.println("Rock state saved to " + filePath);
		} catch (IOException e) {
			System.out.println("Error saving rock state: " + e.getMessage());
		}

	}

	public void loadState(String filePath) { // Loads the rock's state from a JSON file.
		try {
			String content = new String(Files.readAllBytes(Paths.get(filePath)));
			JSONObject state = new JSONObject(content);
			this.name = state.getString("name");
			this.mood = state.getString("mood");
			this.hunger = state.getInt("hunger");
			this.boredom = state.getInt("boredom");
			this.energy = state.getInt("energy");
			System.out.println("Rock state loaded from " + filePath);
		} catch (IOException e) {
			System.out.println("Error loading rock state: " + e.getMessage());
		}
	}
}

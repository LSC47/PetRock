package petrock;

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
		this.boredom = Math.max(this.boredom - 3, 0); // Ensure boredom doesn't go below 0
		this.hunger = Math.min(this.hunger + 1, 10); // Ensure hunger doesn't exceed 10
		this.energy = Math.max(this.energy - 2, 0); // Ensure energy doesn't go below 0
		updateMood(); // Update mood after playing
	}

	public void polish() { // Reduces hunger and boredom by 1, restores 1 energy, and sets mood to "Happy".
							// Implements diminishing returns if overused.
		// Implements diminishing returns if overused
		if (polishCount < 3) { // Full effect for the first 3 polishes
			this.hunger = Math.max(this.hunger - 1, 0);
			this.boredom = Math.max(this.boredom - 1, 0);
			this.energy = Math.min(this.energy + 1, 10);
		} 
		else { // Diminishing returns after 3 polishes
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

	}

	public void increaseHungerAndBoredom() { // Increases hunger and boredom by 1 (called after each turn).

	}

	public void restoreEnergy() { // Restores 1 energy if no action is taken during a turn.

	}

	// Validation methods
	public boolean isEnergyDepleted() { // Returns true if energy is 0.

		return false;
	}

	public boolean isHungerOrBoredomMaxed() { // Returns true if hunger or boredom reaches 10.

		return false;
	}

	// File persistance
	public void saveState(String filePath) { // Saves the rock's state to a JSON file.

	}

	public void loadState(String filePath) { // Loads the rock's state from a JSON file.

	}

}

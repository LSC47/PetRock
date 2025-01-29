package petrock;

public class PetRock {
  private String name;
  private String mood;
  private int hunger;
  private int boredom;
  private int energy;


  // Constructors
  public PetRock(){}

  public PetRock(String n, String m, int h, int b, int e){
    name = n;
    mood = m;
    hunger = h;
    boredom = b;
    energy = e; 
  }

  // Getters
  public String getName(){
    return this.name;
  }

  public String getMood(){
    return this.mood;
  }

  public int getHunger(){
    return this.hunger;
  }

  public int getBoredom(){
    return this.boredom;
  }

  public int getEnergy(){
    return this.energy;
  }

  // Setters
  public void setName(String n){
    this.name = n;
  }

  public void setMood(String m){
    this.mood = m;
  }

  public void setHunger(int h){
    this.hunger = h;
  }

  public void setBoredom(int b){
    this.boredom = b;
  }

  public void setEnergy(int e){
    this.energy = e;
  }


  // Action methods
  public void feed(){ // Reduces hunger by 2, increases boredom by 1, and costs 1 energy.

  }

  public void play(){ // Reduces boredom by 3, increases hunger by 1, and costs 2 energy.

  }

  public void polish(){ // Reduces hunger and boredom by 1, restores 1 energy, and sets mood to "Happy". Implements diminishing returns if overused.

  }


  // State management
  public void updateMood(){ // Dynamically calculates the mood based on hunger, boredom, and energy levels.

  }

  public void increaseHungerAndBoredom(){ // Increases hunger and boredom by 1 (called after each turn).

  }

  public void restoreEnergy(){ // Restores 1 energy if no action is taken during a turn.

  }

  // Validation methods
  public boolean isEnergyDepleted(){ // Returns true if energy is 0.

    return false;
  }

  public boolean isHungerOrBoredomMaxed(){ // Returns true if hunger or boredom reaches 10.

    return false;
  }


  // File persistance
  public void saveState(String filePath){ // Saves the rock's state to a JSON file.

  }

  public void loadState(String filePath){ // Loads the rock's state from a JSON file.

  }



  
}

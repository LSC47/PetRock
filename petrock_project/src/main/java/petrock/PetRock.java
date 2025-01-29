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
    return name;
  }

  public String getMood(){
    return mood;
  }

  public int getHunger(){
    return hunger;
  }

  public int getBoredom(){
    return boredom;
  }

  public int getEnergy(){
    return energy;
  }

  // Setters
  public void setName(String n){
    name = n;
  }

  public void setMood(String m){
    mood = m;
  }

  public void setHunger(int h){
    hunger = h;
  }

  public void setBoredom(int b){
    boredom = b;
  }

  public void setEnergy(int e){
    energy = e;
  }


  // Extra methods
  public void feedPetRock(){

  }

  public void playWithPetRock(){

  }

  public void polishTheRock(){

  }

  public void updateMood(){
    
  }


  
}

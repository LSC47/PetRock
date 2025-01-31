package petrock;
import java.util.Random;


class PetRock {
  private String name;
  private String mood;
  private int hunger;
  private int boredom;
  private int energy;
  private boolean canFeed = true;
  private boolean canPlay = true;

  public PetRock(String name) {
      this.name = name;
      this.hunger = 3;
      this.boredom = 2;
      this.energy = 5;
      updateMood();
  }

  public void feedTheRock() {
      if (!canFeed) {
          System.out.println(name + " is not ready to eat again yet.");
          return;
      }
      hunger = Math.max(0, hunger - 2);
      boredom = Math.min(10, boredom + 1);
      energy = Math.max(0, energy - 1);
      canFeed = false;
      canPlay = true;
      updateMood();
  }

  public void playWithTheRock() {
      if (!canPlay) {
          System.out.println(name + " is too tired to play right now.");
          return;
      }
      boredom = Math.max(0, boredom - 3);
      hunger = Math.min(10, hunger + 1);
      energy = Math.max(0, energy - 2);
      canPlay = false;
      canFeed = true;
      updateMood();
  }

  public void polishTheRock() {
      hunger = Math.max(0, hunger - 1);
      boredom = Math.max(0, boredom - 1);
      energy = Math.min(10, energy + 1);
      mood = "Happy";
      updateMood();
  }

  public void rest() {
    energy = Math.min(10, energy + 1);
    System.out.println(name + " is resting and regaining energy.");
    updateMood();
}

  public void updateMood() {
      if (energy <= 2) {
          mood = "Tired";
      } else if (hunger > 7 || boredom > 7 || energy <= 3) {
          mood = "Sad";
      } else if (hunger >= 4 || boredom >= 4) {
          mood = "Bored";
      } else {
          mood = "Happy";
      }
  }

  public void randomEvent() {
      Random rand = new Random();
      int event = rand.nextInt(4);
      switch (event) {
        case 0:
            System.out.println(name + " found a shiny pebble! Its happier now!");
            break;
        case 1:
            System.out.println(name + " got some extra sleep! Energy restored!");
            energy = Math.min(10, energy + 1);
            break;
        case 2:
            System.out.println(name + " was scared by a sudden noise! Boredom increased!");
            boredom = Math.min(10, boredom + 2);
            break;
        case 3:
            System.out.println(name + " is grumpy today. Hunger increased!");
            hunger = Math.min(10, hunger + 2);
            break;
    }
    
      updateMood();
  }

  public void status() {
      System.out.printf("%s's Status: Mood: %s, Hunger: %d, Boredom: %d, Energy: %d\n",
              name, mood, hunger, boredom, energy);
  }

  public boolean isGameOver() {
      return hunger >= 10 || boredom >= 10 || energy == 0;
  
    }
    public void advanceTurn(boolean tookAction) {
      hunger = Math.min(10, hunger + 1);
      boredom = Math.min(10, boredom + 1);
      if (!tookAction) {
          rest();
      }
      updateMood();
  }
}

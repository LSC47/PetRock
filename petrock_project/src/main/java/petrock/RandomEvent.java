package petrock;

import java.util.Random;

public class RandomEvent {
  private Random random = new Random(); // Random number generator

  // Event trigger
  public void triggerEvent(PetRock rock) { // Randomly select an event (20% chance of an event occurring)
    if (random.nextInt(100) < 20) { // 20% chance
      int eventType = random.nextInt(4); // Randomly select an event type (0-3)
      switch (eventType) {
        case 0:
          shinyPebbleEvent(rock);
          break;
        case 1:
          extraSleepEvent(rock);
          break;
        case 2:
          suddenNoiseEvent(rock);
          break;
        case 3:
          grumpyEvent(rock);
          break;
      }
    }
  }

  public void shinyPebbleEvent(PetRock rock) { // Positive event: Improves the rock's mood
    System.out.println("Your rock found a shiny pebble! It’s happier now!");
    rock.setMood("Happy"); // Set mood to "Happy"
    rock.updateMood(); // Update mood after the event
  }

  public void extraSleepEvent(PetRock rock) { // Positive event: Restores the rock's energy
    System.out.println("Your rock got some extra sleep! Energy restored!");
    int currentEnergy = rock.getEnergy();
    rock.setEnergy(Math.min(currentEnergy + 2, 10)); // Restore 2 energy (max 10)
    rock.updateMood();
  }

  public void suddenNoiseEvent(PetRock rock) { // Negative event: Increases the rock's boredom
    System.out.println("Your rock is scared by a sudden noise! Boredom increased!");
    int currentBoredom = rock.getBoredom();
    rock.setBoredom(Math.min(currentBoredom + 2, 10)); // Increase boredom by 2 (max 10)
    rock.updateMood();
  }

  public void grumpyEvent(PetRock rock) { // Negative event: Increases the rock's hunger
    System.out.println("Your rock is grumpy today. Hunger increased!");
    int currentHunger = rock.getHunger();
    rock.setHunger(Math.min(currentHunger + 2, 10)); // Increase hunger by 2 (max 10)
    rock.updateMood();
  }
}
package petrock;

import java.util.Random;

public class RandomEvent {
  private Random random = new Random();
  public PetRockView view = new PetRockView();

  // Trigger a random event
  public boolean triggerEvent(PetRockModel rock) {
    boolean didRandomEventHappen = false;
    if (random.nextInt(100) < 50) { // 50% chance
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
      didRandomEventHappen = true;
    }
    return didRandomEventHappen;

  }

  private void shinyPebbleEvent(PetRockModel rock) {
    rock.setMood("Happy");
    view.displayShinyPebbleEvent();
    rock.updateMood();
  }

  private void extraSleepEvent(PetRockModel rock) {
    rock.setEnergy(Math.min(rock.getEnergy() + 2, 10));
    view.displayExtraSleepEvent();
    rock.updateMood();
  }

  private void suddenNoiseEvent(PetRockModel rock) {
    rock.setBoredom(Math.min(rock.getBoredom() + 2, 10));
    view.displaySuddenNoiseEvent();
    rock.updateMood();
  }

  private void grumpyEvent(PetRockModel rock) {
    rock.setHunger(Math.min(rock.getHunger() + 2, 10));
    view.displayGrumpyEvent();
    rock.updateMood();
  }
}
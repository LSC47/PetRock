package petrock;

import java.util.Random;

public class PetRockController extends Observable {
    private PetRockModel rock;
    private Random random = new Random();

    public PetRockController(PetRockModel rock) {
        this.rock = rock;
    }

    // Run the game loop
    public void runGameLoop() {
        while (true) {
            notifyObservers("displayMenu");
            int choice = Integer.parseInt(notifyObserversAndGetResponse("getUserChoice"));

            switch (choice) {
                case 1:
                    handleFeed();
                    break;
                case 2:
                    handlePlay();
                    break;
                case 3:
                    handlePolish();
                    break;
                case 4:
                    notifyObservers("displayStatus");
                    continue; // Skip the rest of the loop and show the menu again
                case 5:
                    notifyObservers("displayQuitGame");
                    return; // Exit the game
                default:
                    notifyObservers("displayInvalidChoice");
                    continue; // Skip the rest of the loop and show the menu again
            }

            // Trigger a random event after each action
            triggerRandomEvent();

            // Update the rock's state for the next turn
            increaseHungerAndBoredom();
            restoreEnergy();
            updateMood();

            // Reset cooldowns
            rock.setFeedCooldown(false);
            rock.setPlayCooldown(false);

            // Check for game over conditions
            if (isEnergyDepleted() || isHungerOrBoredomMaxed()) {
                notifyObservers("displayGameOver");
                break; // End the game loop
            }
        }
    }

    // Handle the feed action
    public void handleFeed() {
        if (rock.isFeedCooldown()) {
            notifyObservers("displayMessage:You cannot feed the rock again so soon!");
            return;
        }
        rock.setHunger(Math.max(rock.getHunger() - 2, 0));
        rock.setBoredom(Math.min(rock.getBoredom() + 1, 10));
        rock.setEnergy(Math.max(rock.getEnergy() - 1, 0));
        rock.setLastMeal("nom nom");
        rock.setFeedCooldown(true);
        notifyObservers("displayFeed");
    }

    // Handle the play action
    public void handlePlay() {
        if (rock.isPlayCooldown()) {
            notifyObservers("displayMessage:You cannot play with the rock again so soon!");
            return;
        }
        rock.setBoredom(Math.max(rock.getBoredom() - 3, 0));
        rock.setHunger(Math.min(rock.getHunger() + 1, 10));
        rock.setEnergy(Math.max(rock.getEnergy() - 2, 0));
        rock.setPlayCooldown(true);
        notifyObservers("displayPlay");
    }

    // Handle the polish action
    public void handlePolish() {
        if (rock.getPolishCount() < 3) {
            rock.setHunger(Math.max(rock.getHunger() - 1, 0));
            rock.setBoredom(Math.max(rock.getBoredom() - 1, 0));
            rock.setEnergy(Math.min(rock.getEnergy() + 1, 10));
        } else if (rock.getPolishCount() < 6) {
            rock.setBoredom(Math.max(rock.getBoredom() - 1, 0));
            rock.setEnergy(Math.min(rock.getEnergy() + 1, 10));
        } else {
            rock.setEnergy(Math.min(rock.getEnergy() + 1, 10));
        }
        rock.setPolishCount(rock.getPolishCount() + 1);
        notifyObservers("displayPolish");
    }

    // Trigger a random event
    private void triggerRandomEvent() {
        if (random.nextInt(100) < 50) { // 50% chance
            int eventType = random.nextInt(4); // Randomly select an event type (0-3)
            switch (eventType) {
                case 0:
                    shinyPebbleEvent();
                    break;
                case 1:
                    extraSleepEvent();
                    break;
                case 2:
                    suddenNoiseEvent();
                    break;
                case 3:
                    grumpyEvent();
                    break;
            }
        }
    }

    private void shinyPebbleEvent() {
        rock.setMood("Happy");
        notifyObservers("displayShinyPebbleEvent");
    }

    private void extraSleepEvent() {
        rock.setEnergy(Math.min(rock.getEnergy() + 2, 10));
        notifyObservers("displayExtraSleepEvent");
    }

    private void suddenNoiseEvent() {
        rock.setBoredom(Math.min(rock.getBoredom() + 2, 10));
        notifyObservers("displaySuddenNoiseEvent");
    }

    private void grumpyEvent() {
        rock.setHunger(Math.min(rock.getHunger() + 2, 10));
        notifyObservers("displayGrumpyEvent");
    }

    // Update the rock's mood
    private void updateMood() {
        if (rock.getEnergy() <= 2) {
            rock.setMood("Tired");
        } else if (rock.getHunger() > 7 || rock.getBoredom() > 7 || rock.getEnergy() <= 3) {
            rock.setMood("Sad");
        } else if (rock.getHunger() >= 4 || rock.getBoredom() >= 4) {
            rock.setMood("Bored");
        } else {
            rock.setMood("Happy");
        }
    }

    // Increase hunger and boredom
    private void increaseHungerAndBoredom() {
        rock.setHunger(Math.min(rock.getHunger() + 1, 10));
        rock.setBoredom(Math.min(rock.getBoredom() + 1, 10));
    }

    // Restore energy
    private void restoreEnergy() {
        rock.setEnergy(Math.min(rock.getEnergy() + 1, 10));
    }

    // Check if energy is depleted
    private boolean isEnergyDepleted() {
        return rock.getEnergy() == 0;
    }

    // Check if hunger or boredom is maxed
    private boolean isHungerOrBoredomMaxed() {
        return rock.getHunger() == 10 || rock.getBoredom() == 10;
    }
}
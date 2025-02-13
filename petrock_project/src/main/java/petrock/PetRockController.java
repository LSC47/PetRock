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
            notifyObservers("displayMenu", rock); // Pass the rock object

            // Get user choice from the observer (PetRockView)
            int choice = Integer.parseInt(((PetRockView) getObservers().get(0)).getUserChoice());

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
                    notifyObservers("displayStatus", rock); // Pass the rock object
                    continue; // Skip the rest of the loop and show the menu again
                case 5:
                    notifyObservers("displayQuitGame", rock); // Pass the rock object
                    return; // Exit the game
                default:
                    notifyObservers("displayInvalidChoice", rock); // Pass the rock object
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
                notifyObservers("displayGameOver", rock); // Pass the rock object
                break; // End the game loop
            }
        }
    }

    public void handlePlay() {
        if (rock.isPlayCooldown()) {
            throw new IllegalStateException("You cannot play with the rock again so soon!");
        }

        // Ensure boredom does not go below 0
        rock.setBoredom(Math.max(rock.getBoredom() - 3, 0));

        // Increase hunger by 1, but ensure it does not exceed 10
        rock.setHunger(Math.min(rock.getHunger() + 1, 10));

        // Decrease energy by 2, but ensure it does not go below 0
        rock.setEnergy(Math.max(rock.getEnergy() - 2, 0));

        // Set play cooldown
        rock.setPlayCooldown(true);

        // Update mood
        updateMood();

        // Notify observers
        notifyObservers("displayPlay", rock);
    }

    public void handleFeed() {
        if (rock.isFeedCooldown()) {
            throw new IllegalStateException("You cannot feed the rock again so soon!");
        }

        // Ensure hunger does not go below 0
        rock.setHunger(Math.max(rock.getHunger() - 2, 0));

        // Increase boredom by 1, but ensure it does not exceed 10
        rock.setBoredom(Math.min(rock.getBoredom() + 1, 10));

        // Decrease energy by 1, but ensure it does not go below 0
        rock.setEnergy(Math.max(rock.getEnergy() - 1, 0));

        // Set last meal
        rock.setLastMeal("nom nom");

        // Set feed cooldown
        rock.setFeedCooldown(true);

        // Update mood
        updateMood();

        // Notify observers
        notifyObservers("displayFeed", rock);
    }

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
        updateMood(); // Update the mood after polishing
        notifyObservers("displayPolish", rock);
    }

    // Trigger a random event
    public void triggerRandomEvent() {
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
        notifyObservers("displayShinyPebbleEvent", rock);
    }

    private void extraSleepEvent() {
        rock.setEnergy(Math.min(rock.getEnergy() + 2, 10));
        notifyObservers("displayExtraSleepEvent", rock);
    }

    private void suddenNoiseEvent() {
        rock.setBoredom(Math.min(rock.getBoredom() + 2, 10));
        notifyObservers("displaySuddenNoiseEvent", rock);
    }

    private void grumpyEvent() {
        rock.setHunger(Math.min(rock.getHunger() + 2, 10));
        notifyObservers("displayGrumpyEvent", rock);
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
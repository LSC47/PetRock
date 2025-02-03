package petrock;

import java.util.Random;

public class RandomEvent {
    private Random random;

    public RandomEvent() {
        this.random = new Random();
    }

    public String triggerEvent(PetRock petRock) {
        int eventChance = random.nextInt(100);
        String eventMessage = "";

        if (eventChance < 20) { // 20% chance for a random event
            int eventType = random.nextInt(3);
            switch (eventType) {
                case 0:
                    petRock.setHunger(Math.min(petRock.getHunger() + 2, 10));
                    eventMessage = petRock.getName() + " found some snacks and is less hungry!";
                    break;
                case 1:
                    petRock.setBoredom(Math.max(petRock.getBoredom() - 2, 0));
                    eventMessage = petRock.getName() + " discovered a fun new game and feels less bored!";
                    break;
                case 2:
                    petRock.setEnergy(Math.max(petRock.getEnergy() - 1, 0));
                    eventMessage = petRock.getName() + " had a restless night and feels a bit tired.";
                    break;
            }
        }

        return eventMessage;
    }
}
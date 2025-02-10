package petrock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetRockTest {
    private PetRockModel rock;
    private PetRockController controller;
    private PetRockView view;

    @BeforeEach
    public void setUp() {
        rock = new PetRockModel("TestRock");
        view = new PetRockView();
        controller = new PetRockController(rock);
        controller.addObserver(view); // Add the view as an observer
    }

    @Test
    public void testPlay() {
        // Initial boredom is 0, so playing should not decrease it further
        controller.handlePlay();
        assertEquals(0, rock.getBoredom());

        // Set boredom to 5 and play
        rock.setBoredom(5);
        controller.handlePlay();
        assertEquals(2, rock.getBoredom()); // Boredom decreases by 3
    }

    @Test
    public void testFeed() {
        // Initial hunger is 0, so feeding should not decrease it further
        controller.handleFeed();
        assertEquals(0, rock.getHunger());

        // Set hunger to 5 and feed
        rock.setHunger(5);
        controller.handleFeed();
        assertEquals(3, rock.getHunger()); // Hunger decreases by 2
    }

    @Test
    public void testPolish() {
        // Initial polish count is 0
        assertEquals(0, rock.getPolishCount());

        // Polish the rock
        controller.handlePolish();
        assertEquals(1, rock.getPolishCount());
        assertEquals("Happy", rock.getMood());
    }

    @Test
    public void testPlayCooldown() {
        // Play once
        controller.handlePlay();
        assertTrue(rock.isPlayCooldown());

        // Try to play again immediately (should fail due to cooldown)
        assertThrows(IllegalStateException.class, () -> controller.handlePlay());

        // Reset cooldown and play again
        rock.setPlayCooldown(false);
        controller.handlePlay();
        assertTrue(rock.isPlayCooldown());
    }

    @Test
    public void testFeedCooldown() {
        // Feed once
        controller.handleFeed();
        assertTrue(rock.isFeedCooldown());

        // Try to feed again immediately (should fail due to cooldown)
        assertThrows(IllegalStateException.class, () -> controller.handleFeed());

        // Reset cooldown and feed again
        rock.setFeedCooldown(false);
        controller.handleFeed();
        assertTrue(rock.isFeedCooldown());
    }

    @Test
    public void testRandomEvents() {
        // Trigger random events and verify their effects
        controller.triggerRandomEvent();
        // Since random events are probabilistic, we can't assert specific values
        // But we can ensure the rock's state is updated correctly
        assertTrue(rock.getHunger() >= 0 && rock.getHunger() <= 10);
        assertTrue(rock.getBoredom() >= 0 && rock.getBoredom() <= 10);
        assertTrue(rock.getEnergy() >= 0 && rock.getEnergy() <= 10);
    }
}
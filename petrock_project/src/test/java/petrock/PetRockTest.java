package petrock;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetRockTest {
    private PetRockModel rock = new PetRockModel("Rocky");
    private final Gson gson = new Gson();
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
        rock.setBoredom(0);
        rock.setHunger(0);
        rock.setEnergy(10);
        rock.setPlayCooldown(false);

        controller.handlePlay();
        assertEquals(0, rock.getBoredom());
        assertEquals(1, rock.getHunger());
        assertEquals(8, rock.getEnergy());
        assertTrue(rock.isPlayCooldown());

        assertThrows(IllegalStateException.class, () -> controller.handlePlay());
        assertThrows(IllegalStateException.class, () -> controller.handlePlay());
    }

    @Test
    public void testFeed() {
        rock.setHunger(0);
        rock.setFeedCooldown(false);

        controller.handleFeed();
        assertEquals(0, rock.getHunger());
        assertTrue(rock.isFeedCooldown());

        rock.setFeedCooldown(false);
        rock.setHunger(5);

        controller.handleFeed();
        assertEquals(3, rock.getHunger());
        assertTrue(rock.isFeedCooldown());
    }

    @Test
    public void testPolish() {
        assertEquals(0, rock.getPolishCount());
        rock.setHunger(0);
        rock.setBoredom(0);
        rock.setEnergy(10);

        controller.handlePolish();
        assertEquals(1, rock.getPolishCount());
        assertEquals("Happy", rock.getMood());
    }

    @Test
    public void testPlayCooldown() {
        controller.handlePlay();
        assertTrue(rock.isPlayCooldown());
        assertThrows(IllegalStateException.class, () -> controller.handlePlay());

        rock.setPlayCooldown(false);
        controller.handlePlay();
        assertTrue(rock.isPlayCooldown());
    }

    @Test
    public void testFeedCooldown() {
        controller.handleFeed();
        assertTrue(rock.isFeedCooldown());
        assertThrows(IllegalStateException.class, () -> controller.handleFeed());

        rock.setFeedCooldown(false);
        controller.handleFeed();
        assertTrue(rock.isFeedCooldown());
    }

    @Test
    public void testRandomEvents() {
        controller.triggerRandomEvent();
        assertTrue(rock.getHunger() >= 0 && rock.getHunger() <= 10);
        assertTrue(rock.getBoredom() >= 0 && rock.getBoredom() <= 10);
        assertTrue(rock.getEnergy() >= 0 && rock.getEnergy() <= 10);
    }

    // New tests

    @Test
    public void testRockName() {
        assertEquals("TestRock", rock.getName());
    }

    @Test
    public void testMoodCalculation() {
        // Simulate low energy situation:
        // Set energy to 2, which should cause updateMood() (via an action) to update
        // the mood.
        rock.setEnergy(2);
        rock.setHunger(0);
        rock.setBoredom(0);
        // Call an action that triggers updateMood; handlePolish() increases energy by 1
        // (from 2 to 3)
        // and then calls updateMood(). With energy == 3, the second condition (energy
        // <= 3) applies, making mood \"Sad\".
        controller.handlePolish();
        assertEquals("Sad", rock.getMood());
    }

    @Test
    public void testSerialization() {
        // Set some properties on the rock
        rock.setHunger(4);
        rock.setBoredom(5);
        rock.setEnergy(7);
        rock.setPolishCount(2);
        rock.setLastMeal("pasta");
        rock.setMood("Bored");

        // Serialize to JSON string
        String json = gson.toJson(rock);

        // Deserialize back to object
        PetRockModel rockFromJson = gson.fromJson(json, PetRockModel.class);

        // Verify properties
        assertEquals(rock.getName(), rockFromJson.getName());
        assertEquals(rock.getMood(), rockFromJson.getMood());
        assertEquals(rock.getHunger(), rockFromJson.getHunger());
        assertEquals(rock.getBoredom(), rockFromJson.getBoredom());
        assertEquals(rock.getEnergy(), rockFromJson.getEnergy());
        assertEquals(rock.getPolishCount(), rockFromJson.getPolishCount());
        assertEquals(rock.getLastMeal(), rockFromJson.getLastMeal());
    }

    @Test
    public void testInvalidHunger() {
        // Test that setting hunger below 0 or above 10 throws an exception
        assertThrows(IllegalArgumentException.class, () -> rock.setHunger(-1));
        assertThrows(IllegalArgumentException.class, () -> rock.setHunger(11));
    }

    @Test
    public void testInvalidEnergy() {
        // Test that setting energy below 0 or above 10 throws an exception
        assertThrows(IllegalArgumentException.class, () -> rock.setEnergy(-1));
        assertThrows(IllegalArgumentException.class, () -> rock.setEnergy(11));
    }

    @Test
    public void testObserverNotification() {
        // Create a dummy observer that records events
        DummyObserver dummy = new DummyObserver();
        controller.addObserver(dummy);

        // Reset feed cooldown so we can feed
        rock.setFeedCooldown(false);
        rock.setHunger(5);

        // Call handleFeed
        controller.handleFeed();

        // Verify that the dummy observer received the \"displayFeed\" event
        assertTrue(dummy.receivedEvent("displayFeed"));
    }

    // Dummy observer for testing purposes
    static class DummyObserver implements Observer {
        private java.util.List<String> events = new java.util.ArrayList<>();

        @Override
        public void update(String event, PetRockModel rock) {
            events.add(event);
        }

        @Override
        public String getUserChoice() {
            return "";
        }

        public boolean receivedEvent(String event) {
            return events.contains(event);
        }
    }
}

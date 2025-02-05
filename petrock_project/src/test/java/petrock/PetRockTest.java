package petrock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

class PetRockTest {

    @Test
    void testGetName() {
        PetRockModel rock = new PetRockModel("Rocky");
        assertEquals("Rocky", rock.getName(), "PetRock name should match.");
    }

    @Test
    void testInitialStates() {
        PetRockModel rock = new PetRockModel("Rocky");
        assertEquals("None", rock.getMood(), "A new PetRock should have no mood.");
        assertEquals(0, rock.getHunger(), "A new PetRock should start with no hunger.");
        assertEquals(0, rock.getBoredom(), "A new PetRock should start with no boredom.");
        assertEquals(10, rock.getEnergy(), "A new PetRock should start with full energy.");
    }

    @Test
    void testPlayAdjustsMoodAndStats() {
        PetRockModel rock = new PetRockModel("Rocky");
        rock.play();
        assertEquals("Happy", rock.getMood(), "After playing, PetRock should be happy.");
        assertEquals(1, rock.getHunger(), "Playing should increase hunger.");
        assertEquals(0, rock.getBoredom(), "Playing should reduce boredom.");
        assertEquals(8, rock.getEnergy(), "Playing should decrease energy.");
    }

    @Test
    void testFeedPetRock() {
        PetRockModel rock = new PetRockModel("Rocky");
        rock.feed();
        assertEquals("nom nom", rock.getLastMeal(), "Feeding should set last meal.");
        assertEquals(0, rock.getHunger(), "Feeding should reduce hunger to zero.");
    }

    @Test
    void testPolishPetRock() {
        PetRockModel rock = new PetRockModel("Rocky");
        rock.polish();
        assertEquals(1, rock.getPolishCount(), "Polishing should increase polish count.");
        assertEquals("Happy", rock.getMood(), "Polishing should make the PetRock happy.");
    }

    @Test
    void testThrowExceptionIfNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PetRockModel(null);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void testJSONSerialization() {
        PetRockModel rock = new PetRockModel("Rocky");
        rock.play();
        JSONObject json = rock.toJson();
        assertTrue(json.has("name"), "JSON should contain the pet rock's name.");
        assertTrue(json.has("mood"), "JSON should reflect the correct mood.");
        assertEquals("Rocky", json.getString("name"), "JSON name should match.");
        assertEquals("Happy", json.getString("mood"), "JSON mood should match.");
    }

    @Test
    void testJSONDeserialization() {
        String json = "{\"name\":\"Rocky\",\"mood\":\"Happy\",\"hunger\":0,\"boredom\":0,\"energy\":10,\"lastMeal\":\"None\",\"polishCount\":0}";
        JSONObject jsonObject = new JSONObject(json);
        PetRockModel rock = PetRockModel.fromJson(jsonObject);
        assertEquals("Rocky", rock.getName(), "Deserialized PetRock should have the correct name.");
        assertEquals("Happy", rock.getMood(), "Deserialized PetRock should be happy.");
    }

    @Test
    void testRockCannotPlayWithoutEnergy() {
        PetRockModel rock = new PetRockModel("Rocky");
        rock.play(); // Energy: 10 -> 8
        rock.play(); // Energy: 8 -> 6
        rock.play(); // Energy: 6 -> 4
        rock.play(); // Energy: 4 -> 2
        rock.play(); // Energy: 2 -> 0
        assertThrows(IllegalStateException.class, rock::play, "PetRock should not be able to play without energy.");
    }

    @Test
    void testRandomEventAffectsPetRock() {
        PetRockModel rock = new PetRockModel("Rocky");
        RandomEvent event = new RandomEvent();
        event.triggerEvent(rock);

        // Check if any attribute has changed
        boolean isAffected = rock.getHunger() != 0 || rock.getBoredom() != 0 || rock.getEnergy() != 10;
        assertTrue(isAffected, "Random event should affect hunger, boredom, or energy.");
    }

    @Test
    void testViewDisplaysStatus() {
        PetRockModel rock = new PetRockModel("Rocky");
        PetRockView view = new PetRockView();
        view.displayStatus(rock); // Just ensure no exceptions are thrown
    }

    @Test
    void testControllerHandlesFeed() {
        PetRockModel rock = new PetRockModel("Rocky");
        PetRockView view = new PetRockView();
        PetRockController controller = new PetRockController(rock, view);

        controller.handleFeed(); // Ensure no exceptions are thrown
        assertEquals(0, rock.getHunger(), "Feeding should reduce hunger to zero.");
    }

    @Test
    void testControllerHandlesPlay() {
        PetRockModel rock = new PetRockModel("Rocky");
        PetRockView view = new PetRockView();
        PetRockController controller = new PetRockController(rock, view);

        controller.handlePlay(); // Ensure no exceptions are thrown
        assertEquals(0, rock.getBoredom(), "Playing should reduce boredom to zero.");
    }

    @Test
    void testControllerHandlesPolish() {
        PetRockModel rock = new PetRockModel("Rocky");
        PetRockView view = new PetRockView();
        PetRockController controller = new PetRockController(rock, view);

        controller.handlePolish(); // Ensure no exceptions are thrown
        assertEquals("Happy", rock.getMood(), "Polishing should make the PetRock happy.");
    }
}
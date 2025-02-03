package petrock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetRockTest {

    @Test
    void testGetName() {
        PetRock rock = new PetRock("Rocky");
        assertEquals("Rocky", rock.getName(), "PetRock name should match.");
    }

    @Test
    void testInitialStates() {
        PetRock rock = new PetRock("Rocky");
        assertEquals("Neutral", rock.getMood(), "A new PetRock should have a neutral mood.");
        assertEquals(5, rock.getHunger(), "A new PetRock should start with moderate hunger.");
        assertEquals(5, rock.getBoredom(), "A new PetRock should start with moderate boredom.");
        assertEquals(5, rock.getEnergy(), "A new PetRock should start with moderate energy.");
    }

    @Test
    void testPlayAdjustsMoodAndStats() {
        PetRock rock = new PetRock("Rocky");
        rock.play();
        assertEquals("Happy", rock.getMood(), "After playing, PetRock should be happy.");
        assertEquals(6, rock.getHunger(), "Playing should increase hunger.");
        assertEquals(0, rock.getBoredom(), "Playing should reduce boredom.");
        assertEquals(4, rock.getEnergy(), "Playing should decrease energy.");
    }

    @Test
    void testFeedPetRock() {
        PetRock rock = new PetRock("Rocky");
        rock.feed();
        assertEquals("nom nom", rock.getLastMeal(), "Feeding should set last meal.");
        assertEquals(0, rock.getHunger(), "Feeding should reduce hunger to zero.");
    }

    @Test
    void testPolishPetRock() {
        PetRock rock = new PetRock("Rocky");
        rock.polish();
        assertEquals(1, rock.getPolishCount(), "Polishing should increase polish count.");
        assertEquals("Happy", rock.getMood(), "Polishing should make the PetRock happy.");
    }

    @Test
    void testThrowExceptionIfNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PetRock(null);
        });
        assertEquals("Name cannot be null", exception.getMessage());
    }

    @Test
    void testJSONSerialization() {
        PetRock rock = new PetRock("Rocky");
        rock.play();
        String json = rock.toJson();
        assertTrue(json.contains("\"name\":\"Rocky\""), "JSON should contain the pet rock's name.");
        assertTrue(json.contains("\"mood\":\"Happy\""), "JSON should reflect the correct mood.");
    }

    @Test
    void testJSONDeserialization() {
        String json = "{\"name\":\"Rocky\",\"mood\":\"Happy\",\"hunger\":0,\"boredom\":0,\"energy\":10,\"lastMeal\":\"None\",\"polishCount\":0}";
        PetRock rock = PetRock.fromJson(json);
        assertEquals("Rocky", rock.getName(), "Deserialized PetRock should have the correct name.");
        assertEquals("Happy", rock.getMood(), "Deserialized PetRock should be happy.");
    }

    @Test
    void testRockCannotPlayWithoutEnergy() {
        PetRock rock = new PetRock("Rocky");
        rock.play();
        rock.play();
        rock.play();
        rock.play();
        rock.play(); // Exhaust energy
        assertThrows(IllegalStateException.class, rock::play, "PetRock should not be able to play without energy.");
    }

    @Test
    void testRandomEventAffectsPetRock() {
        PetRock rock = new PetRock("Rocky");
        RandomEvent event = new RandomEvent();
        String result = event.triggerEvent(rock);
        if (!result.isEmpty()) {
            assertTrue(result.contains("Rocky"), "Random event message should contain PetRock's name.");
        }
        // Test that one of the attributes might have changed
        assertTrue(rock.getHunger() != 5 || rock.getBoredom() != 5 || rock.getEnergy() != 5,
                "Random event should affect hunger, boredom, or energy.");
    }
} 

package petrock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

class PetRockTest {

    @Test
    void testGetName() {
        PetRock rock = new PetRock("Rocky");
        assertEquals("Rocky", rock.getName(), "PetRock name should match.");
    }

    @Test
    void testHappyInitiallyFalse() {
        PetRock rock = new PetRock("Rocky");
        assertFalse(rock.isHappy(), "A new PetRock should not be happy.");
    }

    @Test
    void testPlayMakesRockHappy() {
        PetRock rock = new PetRock("Rocky");
        rock.play();
        assertTrue(rock.isHappy(), "After playing, PetRock should be happy.");
    }

    @Test
    void testFeedPetRock() {
        PetRock rock = new PetRock("Rocky");
        rock.feed();
        assertEquals("nom nom", rock.getLastMeal(), "Feeding should set last meal.");
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
    }

    @Test
    void testJSONDeserialization() {
        String json = "{\"name\":\"Rocky\",\"mood\":\"Happy\",\"hunger\":0,\"boredom\":0,\"energy\":10,\"lastMeal\":\"None\",\"polishCount\":0}";
        PetRock rock = PetRock.fromJson(json);
        assertEquals("Rocky", rock.getName(), "Deserialized PetRock should have the correct name.");
        assertEquals("Happy", rock.getMood(), "Deserialized PetRock should be happy.");
    }

    @Test
    void testRockCannotPlayTwice() {
        PetRock rock = new PetRock("Rocky");
        rock.play();
        assertThrows(IllegalStateException.class, rock::play, "PetRock should not be able to play twice.");
    }
}
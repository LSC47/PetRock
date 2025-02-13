package petrock;

public interface Observer {
    void update(String event, PetRockModel rock); // Notify observers of an event with the rock's state

    String getUserChoice(); // Request user input from the observer
}
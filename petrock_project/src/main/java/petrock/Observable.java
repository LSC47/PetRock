package petrock;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<Observer> observers = new ArrayList<>();

    // Add an observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers
    public void notifyObservers(String event, PetRockModel rock) {
        for (Observer observer : observers) {
            observer.update(event, rock); // Pass the rock object
        }
    }

    // Get the list of observers
    public List<Observer> getObservers() {
        return observers;
    }
}
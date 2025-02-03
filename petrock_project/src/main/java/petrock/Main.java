package petrock;

public class Main {
    public static void main(String[] args) {
        PetRock petRock = new PetRock("Rocky");
        PetRockView view = new PetRockView();
        RandomEvent randomEvent = new RandomEvent();
        PetRockController controller = new PetRockController(petRock, view, randomEvent);

        controller.runGameLoop();
    }
}
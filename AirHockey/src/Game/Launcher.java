package Game;

public class Launcher {
    public static void main(String[] args) {
        GameEngine hockey = new GameEngine("Air Hockey");

        hockey.start();
    }
}
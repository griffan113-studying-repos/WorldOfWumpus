package main.java.com.worldofwumpus;

import main.java.com.worldofwumpus.controller.GameController;

public class WorldOfWumpusApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.startGame();
    }
}

package main.java.com.worldofwumpus;

import main.java.com.worldofwumpus.controllers.GameController;

public class WorldOfWumpusApplication {
    public static void main(String[] args) {
        GameController gameController = new GameController();

        gameController.init();
    }
}

package main.java.com.worldofwumpus.model.wumpus;

import main.java.com.worldofwumpus.model.game.GameObject;

public class Wumpus extends GameObject {
    boolean isDead;
    public Wumpus() {
        super(1, "👹");

        isDead = false;
    }
}

package main.java.com.worldofwumpus.entities.wumpus;

import main.java.com.worldofwumpus.models.GameObject;

public class Wumpus extends GameObject {
    boolean isDead;
    public Wumpus() {
        super(1, "👹");

        isDead = false;
    }
}

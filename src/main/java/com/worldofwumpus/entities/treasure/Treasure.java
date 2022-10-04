package main.java.com.worldofwumpus.entities.treasure;

import main.java.com.worldofwumpus.models.GameObject;

public class Treasure extends GameObject {
    boolean alreadyCaught;
    public Treasure() {
        super(1 << 2, "💰");

        alreadyCaught = false;
    }

    public boolean isAlreadyCaught() {
        return alreadyCaught;
    }

    public void setAlreadyCaught(boolean alreadyCaught) {
        this.alreadyCaught = alreadyCaught;
    }
}
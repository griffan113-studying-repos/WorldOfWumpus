package main.java.com.worldofwumpus.entities.gold;

import main.java.com.worldofwumpus.models.GameEntity;

public class Gold extends GameEntity {
    boolean alreadyCaught;
    public Gold() {
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
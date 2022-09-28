package main.java.com.worldofwumpus.model.treasure;

import main.java.com.worldofwumpus.model.game.GameObject;

public class Treasure extends GameObject {
    public Treasure() {
        super(1 << 2, "💰");
    }
}
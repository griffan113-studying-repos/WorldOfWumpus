package main.java.com.worldofwumpus.entities.player;

import main.java.com.worldofwumpus.models.GameObject;

public class Player extends GameObject {
    int direction;

    public Player() {
        super(1 << 1, 3, 0, "🤠");

        direction = MovementDirections.EAST.getValue();
    }

    public void turnR() {
        setDirection((direction + 1) % 4);
    }

    public void turnL() {
        setDirection(--direction);
        if (direction < 0) setDirection(direction = 3);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

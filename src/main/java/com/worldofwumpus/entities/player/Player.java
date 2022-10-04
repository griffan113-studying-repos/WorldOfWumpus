package main.java.com.worldofwumpus.entities.player;

import main.java.com.worldofwumpus.models.GameObject;

public class Player extends GameObject {
    int direction;
    int arrows;

    public Player() {
        super(1 << 1, 3, 0, "🤠");

        direction = MovementDirections.EAST.getValue();
        arrows = 5;
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

    public String printDirection() {
        if (direction == MovementDirections.EAST.getValue()) return "➡️ East";
        else if (direction == MovementDirections.WEST.getValue()) return "⬅ West";
        else if (direction == MovementDirections.NORTH.getValue()) return "⬆️ North";
        else return "⬇ South";
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getArrows() {
        return arrows;
    }

    public boolean shoot() {
        if (arrows == 0) {
            System.out.println("You don't have arrows.");

            return false;
        }

        arrows -= 1;

        return true;
    }
}
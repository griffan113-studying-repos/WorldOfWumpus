package main.java.com.worldofwumpus.entities.player;

public enum MovementDirections {
    NORTH(3),
    SOUTH(1),
    EAST(0),
    WEST(2);

    private final int value;

    MovementDirections(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
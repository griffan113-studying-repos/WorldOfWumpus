package main.java.com.worldofwumpus.entities.hero;

public enum Directions {
    NORTH(3),
    SOUTH(1),
    EAST(0),
    WEST(2);

    private final int value;

    Directions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

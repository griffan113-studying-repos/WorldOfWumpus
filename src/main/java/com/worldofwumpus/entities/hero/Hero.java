package main.java.com.worldofwumpus.entities.hero;

import main.java.com.worldofwumpus.models.GameEntity;

public class Hero extends GameEntity {
    int direction;
    int arrows;

    public Hero() {
        super(1 << 1, 3, 0, "🤠");

        direction = Directions.EAST.getValue();
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
        if (direction == Directions.EAST.getValue()) return "➡️ East";
        else if (direction == Directions.WEST.getValue()) return "⬅ West";
        else if (direction == Directions.NORTH.getValue()) return "⬆️ North";
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

    public void walk(int[][] map) {
        if (direction == Directions.EAST.getValue()) {
            if (getColumn() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[getLine()][getColumn()] ^= getNumber();
                map[getLine()][getColumn() + 1] |= getNumber();
                setColumn(getColumn() + 1);
            }
        } else if (direction == Directions.SOUTH.getValue()) {
            if (getLine() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[getLine()][getColumn()] ^= getNumber();
                map[getLine() + 1][getColumn()] |= getNumber();
                setLine(getLine() + 1);
            }
        } else if (direction == Directions.WEST.getValue()) {
            if (getColumn() - 1 < 0) System.out.println("Fim do tabuleiro");
            else {
                map[getLine()][getColumn()] ^= getNumber();
                map[getLine()][getColumn() - 1] |= getNumber();
                setColumn(getColumn() - 1);
            }
        } else if (direction == Directions.NORTH.getValue()) {
            if (getLine() - 1 < 0) System.out.println("Fim do tabuleiro");
            else {
                map[getLine()][getColumn()] ^= getNumber();
                map[getLine() - 1][getColumn()] |= getNumber();
                setLine(getLine() - 1);
            }
        }
    }
}

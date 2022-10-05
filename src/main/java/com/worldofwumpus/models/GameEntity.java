package main.java.com.worldofwumpus.models;

public abstract class GameEntity {
    int number;
    int line;
    int column;
    String symbol;

    boolean isVisible;

    public GameEntity(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
        isVisible = true;
    }

    public GameEntity(int number, int line, int column, String symbol) {
        this.line = line;
        this.column = column;
        this.number = number;
        this.symbol = symbol;
        isVisible = true;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumber() {
        return number;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean visible) {
        isVisible = visible;
    }
}

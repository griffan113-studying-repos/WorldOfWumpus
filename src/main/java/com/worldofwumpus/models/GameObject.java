package main.java.com.worldofwumpus.models;

public abstract class GameObject {
    int id;
    int line;
    int column;
    String symbol;

    boolean isVisible;

    public GameObject(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
        isVisible = true;
    }

    public GameObject(int id, int line, int column, String symbol) {
        this.line = line;
        this.column = column;
        this.id = id;
        this.symbol = symbol;
        isVisible = true;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getId() {
        return id;
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

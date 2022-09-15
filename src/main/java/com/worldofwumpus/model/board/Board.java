package main.java.com.worldofwumpus.model.board;

import main.java.com.worldofwumpus.model.game.GameObject;
import main.java.com.worldofwumpus.model.player.MovementDirections;
import main.java.com.worldofwumpus.model.player.Player;
import main.java.com.worldofwumpus.model.wumpus.Wumpus;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    int[][] map;
    Player player;
    Wumpus wumpus;
    ArrayList<GameObject> spawnedEntities;

    public Board(Player player, Wumpus wumpus) {
        map = new int[4][4];
        this.player = player;
        this.wumpus = wumpus;
        spawnedEntities = new ArrayList<>();
        spawn(player);
        spawnWumpus();
    }

    private void spawn(GameObject object) {
        map[object.getLine()][object.getColumn()] = object.getId();
        spawnedEntities.add(object);
    }

    private void spawnWumpus() {
        Random random = new Random();
        int randomMapLine = random.nextInt(map.length);
        int randomMapColumn = random.nextInt(map[0].length);

        if (map[randomMapLine][randomMapColumn] == player.getId())
            spawnWumpus(); // Try again until find a place without player
        else {
            wumpus.setLine(randomMapLine);
            wumpus.setColumn(randomMapColumn);
            spawn(wumpus);
        }
    }

    public void movementPlayer() {
        if (player.getDirection() == MovementDirections.EAST.getValue()) {
            if (player.getColumn() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine()][player.getColumn() + 1] |= player.getId();
            }
        } else if (player.getDirection() == MovementDirections.SOUTH.getValue()) {
            if (player.getLine() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine() + 1][player.getColumn()] |= player.getId();
            }
        } else if (player.getDirection() == MovementDirections.WEST.getValue()) {
            if (player.getColumn() - 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine()][player.getColumn() + 1] |= player.getId();
            }
        } else if (player.getDirection() == MovementDirections.NORTH.getValue()) {
            if (player.getColumn() - 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine() + 1][player.getColumn()] |= player.getId();
            }
        }
    }

    public void printBoard() {
        System.out.println();
        for (int line = 0; line < map.length; line++) {
            for (int column = 0; column < map[line].length; column++) {
                int finalLine = line;
                int finalColumn = column;

                if (map[finalLine][finalColumn] == 0) System.out.print("⁕  ");

                spawnedEntities.forEach(entity -> {
                    if (map[finalLine][finalColumn] == entity.getId()) System.out.print(entity.getSymbol() + "  ");
                });
            }

            System.out.println();
        }
    }
}

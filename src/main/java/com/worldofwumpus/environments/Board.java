package main.java.com.worldofwumpus.environments;

import main.java.com.worldofwumpus.entities.pit.Pit;
import main.java.com.worldofwumpus.models.GameObject;
import main.java.com.worldofwumpus.entities.player.MovementDirections;
import main.java.com.worldofwumpus.entities.player.Player;
import main.java.com.worldofwumpus.entities.treasure.Treasure;
import main.java.com.worldofwumpus.entities.wumpus.Wumpus;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    final int[][] map;
    Player player;
    Wumpus wumpus;
    Treasure treasure;
    ArrayList<GameObject> spawnedEntities;

    public Board(Player player, Wumpus wumpus, Treasure treasure) {
        map = new int[4][4];
        this.player = player;
        this.wumpus = wumpus;
        this.treasure = treasure;
        spawnedEntities = new ArrayList<>();
        spawn(player);
        randomlySpawn(wumpus, true);
        randomlySpawn(treasure, false);
        randomlySpawn(new Pit(), true);
    }

    private void spawn(GameObject object) {
        map[object.getLine()][object.getColumn()] = object.getId();
        spawnedEntities.add(object);
    }

    private void randomlySpawn(GameObject entityToSpawn, boolean withGap) {
        Random random = new Random();
        int randomMapLine = random.nextInt(map.length);
        int randomMapColumn = random.nextInt(map[0].length);

        System.out.println(randomMapLine);
        System.out.println(randomMapColumn);

        for (GameObject ignored : spawnedEntities) {
            if (withGap) {
                int calculateMapLinePlusOffset = randomMapLine + 1 > map.length - 1 ? randomMapLine : randomMapLine + 1;
                int calculateMapLineMinusOffset = randomMapLine - 1 < 0 ? randomMapLine : randomMapLine - 1;

                int calculateMapColumnPlusOffset = randomMapColumn + 1 > map[0].length - 1 ? randomMapColumn : randomMapColumn + 1;
                int calculateMapColumnMinusOffset = randomMapColumn - 1 < 0 ? randomMapColumn : randomMapColumn - 1;

                int[] lineOffsets = {calculateMapLinePlusOffset, calculateMapLineMinusOffset};
                int[] columnOffsets = {calculateMapColumnPlusOffset, calculateMapColumnMinusOffset};

                for (int lineOffset : lineOffsets) {
                    for (int columnOffset : columnOffsets) {
                        if (map[lineOffset][columnOffset] != 0) {
                            randomlySpawn(entityToSpawn, true);
                        }
                        else {
                            entityToSpawn.setLine(randomMapLine);
                            entityToSpawn.setColumn(randomMapColumn);
                            spawn(entityToSpawn);
                        }
                        break;
                    }
                    break;
                }
            } else {
                if (map[randomMapLine][randomMapColumn] != 0) randomlySpawn(entityToSpawn, false);
                else {
                    entityToSpawn.setLine(randomMapLine);
                    entityToSpawn.setColumn(randomMapColumn);
                    spawn(entityToSpawn);
                }
            }
            break;
        }
    }

    private void killWumpus(int line, int column) {
        map[line][column] ^= wumpus.getId();
        spawnedEntities.remove(wumpus);
        wumpus.setIsDead(true);

        System.out.println("🥳 You just killed Wumpus!");
    }

    private void takeTreasure() {
        map[treasure.getLine()][treasure.getColumn()] ^= treasure.getId();
        spawnedEntities.remove(treasure);
        treasure.setAlreadyCaught(true);

        System.out.println("🥳 You just took the treasure!");
    }

    public void playerTakeTreasure() {
        if (player.getDirection() == MovementDirections.EAST.getValue()) {
            if (player.getColumn() + 1 < map[player.getLine()].length && map[player.getLine()][player.getColumn() + 1] == treasure.getId()) {
                takeTreasure();
            }
        } else if (player.getDirection() == MovementDirections.SOUTH.getValue()) {
            if (player.getLine() + 1 < map.length && map[player.getLine() + 1][player.getColumn()] == treasure.getId()) {
                takeTreasure();
            }
        } else if (player.getDirection() == MovementDirections.WEST.getValue()) {
            if (player.getColumn() - 1 >= 0 && map[player.getLine()][player.getColumn() - 1] == treasure.getId()) {
                takeTreasure();
            }
        } else if (player.getDirection() == MovementDirections.NORTH.getValue()) {
            if (player.getLine() - 1 >= 0 && map[player.getLine() - 1][player.getColumn()] == treasure.getId()) {
                takeTreasure();
            }
        }
    }

    public void playerShoot() {
        if (player.shoot()) {
            if (player.getDirection() == MovementDirections.EAST.getValue()) {
                for (int column = player.getColumn() + 1; column <= map[player.getLine()].length - 1; column++) {
                    if (map[player.getLine()][column] == wumpus.getId()) {
                        killWumpus(player.getLine(), column);
                        break;
                    }
                }
            } else if (player.getDirection() == MovementDirections.SOUTH.getValue()) {
                for (int line = player.getLine() + 1; line <= map.length - 1; line++) {
                    if (map[line][player.getColumn()] == wumpus.getId()) {
                        killWumpus(line, player.getColumn());
                        break;
                    }

                }
            } else if (player.getDirection() == MovementDirections.WEST.getValue()) {
                for (int column = player.getColumn() - 1; column <= map[player.getLine()].length - 1; column--) {
                    if (map[player.getLine()][column] == wumpus.getId()) {
                        killWumpus(player.getLine(), column);

                        break;
                    }
                }
            } else if (player.getDirection() == MovementDirections.NORTH.getValue()) {
                for (int line = player.getLine() - 1; line <= map.length - 1; line--) {
                    if (map[line][player.getColumn()] == wumpus.getId()) {
                        killWumpus(line, player.getColumn());

                        break;
                    }
                }
            }
        }
    }

    public void playerWalk() {
        if (player.getDirection() == MovementDirections.EAST.getValue()) {
            if (player.getColumn() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine()][player.getColumn() + 1] |= player.getId();
                player.setColumn(player.getColumn() + 1);
            }
        } else if (player.getDirection() == MovementDirections.SOUTH.getValue()) {
            if (player.getLine() + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine() + 1][player.getColumn()] |= player.getId();
                player.setLine(player.getLine() + 1);
            }
        } else if (player.getDirection() == MovementDirections.WEST.getValue()) {
            if (player.getColumn() - 1 < 0) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine()][player.getColumn() - 1] |= player.getId();
                player.setColumn(player.getColumn() - 1);
            }
        } else if (player.getDirection() == MovementDirections.NORTH.getValue()) {
            if (player.getLine() - 1 < 0) System.out.println("Fim do tabuleiro");
            else {
                map[player.getLine()][player.getColumn()] ^= player.getId();
                map[player.getLine() - 1][player.getColumn()] |= player.getId();
                player.setLine(player.getLine() - 1);
            }
        }
    }

    public void printBoard() {
        System.out.println("—————————————————————");
        System.out.println("🏹 Arrows: " + player.getArrows());
        System.out.println("🚶 Direction: " + player.printDirection());
        System.out.println("—————————————————————");
        for (int line = 0; line < map.length; line++) {
            for (int column = 0; column < map[line].length; column++) {
                int finalLine = line;
                int finalColumn = column;

                if (map[finalLine][finalColumn] == 0) System.out.print("⁕  ");

                spawnedEntities.forEach(entity -> {
                    if (map[finalLine][finalColumn] == entity.getId() && entity.isVisible())
                        System.out.print(entity.getSymbol() + " ");
                });
            }

            System.out.println();
        }
    }
}

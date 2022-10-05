package main.java.com.worldofwumpus.environments;

import main.java.com.worldofwumpus.entities.pit.Buraco;
import main.java.com.worldofwumpus.models.GameEntity;
import main.java.com.worldofwumpus.entities.hero.Directions;
import main.java.com.worldofwumpus.entities.hero.Hero;
import main.java.com.worldofwumpus.entities.gold.Gold;
import main.java.com.worldofwumpus.entities.wumpus.Wumpus;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    final int[][] map;
    Hero hero;
    Wumpus wumpus;
    Gold gold;
    ArrayList<GameEntity> spawnedEntities;

    public Board(Hero hero, Wumpus wumpus, Gold gold) {
        map = new int[4][4];
        this.hero = hero;
        this.wumpus = wumpus;
        this.gold = gold;
        spawnedEntities = new ArrayList<>();
        spawn(hero);
        randomlySpawn(wumpus, true);
        randomlySpawn(gold, false);
        randomlySpawn(new Buraco(), true);
    }

    private void spawn(GameEntity object) {
        map[object.getLine()][object.getColumn()] = object.getNumber();
        spawnedEntities.add(object);
    }

    private void randomlySpawn(GameEntity entityToSpawn, boolean withGap) {
        Random random = new Random();
        int randomMapLine = random.nextInt(map.length);
        int randomMapColumn = random.nextInt(map[0].length);

        System.out.println(randomMapLine);
        System.out.println(randomMapColumn);

        for (GameEntity ignored : spawnedEntities) {
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
                        } else {
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
        map[line][column] ^= wumpus.getNumber();
        spawnedEntities.remove(wumpus);
        wumpus.setIsDead(true);

        System.out.println("🥳 You just killed Wumpus!");
    }

    private void takeTreasure() {
        map[gold.getLine()][gold.getColumn()] ^= gold.getNumber();
        spawnedEntities.remove(gold);
        gold.setAlreadyCaught(true);

        System.out.println("🥳 You just took the treasure!");
    }

    public void playerTakeTreasure() {
        if (hero.getDirection() == Directions.EAST.getValue()) {
            if (hero.getColumn() + 1 < map[hero.getLine()].length && map[hero.getLine()][hero.getColumn() + 1] == gold.getNumber()) {
                takeTreasure();
            }
        } else if (hero.getDirection() == Directions.SOUTH.getValue()) {
            if (hero.getLine() + 1 < map.length && map[hero.getLine() + 1][hero.getColumn()] == gold.getNumber()) {
                takeTreasure();
            }
        } else if (hero.getDirection() == Directions.WEST.getValue()) {
            if (hero.getColumn() - 1 >= 0 && map[hero.getLine()][hero.getColumn() - 1] == gold.getNumber()) {
                takeTreasure();
            }
        } else if (hero.getDirection() == Directions.NORTH.getValue()) {
            if (hero.getLine() - 1 >= 0 && map[hero.getLine() - 1][hero.getColumn()] == gold.getNumber()) {
                takeTreasure();
            }
        }
    }

    public void playerShoot() {
        if (hero.shoot()) {
            if (hero.getDirection() == Directions.EAST.getValue()) {
                for (int column = hero.getColumn() + 1; column <= map[hero.getLine()].length - 1; column++) {
                    if (map[hero.getLine()][column] == wumpus.getNumber()) {
                        killWumpus(hero.getLine(), column);
                        break;
                    }
                }
            } else if (hero.getDirection() == Directions.SOUTH.getValue()) {
                for (int line = hero.getLine() + 1; line <= map.length - 1; line++) {
                    if (map[line][hero.getColumn()] == wumpus.getNumber()) {
                        killWumpus(line, hero.getColumn());
                        break;
                    }

                }
            } else if (hero.getDirection() == Directions.WEST.getValue()) {
                for (int column = hero.getColumn() - 1; column <= map[hero.getLine()].length - 1; column--) {
                    if (map[hero.getLine()][column] == wumpus.getNumber()) {
                        killWumpus(hero.getLine(), column);

                        break;
                    }
                }
            } else if (hero.getDirection() == Directions.NORTH.getValue()) {
                for (int line = hero.getLine() - 1; line <= map.length - 1; line--) {
                    if (map[line][hero.getColumn()] == wumpus.getNumber()) {
                        killWumpus(line, hero.getColumn());

                        break;
                    }
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public void printBoard() {
        System.out.println("—————————————————————");
        System.out.println("🏹 Arrows: " + hero.getArrows());
        System.out.println("🚶 Direction: " + hero.printDirection());
        System.out.println("—————————————————————");
        for (int line = 0; line < map.length; line++) {
            for (int column = 0; column < map[line].length; column++) {
                int finalLine = line;
                int finalColumn = column;

                if (map[finalLine][finalColumn] == 0) System.out.print("⁕  ");

                spawnedEntities.forEach(entity -> {
                    if (map[finalLine][finalColumn] == entity.getNumber() && entity.isVisible())
                        System.out.print(entity.getSymbol() + " ");
                });
            }

            System.out.println();
        }
    }
}

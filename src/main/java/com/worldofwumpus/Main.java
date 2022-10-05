package main.java.com.worldofwumpus;

import main.java.com.worldofwumpus.entities.hero.Hero;
import main.java.com.worldofwumpus.entities.wumpus.Wumpus;
import main.java.com.worldofwumpus.environments.Board;

import java.util.Scanner;

public class Main {

    static Hero hero;
    Wumpus wumpus;
    static Board board;

    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);

        // next - pega o próximo comando do Buffer
        String userPrompt = prompt.next();

        if (userPrompt.equals("start")) startGame();
    }

    private static void startGame() {
        Scanner prompt = new Scanner(System.in);

        String userPrompt;

        board.printBoard();

        // next - pega o próximo comando do Buffer
        while (!(userPrompt = prompt.next()).equals("exit")) {
            switch (userPrompt) {
                case "left" -> hero.turnL();
                case "right" -> hero.turnR();
                case "walk" -> hero.walk(board.getMap());
                case "shoot" -> board.playerShoot();
                case "take" -> board.playerTakeTreasure();
            }

            board.printBoard();
        }
    }
}

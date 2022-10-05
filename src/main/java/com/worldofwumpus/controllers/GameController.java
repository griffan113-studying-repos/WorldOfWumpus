package main.java.com.worldofwumpus.controllers;

import main.java.com.worldofwumpus.environments.Board;
import main.java.com.worldofwumpus.entities.player.Player;
import main.java.com.worldofwumpus.entities.treasure.Treasure;
import main.java.com.worldofwumpus.entities.wumpus.Wumpus;

import java.util.Scanner;

public class GameController {
    Player player;
    Wumpus wumpus;
    Board board;
    boolean isGameOver;

    public GameController() {
        player = new Player();
        wumpus = new Wumpus();
        Treasure treasure = new Treasure();
        board = new Board(player, wumpus, treasure);
        isGameOver = false;
    }

    public void init() {
        printBanner();

        Scanner prompt = new Scanner(System.in);

        // next - pega o próximo comando do Buffer
        String userPrompt = prompt.next();

        if (userPrompt.equals("start")) startGame();
    }

    private void printBanner() {
        System.out.println("⌈———————————————————👹 Welcome to World Of Wumpus 👹——————————⌉");
        System.out.println("|———————————————————— Author: Eliel M. Gaspar —————————————————|");
        System.out.println("|— Objective: Get the treasure and kill Wumpus (and not die) ——|");
        System.out.println("|—————————————————————— Type 'start' to play ——————————————————|");
        System.out.println("⌊———————————————————————————— Good Luck! ——————————————————————⌋");
    }

    private void startGame() {
        Scanner prompt = new Scanner(System.in);

        String userPrompt;

        board.printBoard();

        // next - pega o próximo comando do Buffer
        while (!(userPrompt = prompt.next()).equals("exit")) {
            switch (userPrompt) {
                case "left" -> player.turnL();
                case "right" -> player.turnR();
                case "walk" -> player.walk(board.getMap());
                case "shoot" -> board.playerShoot();
                case "take" -> board.playerTakeTreasure();
            }

            board.printBoard();
        }
    }
}


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

    public void startGame() {
        Scanner user = new Scanner(System.in);

        String userInput;

        board.printBoard();

        // next - pega o próximo comando do Buffer
        while (!(userInput = user.next()).equals("exit")) {
            switch (userInput) {
                case "walk" -> board.movementPlayer();
                case "left" -> player.turnL();
                case "right" -> player.turnR();
            }

            board.printBoard();
        }
    }
}


package main.java.com.worldofwumpus.controller;

import main.java.com.worldofwumpus.model.board.Board;
import main.java.com.worldofwumpus.model.player.Player;
import main.java.com.worldofwumpus.model.wumpus.Wumpus;

import java.util.Scanner;

public class GameController {
    Player player;
    Wumpus wumpus;
    Board board;
    boolean isGameOver;

    public GameController() {
        player = new Player();
        wumpus = new Wumpus();
        board = new Board(player, wumpus);
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
            }

            board.printBoard();
        }
    }
}

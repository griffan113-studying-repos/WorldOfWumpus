import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Kernel game = new Kernel();
        Scanner user = new Scanner(System.in);

        String userInput;

        // next - pega o proximo comando do Buffer
        while (!(userInput = user.next()).equals("exit")) {
            if (userInput.equals("walk")) game.walk();
            else if (userInput.equals("turnL")) game.turnL();
            else if (userInput.equals("turnR")) game.turnR();

            game.printCave();
        }
    }
}
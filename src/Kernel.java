public class Kernel {
    int EAST = 0;
    int SOUTH = 1;
    int WEST = 2;
    int NORTH = 3;
    int wumpus = 1;
    int player = 1 << 1;
    int gold = 1 << 2;
    int pit = 1 << 3;
    int breeze = 1 << 4;
    int smell = 1 << 5;
    int spark = 1 << 6;

    int[][] cave;
    int playerLine, playerColumn;
    int playerDirection;

    public Kernel() {
        cave = new int[4][4];
        playerLine = 3;
        playerColumn = 0;
        playerDirection = EAST;
        cave[playerLine][playerColumn] = player;
        cave[0][2] = wumpus;
    }

    public void walk() {
        if (playerDirection == EAST) {
            if (playerColumn + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                cave[playerLine][playerColumn] ^= player;
                cave[playerLine][++playerColumn] |= player;
            }
        } else if (playerDirection == SOUTH) {
            if (playerLine + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                cave[playerLine][playerColumn] ^= player;
                cave[++playerLine][playerColumn] |= player;
            }
        } else if (playerDirection == WEST) {
            if (playerColumn + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                cave[playerLine][playerColumn] ^= player;
                cave[playerLine][--playerColumn] |= player;
            }
        } else if (playerDirection == NORTH) {
            if (playerColumn + 1 > 3) System.out.println("Fim do tabuleiro");
            else {
                cave[playerLine][playerColumn] ^= player;
                cave[--playerLine][playerColumn] |= player;
            }
        }
    }

    public void turnR() {
        playerDirection = ++playerDirection % 4;
    }

    public void turnL() {
        --playerDirection;
        if (playerDirection < 0) playerDirection = 3;
    }

    public void printCave() {
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cave[i][j] == 0) System.out.print("⁕  ");
                else if (cave[i][j] == player) System.out.print("🤠  ");
                else if (cave[i][j] == wumpus) System.out.print("👹  ");
            }

            System.out.println();
        }
    }
}

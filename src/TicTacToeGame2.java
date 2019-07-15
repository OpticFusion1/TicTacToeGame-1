// Anirudh Prakash

import java.util.*;
import java.awt.*;

public class TicTacToeGame2 { // With graphics

    public static final int SIZE = 250;

    public static Scanner console;
    public static char[][] board;
    public static boolean gameOver;
    public static int count;
    public static Random random;
    public static DrawingPanel panel;
    public static Graphics g;
    public static boolean comp;

    public static void main(String[] args) {
        board = new char[3][3];
        console = new Scanner(System.in);
        random = new Random();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }

        panel = new DrawingPanel(SIZE * 3, SIZE * 3);
        g = panel.getGraphics();
        g.fillRect(SIZE - (SIZE / 40), 0, SIZE / 20, SIZE * 3);
        g.fillRect(2 * SIZE - (SIZE / 40), 0, SIZE / 20, SIZE * 3);
        g.fillRect(0, SIZE - (SIZE / 40), SIZE * 3, SIZE / 20);
        g.fillRect(0, 2 * SIZE - (SIZE / 40), SIZE * 3, SIZE / 20);
        g.setFont(new Font("Monospaced", Font.BOLD, SIZE));

        print();
        gameOver = false;
        count = 0;
        comp = false;
        System.out.print("1 or 2 players? ");
        int p = console.nextInt();
        if (p == 1) {
            comp = true;
        }
        while (!gameOver) {
            if (comp) {
                System.out.println("User turn.");
            } else {
                System.out.println("Player 1 turn.");
            }
            askUser('X');
            checkBoard();
            if (gameOver) {
                break;
            }
            if (comp) {
                System.out.println("Computer turn.");
                askComputer();
            } else {
                System.out.println("Player 2 turn.");
                askUser('O');
            }
            checkBoard();
        }
        System.out.println("GAME OVER."); // win, lose, draw
    }

    public static void print() {
        System.out.println("    0  1  2");
        System.out.println("  ----------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " [ " + board[i][0]);
            for (int j = 1; j < board[i].length; j++) {
                System.out.print("| " + board[i][j]);
            }
            System.out.println("]");
            System.out.println("  ----------");
        }
    }

    public static void askUser(char symb) {
        System.out.print("Choose where to place your " + symb + " (row column): ");
        int x = console.nextInt();
        int y = console.nextInt();
        if (x >= board.length || y >= board.length) {
            gameOver = true;
        } else if (board[x][y] == ' ') {
            board[x][y] = symb;
            count++;
            int xc = SIZE / 5 + y * SIZE;
            int yc = 4 * SIZE / 5 + x * SIZE;
            g.drawString("" + symb, xc, yc);
            print();
        } else {
            System.out.println("Try again."); // do this if >= length too
            askUser(symb);
        }
    }

    // if want super intelligent, make it go through everything and find move that
    // is most likely to win
    /*
     * _|_|_ _|O|_ _|O|_ O|O|_ O|O|X O|O|X O|O|X _|X|_ _|X|_ _|X|_ _|X|_ _|X|_ _|X|_
     * _|X|X _|_|_ _|_|_ _|_|X _|_|X _|_|X O|_|X O|_|X 1 1 0 1 2 2 0 0 0 2 2 0 1 2
     * Basically, player 1 guaranteed win or draw, 2 guaranteed loss or draw
     */
    public static void askComputer() { // random location
        // need to block: up, down, diagonal; adjacent or opposite
        int x = 3;
        int y = 3;
        if (count == 1 && board[1][1] == 'X') {
            // 0 0 or 0 2 or 2 0 or 2 2
            int a = random.nextInt(2); // 0 or 1
            int b = random.nextInt(2);
            if (a == 0) {
                x = 0;
            } else {
                x = 2;
            }
            if (b == 0) {
                y = 0;
            } else {
                y = 2;
            }
        } else {
            // try to win first
            if (count >= 4) {
                for (int i = 0; i < board.length; i++) { // row
                    int counter = 0;
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == 'O') {
                            counter++;
                        }
                    }
                    if (counter == 2) {
                        // pick the empty spot
                        for (int k = 0; k < board[i].length; k++) {
                            if (board[i][k] == ' ') {
                                x = i;
                                y = k;
                            }
                        }
                    }
                }
                for (int i = 0; i < board[0].length; i++) { // column
                    int counter = 0;
                    for (int j = 0; j < board.length; j++) {
                        if (board[j][i] == 'O') {
                            counter++;
                        }
                    }
                    if (counter == 2) {
                        // pick the empty spot
                        for (int k = 0; k < board[i].length; k++) {
                            if (board[k][i] == ' ') {
                                x = k;
                                y = i;
                            }
                        }
                    }
                }
                int num = 0; // diagonal \
                for (int i = 0; i < board.length; i++) {
                    if (board[i][i] == 'O') {
                        num++;
                    }
                }
                if (num == 2) {
                    for (int i = 0; i < board.length; i++) {
                        if (board[i][i] == ' ') {
                            x = i;
                            y = i;
                        }
                    }
                }
                int number = 0; // diagonal /
                for (int i = 0; i < board.length; i++) {
                    if (board[i][2 - i] == 'O') {
                        number++;
                    }
                }
                if (number == 2) {
                    for (int i = 0; i < board.length; i++) {
                        if (board[i][2 - i] == ' ') {
                            x = i;
                            y = 2 - i;
                        }
                    }
                }
            }
            if (x == 3) { // defend
                for (int i = 0; i < board.length; i++) { // row
                    int counter = 0;
                    for (int j = 0; j < board[i].length; j++) {
                        if (board[i][j] == 'X') {
                            counter++;
                        }
                    }
                    if (counter == 2) {
                        // pick the empty spot
                        for (int k = 0; k < board[i].length; k++) {
                            if (board[i][k] == ' ') {
                                x = i;
                                y = k;
                            }
                        }
                    }
                }
                for (int i = 0; i < board[0].length; i++) { // column
                    int counter = 0;
                    for (int j = 0; j < board.length; j++) {
                        if (board[j][i] == 'X') {
                            counter++;
                        }
                    }
                    if (counter == 2) {
                        // pick the empty spot
                        for (int k = 0; k < board[i].length; k++) {
                            if (board[k][i] == ' ') {
                                x = k;
                                y = i;
                            }
                        }
                    }
                }
                int num2 = 0; // diagonal \
                for (int i = 0; i < board.length; i++) {
                    if (board[i][i] == 'X') {
                        num2++;
                    }
                }
                if (num2 == 2) {
                    for (int i = 0; i < board.length; i++) {
                        if (board[i][i] == ' ') {
                            x = i;
                            y = i;
                        }
                    }
                }
                int number2 = 0; // diagonal /
                for (int i = 0; i < board.length; i++) {
                    if (board[i][2 - i] == 'X') {
                        number2++;
                    }
                }
                if (number2 == 2) {
                    for (int i = 0; i < board.length; i++) {
                        if (board[i][2 - i] == ' ') {
                            x = i;
                            y = 2 - i;
                        }
                    }
                }
                if (x == 3) { // random; ***ideally want it to try and win
                    x = random.nextInt(3);
                    y = random.nextInt(3);
                }
            }
        }
        if (board[x][y] == ' ') {
            board[x][y] = 'O';
            count++;
            int xc = SIZE / 5 + y * SIZE;
            int yc = 4 * SIZE / 5 + x * SIZE;
            g.drawString("O", xc, yc);
            print();
        } else {
            askComputer();
        }
    }

    public static void checkBoard() {
        if (count >= 5) {
            for (int i = 0; i < board.length; i++) { // across
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][1] != ' ') {
                    gameOver = true;
                    if (board[i][1] == 'X') {
                        if (comp) {
                            System.out.println("You win!");
                            g.setColor(Color.GREEN);
                        } else {
                            System.out.println("Player 1 wins!");
                            g.setColor(Color.BLUE);
                        }
                        int xc1 = SIZE / 5 + 0 * SIZE;
                        int xc2 = SIZE / 5 + 1 * SIZE;
                        int xc3 = SIZE / 5 + 2 * SIZE;
                        int yc = 4 * SIZE / 5 + i * SIZE;
                        g.drawString("X", xc1, yc);
                        g.drawString("X", xc2, yc);
                        g.drawString("X", xc3, yc);
                    } else {
                        if (comp) {
                            System.out.println("Computer wins.");
                            g.setColor(Color.RED);
                        } else {
                            System.out.println("Player 2 wins!");
                            g.setColor(Color.BLUE);
                        }
                        g.setColor(Color.RED);
                        int xc1 = SIZE / 5 + 0 * SIZE;
                        int xc2 = SIZE / 5 + 1 * SIZE;
                        int xc3 = SIZE / 5 + 2 * SIZE;
                        int yc = 4 * SIZE / 5 + i * SIZE;
                        g.drawString("O", xc1, yc);
                        g.drawString("O", xc2, yc);
                        g.drawString("O", xc3, yc);
                        // System.out.println("Computer wins.");
                    }
                }
            }
            for (int j = 0; j < board[0].length; j++) { // down
                if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[1][j] != ' ') {
                    gameOver = true;
                    if (board[1][j] == 'X') {
                        if (comp) {
                            System.out.println("You win!");
                            g.setColor(Color.GREEN);
                        } else {
                            System.out.println("Player 1 wins!");
                            g.setColor(Color.BLUE);
                        }
                        int xc = SIZE / 5 + j * SIZE;
                        int yc1 = 4 * SIZE / 5 + 0 * SIZE;
                        int yc2 = 4 * SIZE / 5 + 1 * SIZE;
                        int yc3 = 4 * SIZE / 5 + 2 * SIZE;
                        g.drawString("X", xc, yc1);
                        g.drawString("X", xc, yc2);
                        g.drawString("X", xc, yc3);
                        // System.out.println("You win!");
                    } else {
                        if (comp) {
                            System.out.println("Computer wins.");
                            g.setColor(Color.RED);
                        } else {
                            System.out.println("Player 2 wins!");
                            g.setColor(Color.BLUE);
                        }
                        int xc = SIZE / 5 + j * SIZE;
                        int yc1 = 4 * SIZE / 5 + 0 * SIZE;
                        int yc2 = 4 * SIZE / 5 + 1 * SIZE;
                        int yc3 = 4 * SIZE / 5 + 2 * SIZE;
                        g.drawString("O", xc, yc1);
                        g.drawString("O", xc, yc2);
                        g.drawString("O", xc, yc3);
                        // System.out.println("Computer wins.");
                    }
                }
            }
            if (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || // diagonal
                    (board[0][2] == board[1][1] && board[1][1] == board[2][0])) && board[1][1] != ' ') {
                gameOver = true;
                if (board[1][1] == 'X') {
                    if (comp) {
                        System.out.println("You win!");
                        g.setColor(Color.GREEN);
                    } else {
                        System.out.println("Player 1 wins!");
                        g.setColor(Color.BLUE);
                    }
                    g.drawString("X", SIZE / 5 + 1 * SIZE, 4 * SIZE / 5 + 1 * SIZE);
                    if (board[0][0] == 'X' && board[2][2] == 'X') {
                        g.drawString("X", SIZE / 5 + 0 * SIZE, 4 * SIZE / 5 + 0 * SIZE);
                        g.drawString("X", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + 2 * SIZE);
                    } else {
                        g.drawString("X", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + 0 * SIZE);
                        g.drawString("X", SIZE / 5 + 0 * SIZE, 4 * SIZE / 5 + 2 * SIZE);
                    }
                    // System.out.println("You win!");
                } else {
                    if (comp) {
                        System.out.println("Computer wins.");
                        g.setColor(Color.RED);
                    } else {
                        System.out.println("Player 2 wins!");
                        g.setColor(Color.BLUE);
                    }
                    g.drawString("O", SIZE / 5 + 1 * SIZE, 4 * SIZE / 5 + 1 * SIZE);
                    if (board[0][0] == 'O' && board[2][2] == 'O') {
                        g.drawString("O", SIZE / 5 + 0 * SIZE, 4 * SIZE / 5 + 0 * SIZE);
                        g.drawString("O", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + 2 * SIZE);
                    } else {
                        g.drawString("O", SIZE / 5 + 2 * SIZE, 4 * SIZE / 5 + 0 * SIZE);
                        g.drawString("O", SIZE / 5 + 0 * SIZE, 4 * SIZE / 5 + 2 * SIZE);
                    }
                    // System.out.println("Computer wins.");
                }
            }
            if (!gameOver && count >= 9) {
                gameOver = true;
                System.out.println("It's a draw.");
            }
        }
    }
}

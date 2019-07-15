// Anirudh Prakash

import java.util.*;

public class TicTacToeGame1 { // Text-based

    public static Scanner console;
    public static char[][] board;
    public static boolean gameOver;
    public static int count;
    public static Random random;

    public static void main(String[] args) {
        board = new char[3][3];
        console = new Scanner(System.in);
        random = new Random();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
        print();
        gameOver = false;
        count = 0;
        while (!gameOver) {
            askUser();
            checkBoard();
            if (gameOver) {
                break;
            }
            System.out.println("Computer turn.");
            askComputer();
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

    public static void askUser() {
        System.out.print("Choose where to place your X (row column): ");
        int x = console.nextInt();
        int y = console.nextInt();
        if (x >= board.length || y >= board.length) {
            gameOver = true;
        } else if (board[x][y] == ' ') {
            board[x][y] = 'X';
            count++;
            print();
        } else {
            System.out.println("Try again."); // do this if >= length too
            askUser();
        }
    }

    public static void askComputer() { // random location
        int x = random.nextInt(3);
        int y = random.nextInt(3);
        if (board[x][y] == ' ') {
            board[x][y] = 'O';
            count++;
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
                        System.out.println("You win!");
                    } else {
                        System.out.println("Computer wins.");
                    }
                }
            }
            for (int j = 0; j < board[0].length; j++) { // down
                if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[1][j] != ' ') {
                    gameOver = true;
                    if (board[1][j] == 'X') {
                        System.out.println("You win!");
                    } else {
                        System.out.println("Computer wins.");
                    }
                }
            }
            if (((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || // diagonal
                    (board[0][2] == board[1][1] && board[1][1] == board[2][0])) && board[1][1] != ' ') {
                gameOver = true;
                if (board[1][1] == 'X') {
                    System.out.println("You win!");
                } else {
                    System.out.println("Computer wins.");
                }
            }
            if (!gameOver && count >= 9) {
                gameOver = true;
                System.out.println("It's a draw.");
            }
        }
    }
}

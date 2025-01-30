package minesweeper.view;

import java.util.Scanner;

public class GameView {
    private final Scanner sc = new Scanner(System.in);

    public void displayMessage(Object message) {
        System.out.println(message);
    }

    public String getInput() {
        return sc.nextLine().trim();
    }

    public void displayBoard(char[][] board) {
        System.out.print(" |");
        for (int j = 1; j <= board[0].length; j++) {
            System.out.print(j);
        }
        System.out.println("|");

        System.out.println("-|" + "-".repeat(board[0].length) + "|");

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("|");
        }

        System.out.println("-|" + "-".repeat(board[0].length) + "|");
    }
}

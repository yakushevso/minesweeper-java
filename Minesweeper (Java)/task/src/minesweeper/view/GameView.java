package minesweeper.view;

import minesweeper.model.Cell;

import java.util.Scanner;

public class GameView {
    private final Scanner sc = new Scanner(System.in);

    public void displayBoard(Cell[][] board) {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.isMine()) {
                    System.out.print('X');
                } else {
                    if (cell.getAdjacentMines() > 0) {
                        System.out.print(cell.getAdjacentMines());
                    } else {
                        System.out.print('.');
                    }
                }
            }
            System.out.println();
        }
    }

    public void displayOutput(Object message) {
        System.out.println(message);
    }

    public int getInputNum() {
        while (true) {
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine();
                return input;
            } else {
                System.out.println(Messages.INPUT_NUM_ERROR);
                sc.nextLine();
            }
        }
    }
}

package minesweeper.view;

import minesweeper.model.Cell;

public class GameView {

    public void displayBoard(Cell[][] board) {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (cell.isMine()) {
                    System.out.print('x');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
}

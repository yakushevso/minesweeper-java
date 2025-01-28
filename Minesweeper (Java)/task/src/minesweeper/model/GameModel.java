package minesweeper.model;

import java.util.Random;

public class GameModel {
    private int rows;
    private int cols;
    private int mines;
    private Cell[][] board;

    public void initializeBoard(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;

        board = new Cell[rows][cols];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void placeMines() {
        Random random = new Random();

        for (int i = 0; i < mines; i++) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
            } else {
                i--;
            }
        }
    }
}

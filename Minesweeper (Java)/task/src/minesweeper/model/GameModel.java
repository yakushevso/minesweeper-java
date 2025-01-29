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
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void placeMines() {
        Random random = new Random();

        if (mines > rows * cols) {
            mines = rows * cols;
        }

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

    public void calculateAdjacentMines() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!board[i][j].isMine()) {
                    board[i][j].setAdjacentMines(countMinesAround(i, j));
                }
            }
        }
    }

    private int countMinesAround(int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;

                if (newRow >= 0 && newCol >= 0 && newRow < rows && newCol < cols
                        && board[newRow][newCol].isMine()) {
                    count++;
                }
            }
        }

        return count;
    }
}

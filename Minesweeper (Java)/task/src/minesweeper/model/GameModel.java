package minesweeper.model;

import java.util.Random;

public class GameModel {
    private int rows;
    private int cols;
    private Cell[][] board;
    private boolean isGameOver;
    private boolean isWin;

    public void initializeBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        board = new Cell[rows][cols];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public boolean isValidMineCount(int count) {
        return count > 0 && count <= rows * cols;
    }

    public void placeRandomMines(int mines) {
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

    public char[][] getBoardView() {
        char[][] boardView = new char[rows][cols];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isOpen()) {
                    if (board[i][j].isMine()) {
                        boardView[i][j] = 'X';
                    } else if (board[i][j].getMinesAround() > 0) {
                        boardView[i][j] = (char) ('0' + board[i][j].getMinesAround());
                    } else {
                        boardView[i][j] = '/';
                    }
                } else {
                    if (board[i][j].isFlagged()) {
                        boardView[i][j] = '*';
                    } else {
                        boardView[i][j] = '.';
                    }
                }
            }
        }

        return boardView;
    }

    public void calculateMinesAround() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isMine()) {
                    int count = countMinesAround(i, j);

                    if (count > 0) {
                        board[i][j].setMinesAround(count);
                        board[i][j].setOpen(true);
                    }
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

    public boolean isValidCoordinates(int x, int y) {
        return x <= rows && x > 0 && y <= cols && y > 0;
    }

    public boolean isCellOpen(int x, int y) {
        return board[x][y].isOpen();
    }

    public void toggleFlag(int x, int y) {
        board[x][y].setFlagged(!board[x][y].isFlagged());
    }

    public void checkState() {
        int flaggedMines = 0;
        int totalFlags = 0;
        int openCells = 0;
        int totalMines = 0;
        int totalCells = rows * cols;

        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (cell.isMine()) {
                    if (cell.isOpen()) {
                        isGameOver = true;
                        return;
                    }

                    totalMines++;

                    if (cell.isFlagged()) {
                        flaggedMines++;
                    }
                }
                if (cell.isFlagged()) {
                    totalFlags++;
                }
                if (cell.isOpen()) {
                    openCells++;
                }
            }
        }

        boolean allSafeCellsOpened = flaggedMines == 0 && totalMines == totalCells - (totalFlags + openCells);
        boolean allMinesFlagged = (flaggedMines == totalMines && totalFlags == totalMines);

        if (allSafeCellsOpened || allMinesFlagged) {
            isGameOver = true;
            isWin = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isWin() {
        return isWin;
    }

    public void openCell(int x, int y) {
        if (board[x][y].isMine()) {
            board[x][y].setOpen(true);
        } else {
            calculateFreeAround(x, y);
        }
    }

    public void calculateFreeAround(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return;
        }

        if (board[x][y].isOpen()) {
            return;
        }

        board[x][y].setOpen(true);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                calculateFreeAround(x + i, y + j);
            }
        }
    }

    public void openAllCells() {
        for (Cell[] cells : board) {
            for (Cell cell : cells) {
                if (!cell.isOpen()) {
                    cell.setOpen(true);
                }
            }
        }
    }
}

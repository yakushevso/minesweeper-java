package minesweeper.model;

public class Cell {
    private boolean isMine;

    public Cell() {
        this.isMine = false;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
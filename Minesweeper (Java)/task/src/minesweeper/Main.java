package minesweeper;

import minesweeper.controller.GameController;
import minesweeper.model.GameModel;
import minesweeper.view.GameView;

public class Main {
    public static void main(String[] args) {
        new GameController(new GameModel(), new GameView()).run();
    }
}

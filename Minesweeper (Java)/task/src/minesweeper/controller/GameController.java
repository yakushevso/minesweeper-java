package minesweeper.controller;

import minesweeper.model.GameModel;
import minesweeper.view.GameView;

public class GameController {
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        model.initializeBoard(9, 9, 10);
        model.placeMines();
        view.displayBoard(model.getBoard());
    }
}

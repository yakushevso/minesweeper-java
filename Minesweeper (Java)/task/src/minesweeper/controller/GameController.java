package minesweeper.controller;

import minesweeper.model.GameModel;
import minesweeper.view.GameView;
import minesweeper.view.Messages;

public class GameController {
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.displayOutput(Messages.HOW_MANY_MINES);
        int mines = view.getInputNum();

        model.initializeBoard(9, 9, mines);
        model.placeMines();
        model.calculateAdjacentMines();
        view.displayBoard(model.getBoard());
    }
}

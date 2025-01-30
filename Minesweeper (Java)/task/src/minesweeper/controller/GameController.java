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
        model.initializeBoard(9, 9);

        view.displayMessage(Messages.ENTER_MINE_COUNT);
        int mineCount = getMineCount();

        model.placeRandomMines(mineCount);
        model.calculateMinesAround();

        view.displayBoard(model.getBoardView());

        while (!model.isGameOver()) {
            view.displayMessage(Messages.ENTER_COORDINATES);
            int[] coord = getCoordinates();

            boolean move = model.makeMove(coord);

            if (move) {
                view.displayBoard(model.getBoardView());
            } else {
                view.displayMessage(Messages.CELL_HAS_NUMBER);
            }

            model.checkState();
        }

        view.displayMessage(Messages.CONGRATULATIONS);
    }

    private int getMineCount() {
        while (true) {
            try {
                int count = Integer.parseInt(view.getInput());

                if (model.isValidMineCount(count)) {
                    return count;
                } else {
                    view.displayMessage(Messages.INVALID_MINE_COUNT);
                }
            } catch (NumberFormatException e) {
                view.displayMessage(Messages.INVALID_NUMBER);
            }
        }
    }

    private int[] getCoordinates() {
        while (true) {
            try {
                String[] coord = view.getInput().split("\\s+");
                int x = Integer.parseInt(coord[0]);
                int y = Integer.parseInt(coord[1]);

                if (model.isValidCoordinates(x, y)) {
                    return new int[]{x, y};
                } else {
                    view.displayMessage(Messages.INVALID_COORDINATES);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                view.displayMessage(Messages.INVALID_COORDINATES);
            }
        }
    }
}

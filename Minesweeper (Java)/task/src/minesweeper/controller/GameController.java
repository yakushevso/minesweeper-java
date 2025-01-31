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
        int mines = getMineCount();

        model.placeRandomMines(mines);
        model.calculateMinesAround();

        while (!model.isGameOver()) {
            view.displayBoard(model.getBoardView());
            view.displayMessage(Messages.ENTER_COORDINATES);
            makeMove(getPlayerMove());
            model.checkState();
        }

        model.openAllCells();
        view.displayBoard(model.getBoardView());

        if (model.isWin()) {
            view.displayMessage(Messages.CONGRATULATIONS);
        } else {
            view.displayMessage(Messages.GAME_OVER);
        }
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

    private Move getPlayerMove() {
        while (true) {
            try {
                String[] coord = view.getInput().split("\\s", 3);
                int x = Integer.parseInt(coord[1]);
                int y = Integer.parseInt(coord[0]);
                String command = coord[2];

                if (model.isValidCoordinates(x, y)) {
                    return new Move(x - 1, y - 1, command);
                } else {
                    view.displayMessage(Messages.INVALID_COORDINATES);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                view.displayMessage(Messages.INVALID_COORDINATES);
            }
        }
    }

    private void makeMove(Move move) {
        int x = move.x();
        int y = move.y();

        if (!model.isCellOpen(x, y)) {
            switch (move.command()) {
                case "mine" -> model.toggleFlag(x, y);
                case "free" -> model.openCell(x, y);
                default -> view.displayMessage(Messages.INVALID_COMMAND);
            }
        } else {
            view.displayMessage(Messages.CELL_OPEN);
        }
    }
}

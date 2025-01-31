package minesweeper.view;

public enum Messages {
    CELL_OPEN("This cell has already been opened."),
    CONGRATULATIONS("Congratulations! You found all the mines!"),
    ENTER_COORDINATES("Set/unset mines marks or claim a cell as free:"),
    ENTER_MINE_COUNT("How many mines do you want on the field?"),
    GAME_OVER("You stepped on a mine and failed!"),
    INVALID_COORDINATES("Invalid coordinates! Please try again:"),
    INVALID_COMMAND("Invalid command! Please try again:"),
    INVALID_MINE_COUNT("Invalid mine count! Please try again:"),
    INVALID_NUMBER("That's not a number. Please enter a number:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

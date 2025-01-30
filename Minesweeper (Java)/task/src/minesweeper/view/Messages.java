package minesweeper.view;

public enum Messages {
    CELL_HAS_NUMBER("There is a number here!"),
    CONGRATULATIONS("Congratulations! You found all the mines!"),
    ENTER_COORDINATES("Set/delete mine marks (x and y coordinates):"),
    ENTER_MINE_COUNT("How many mines do you want on the field?"),
    INVALID_COORDINATES("Invalid coordinates! Please try again:"),
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

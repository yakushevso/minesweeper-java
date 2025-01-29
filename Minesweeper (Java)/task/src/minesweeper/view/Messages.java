package minesweeper.view;

public enum Messages {
    HOW_MANY_MINES("How many mines do you want on the field?"),
    INPUT_NUM_ERROR("That's not a number. Please enter a number:");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}

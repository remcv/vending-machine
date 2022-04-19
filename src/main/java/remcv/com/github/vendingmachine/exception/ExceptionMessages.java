package remcv.com.github.vendingmachine.exception;

public enum ExceptionMessages {
    FULL_MONEY_STORAGE("Money storage is full"),
    OUT_OF_ITEMS("Out of item"),
    INSUFFICIENT_CREDIT("Insufficient credit to buy the item"),
    OUT_OF_CHANGE("No change left in storage"),
    INCORRECT_ITEM_FILL("Incorrect instructions for filling the vending machine with items");

    // fields
    private final String message;

    // constructor
    private ExceptionMessages(String message) {
        this.message = message;
    }

    // methods
    public String getMessage() {
        return message;
    }
}

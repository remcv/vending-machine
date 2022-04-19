package remcv.com.github.vendingmachine.exception;

public enum ExceptionMessages {
    FULL_MONEY_STORAGE("Money storage is full"),
    OUT_OF_ITEMS("Out of item"),
    INSUFFICIENT_CREDIT("Insufficient credit to buy the item"),
    INVALID_SLOT("Invalid slot number"),
    OUT_OF_CHANGE("No change left in storage"),
    INCORRECT_ITEM_FILL("Incorrect instructions for filling the vending machine with items"),
    INVALID_PROPORTION("Invalid proportion value"),
    NEGATIVE_PRICES("Negative prices or free items are not allowed");

    // fields
    private final String message;

    // constructor
    ExceptionMessages(String message) {
        this.message = message;
    }

    // methods
    public String getMessage() {
        return message;
    }
}

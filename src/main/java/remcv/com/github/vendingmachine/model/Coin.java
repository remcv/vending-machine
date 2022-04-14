package remcv.com.github.vendingmachine.model;

public enum Coin {
    TEN_CENTS(10),
    TWENTY_CENTS(20),
    FIFTY_CENTS(50),
    ONE_EURO(100),
    TWO_EUROS(200);

    // fields
    private final int value;

    // methods
    public int getValue() {
        return value;
    }

    // constructor
    private Coin(int value) {
        this.value = value;
    }
}

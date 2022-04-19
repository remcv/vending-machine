package remcv.com.github.vendingmachine.exception.buy;

public class InvalidSlotException extends BuyException {

    // constructors
    public InvalidSlotException() {
    }

    public InvalidSlotException(String message) {
        super(message);
    }

    public InvalidSlotException(Exception e) {
        super(e);
    }

    public InvalidSlotException(String message, Exception e) {
        super(message, e);
    }
}

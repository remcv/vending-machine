package remcv.com.github.vendingmachine.exception.buy;

public class OutOfItemsException extends BuyException {

    // constructors
    public OutOfItemsException() {
    }

    public OutOfItemsException(String message) {
        super(message);
    }

    public OutOfItemsException(Exception e) {
        super(e);
    }

    public OutOfItemsException(String message, Exception e) {
        super(message, e);
    }
}

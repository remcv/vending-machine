package remcv.com.github.vendingmachine.exception.buy;

public class InsufficientCreditException extends BuyException {

    // constructors
    public InsufficientCreditException() {
    }

    public InsufficientCreditException(String message) {
        super(message);
    }

    public InsufficientCreditException(Exception e) {
        super(e);
    }

    public InsufficientCreditException(String message, Exception e) {
        super(message, e);
    }
}

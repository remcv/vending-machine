package remcv.com.github.vendingmachine.exception.buy;

import remcv.com.github.vendingmachine.exception.VendingMachineException;

public class BuyException extends VendingMachineException {
    public BuyException() {
    }

    public BuyException(String message) {
        super(message);
    }

    public BuyException(Exception e) {
        super(e);
    }

    public BuyException(String message, Exception e) {
        super(message, e);
    }
}

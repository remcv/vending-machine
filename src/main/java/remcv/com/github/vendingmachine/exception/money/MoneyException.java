package remcv.com.github.vendingmachine.exception.money;

import remcv.com.github.vendingmachine.exception.VendingMachineException;

public class MoneyException extends VendingMachineException {
    public MoneyException() {
    }

    public MoneyException(String message) {
        super(message);
    }

    public MoneyException(Exception e) {
        super(e);
    }

    public MoneyException(String message, Exception e) {
        super(message, e);
    }
}

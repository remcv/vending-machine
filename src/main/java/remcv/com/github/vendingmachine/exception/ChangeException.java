package remcv.com.github.vendingmachine.exception;

public class ChangeException extends VendingMachineException {

    // constructors
    public ChangeException() {
    }

    public ChangeException(String message) {
        super(message);
    }

    public ChangeException(Exception e) {
        super(e);
    }

    public ChangeException(String message, Exception e) {
        super(message, e);
    }
}

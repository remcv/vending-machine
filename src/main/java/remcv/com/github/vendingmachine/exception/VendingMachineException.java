package remcv.com.github.vendingmachine.exception;

public class VendingMachineException extends Exception {
    // constructors
    public VendingMachineException() {
        super();
    }

    public VendingMachineException(String message) {
        super(message);
    }

    public VendingMachineException(Exception e) {
        super(e);
    }

    public VendingMachineException(String message, Exception e) {
        super(message, e);
    }
}

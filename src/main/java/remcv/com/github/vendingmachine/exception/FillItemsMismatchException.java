package remcv.com.github.vendingmachine.exception;

public class FillItemsMismatchException extends VendingMachineException {

    // constructors
    public FillItemsMismatchException() {
    }

    public FillItemsMismatchException(String message) {
        super(message);
    }

    public FillItemsMismatchException(Exception e) {
        super(e);
    }

    public FillItemsMismatchException(String message, Exception e) {
        super(message, e);
    }
}

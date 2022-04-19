package remcv.com.github.vendingmachine.exception.money;

public class FullMoneyStorageException extends MoneyException {

    // constructors
    public FullMoneyStorageException() {
    }

    public FullMoneyStorageException(String message) {
        super(message);
    }

    public FullMoneyStorageException(Exception e) {
        super(e);
    }

    public FullMoneyStorageException(String message, Exception e) {
        super(message, e);
    }
}

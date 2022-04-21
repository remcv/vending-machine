package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;

import java.util.Collection;

/**
 * Interface that models behaviour of a credit service for a vending machine.
 *
 * @param <T> the data type for money
 * @param <U> the data type for credit
 */
public interface CreditService<T, U> {
    /**
     * Checks if there is free storage available for the money.
     *
     * @param money the money type to check the free storage for
     * @return true if there is free storage left
     */
    boolean checkMoneyStorageCapacity(T money);

    /**
     * Add money to storage.
     *
     * @param money the money {@code T} to be added
     * @throws FullMoneyStorageException if no free storage is left
     */
    void add(T money) throws FullMoneyStorageException;

    /**
     * Fill the money storage to the desired proportion.
     *
     * @param proportion the proportion to fill the money storage to
     */
    void fillMoneyStorage(double proportion);

    /**
     * Withdraw all money from storage and return them.
     *
     * @return all the money {@code T} from storage
     */
    Collection<Coin> withdrawAll();

    /**
     * Getter method for the client's credit.
     *
     * @return the credit available
     */
    U getCredit();

    /**
     * Setter method for the client's credit.
     *
     * @param credit the credit to set
     */
    void setCredit(U credit);

    /**
     * Convenience method to reset the credit.
     */
    void resetCredit();
}

package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.ChangeException;

import java.util.Collection;

public interface MoneyChange<T> {

    /**
     * Offer the client the change in money {@code T} and remove them from the vending machine's storage.
     *
     * @return the change for the client in the form of a collection of {@code T}.
     * @throws ChangeException if the vending machine doesn't have enough money to return the change.
     */
    Collection<T> getChange() throws ChangeException;
}

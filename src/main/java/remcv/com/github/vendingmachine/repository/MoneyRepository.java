package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;

import java.util.Collection;

public interface MoneyRepository<T> {
    boolean deposit(T money) throws FullMoneyStorageException;
    void fillStorage(double proportion);

    /**
     * Retrieve the collection of money of type {@code T} from the money storage.
     * This is required for computing the change for the client.
     *
     * @param money the type of money {@code T} you want to query from storage.
     * @return a collection of money of type {@code T} in the vending machine's storage.
     */
    Collection<T> getMoneySlot(T money);

    T withdrawOne(T money) throws ChangeException;
    Collection<T> withdrawAll();

    /**
     * The money storage can hold only a limited number of {@code T} objects per {@code Slot}.
     *
     * @return the maximum number of objects in a slot.
     */
    int getMoneyStorageCapacity();

    int computeFreeStorage(T money);
}

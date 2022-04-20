package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;

import java.util.Collection;

/**
 * An interface that defines repository behaviour for the generic {@code T}
 * object, representing the object modeling of the money entity. It assumes
 * that money instances are placed in different slots according to value.
 *
 * @param <T> the entity that models the type of money used
 */
public interface MoneyRepository<T> {
    /**
     * Deposit one instance of money {@code T} in the money storage.
     *
     * @param money the money instance to be deposited
     * @throws FullMoneyStorageException if the money storage is already full
     */
    void deposit(T money) throws FullMoneyStorageException;

    /**
     * Fill the money storage to the desired proportion. Note that an initial
     * fill of 1.0 would cause a {@code FullMoneyStorageException} at every
     * deposit.
     *
     * @param proportion the proportion to fill the money storage
     * @throws IllegalArgumentException if @param proportion is out of 0.0 - 1.0 range
     */
    void fillStorage(double proportion) throws IllegalArgumentException;

    /**
     * Retrieve the collection of money of type {@code T} from the money storage.
     * This is required for computing the change for the client.
     *
     * @param money the type of money {@code T} you want to query from storage.
     * @return a collection of money of type {@code T} in the vending machine's storage.
     */
    Collection<T> getMoneySlot(T money);

    /**
     * Retrieve an instance of the input money {@code T}. This method is used in
     * implementing the change business logic.
     *
     * @param money the type of money {@code T} you want to retrieve from storage
     * @return the instance of money {@code T} you want to retrieve
     * @throws ChangeException if there are not enough money in storage
     */
    T withdrawOne(T money) throws ChangeException;

    /**
     * Empty and recover all the money held in storage.
     *
     * @return a collection of all the money {@code T} held in storage
     */
    Collection<T> withdrawAll();

    /**
     * The money storage can hold only a limited number of {@code T} objects per
     * {@code Slot}.
     *
     * @return the maximum number of objects in a slot.
     */
    int getMoneyStorageCapacity();

    /**
     * Compute how many free {@code T} money instances are left in a slot.
     *
     * @param money the type of money you want to verify
     * @return the number of free {@code T} money instances left in a slot
     */
    int computeFreeStorage(T money);
}

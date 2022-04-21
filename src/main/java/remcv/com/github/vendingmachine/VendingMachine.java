package remcv.com.github.vendingmachine;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.money.MoneyException;

import java.util.Collection;
import java.util.List;

/**
 * Interface that models the behaviour of a vending machine that is composed
 * of {@code Slot}s that contain the items to be sold.
 *
 * @param <T> data type used to represent money
 * @param <U> data type used to represent items to be sold
 */
public interface VendingMachine<T, U> {
    // behaviour for user
    /**
     * Insert money in the vending machine.
     *
     * @param money instance of money inserted
     * @throws MoneyException if the money storage is full
     */
    void insertMoney(T money) throws MoneyException;

    /**
     * Buy an item from the vending machine that is located in a specific slot.
     *
     * @param slotNumber slot id
     * @return the bought item
     * @throws BuyException if the slot id is invalid, there is insufficient
     * credit or the wanted item is out of stock
     */
    U buy(short slotNumber) throws BuyException;

    /**
     * Compute and return the change to the client.
     *
     * @return a collection of money {@code T} instances
     * @throws ChangeException if the vending machine doesn't have enough money
     * in stock to return the change
     */
    Collection<T> getChange() throws ChangeException;

    // behaviour for maintenance
    /**
     * Fill the money storage of the vending machine to the desired proportion.
     *
     * @param proportion the proportion to fill the money storage to
     */
    void fillMoneyStorage(double proportion);

    /**
     * Empty the vending machine's money storage and returns it.
     *
     * @return a collection of all the money {@code T} instances stored in the
     * machine
     */
    Collection<T> withdrawAllMoney();

    /**
     * Fill the machine's {@Code Slot}s with the provided items.
     *
     * @param slotItems a {@code List} of items to be put in each item slot
     * @throws FillItemsMismatchException if the provided slot items do not
     * match the existing machine item slots
     */
    void fillItemStorage(List<U> slotItems) throws FillItemsMismatchException;

    /**
     * Empty the vending machine of items and return them.
     *
     * @return a collection of all the items of type {@code U} stored in the
     * machine
     */
    Collection<U> emptyItemStorage();
}

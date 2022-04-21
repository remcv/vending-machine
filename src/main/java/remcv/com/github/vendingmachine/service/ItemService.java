package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;

import java.util.Collection;
import java.util.List;

/**
 * Interface that models the behaviour of an item service for a vending
 * machine.
 *
 * @param <T> the data type for the service item
 */
public interface ItemService<T> {
    /**
     * Buy an item.
     *
     * @param slotNumber the slot id where the item is stored
     * @return the item
     * @throws BuyException if the slot number is invalid, the credit is
     * insufficient or the item slot is empty
     */
    T buy(short slotNumber) throws BuyException;

    /**
     * Fills all the slots with the drink provided in the slotItems list.
     *
     * @param slotItems a list of items to fill the slots with. One item per
     *                  slot
     * @throws FillItemsMismatchException if the slotItems size doesn't match
     * the number of slots
     */
    void fillItemStorage(List<T> slotItems) throws FillItemsMismatchException;

    /**
     * Removes all items from their slot and returns them.
     *
     * @return a collection of all items contained in a slot
     */
    Collection<T> emptyItemStorage();
}

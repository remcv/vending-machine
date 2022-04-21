package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InvalidSlotException;

import java.util.Collection;
import java.util.List;

/**
 * An interface that defines repository behaviour for the generic {@code T}
 * object, representing the object modeling the item entity. It assumes
 * that the item instances are placed in different slots according to type and
 * price.
 *
 * @param <T> the entity that models the sold item
 */
public interface ItemRepository<T> {
    /**
     * Removes one item from its slot and returns it.
     *
     * @param slotNumber the id used for identifying the slot keeping the item
     * @return the sold item
     * @throws BuyException if the slot number is invalid, the credit is
     * insufficient or the item slot is empty
     */
    T removeOne(short slotNumber) throws BuyException;

    /**
     * Removes all items from their slot and returns them.
     *
     * @return a collection of all items contained in a slot
     */
    Collection<T> removeAll();

    /**
     * Fills all the slots with the drink provided in the slotItems list.
     *
     * @param slotItems a list of items to fill the slots with. One item per
     *                  slot
     * @throws FillItemsMismatchException if the slotItems size doesn't match
     * the number of slots
     */
    void fill(List<T> slotItems) throws FillItemsMismatchException;

    /**
     * Return the number of slots.
     *
     * @return number of slots
     */
    short getNumberOfSlots();

    /**
     * Recover the price of an item placed in a particular slot.
     *
     * @param slotNumber the id for the slot
     * @return the price of items in the selected slot
     * @throws InvalidSlotException if the slot doesn't exist
     */
    int getSlotPrice(short slotNumber) throws InvalidSlotException;
}

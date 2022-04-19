package remcv.com.github.vendingmachine.model;

import remcv.com.github.vendingmachine.exception.buy.OutOfItemsException;

import java.util.Collection;

public interface Slot<T, U> {
    U getPrice();
    void setPrice(U price);

    /**
     * Removes an item from the current slot.
     *
     * @return an item from the slot.
     * @throws OutOfItemsException when the slot is empty.
     */
    T getItem() throws OutOfItemsException;

    /**
     * Shows how many items are currently in the slot.
     *
     * @return the number of items in the slot, as a {@code short}.
     */
    short getNumberOfItems();

    /**
     * Defines the maximum number of items a slot can contain.
     *
     * @return the maximum number of items per slot, as a {@code short}.
     */
    short getMaxItems();

    Collection<T> emptySlot();
    void fillSlot(T item);
}

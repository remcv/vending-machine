package remcv.com.github.vendingmachine.model;

import java.util.Collection;

public interface Slot<T, U> {
    U getPrice();
    void setPrice(U price);

    /**
     * Removes an item from the current slot.
     *
     * @return an item from the slot.
     * @throws Exception when the slot is empty. TODO
     */
    T getItem() throws Exception;

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
    short getMaxItems(); // TODO check alternatives VendingMachine

    Collection<T> emptySlot();
    void fillSlot(T item);
}

package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InvalidSlotException;

import java.util.Collection;
import java.util.List;

public interface ItemRepository<T> {
    T removeOne(short slotNumber) throws BuyException;
    Collection<T> removeAll();
    void fill(List<T> slotItems) throws FillItemsMismatchException;
    short getNumberOfSlots();
    int getSlotPrice(short slotNumber) throws InvalidSlotException;
}

package remcv.com.github.vendingmachine.repository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository<T> {
    T removeOne(short slotNumber) throws Exception; // TODO custom exception
    Collection<T> removeAll();
    void fill(List<T> slotItems) throws Exception;  // TODO custom exception
    short getNumberOfSlots();
    int getSlotPrice(short slotNumber);
}

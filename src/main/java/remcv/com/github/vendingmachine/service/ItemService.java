package remcv.com.github.vendingmachine.service;

import java.util.Collection;
import java.util.List;

public interface ItemService<T> {
    T buy(short slotNumber) throws Exception;
    void fillItemStorage(List<T> slotItems) throws Exception;
    Collection<T> emptyItemStorage();
}

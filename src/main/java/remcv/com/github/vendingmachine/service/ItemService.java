package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;

import java.util.Collection;
import java.util.List;

public interface ItemService<T> {
    T buy(short slotNumber) throws BuyException;
    void fillItemStorage(List<T> slotItems) throws FillItemsMismatchException;
    Collection<T> emptyItemStorage();
}

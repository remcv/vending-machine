package remcv.com.github.vendingmachine;

import remcv.com.github.vendingmachine.model.Drink;

import java.util.Collection;

public interface VendingMachine<T, U> {
    // capacity info
    int getMaxMoneyPerType();
    short getMaxDrinksPerSlot();
    short getNumberOfSlots(); // TODO ??? is it really necessary

    // behaviour for user
    T insertMoney(T money) throws Exception;
    U buy(short slotNumber); // TODO Credit?
    Collection<T> getChange();

    // behaviour for maintenance
    void fillMoneyStorage(double proportion);
    Collection<T> withdrawAllMoney();
    void fillItemStorage();
    Collection<U> emptyDrinksStorage();
}

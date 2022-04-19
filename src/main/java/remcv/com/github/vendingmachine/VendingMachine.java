package remcv.com.github.vendingmachine;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.money.MoneyException;

import java.util.Collection;

public interface VendingMachine<T, U> {
    // behaviour for user
    T insertMoney(T money) throws MoneyException;
    U buy(short slotNumber) throws BuyException;
    Collection<T> getChange() throws ChangeException;

    // behaviour for maintenance
    void fillMoneyStorage(double proportion);
    Collection<T> withdrawAllMoney();
    void fillItemStorage() throws FillItemsMismatchException;
    Collection<U> emptyItemStorage();
}

package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;

import java.util.Collection;

public interface CreditService<T, U> {
    boolean checkMoneyStorageCapacity(T money);
    void add(T money) throws FullMoneyStorageException;
    void fillMoneyStorage(double proportion);
    Collection<Coin> withdrawAll();
    U getCredit();
    void setCredit(U credit);
    void resetCredit();
}

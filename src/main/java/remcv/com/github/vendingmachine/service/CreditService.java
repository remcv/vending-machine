package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;

public interface CreditService<T, U> {
    boolean checkMoneyStorageCapacity(T money);
    void add(T money) throws FullMoneyStorageException;
    U getCredit();
    void setCredit(U credit);
    void resetCredit();
}

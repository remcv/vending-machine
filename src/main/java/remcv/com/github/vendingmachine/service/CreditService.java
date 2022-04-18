package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;

public interface CreditService<T, U> {
    boolean checkMoneyStorageCapacity(T money);
    void add(T money) throws Exception; // TODO custom exception
    U getCredit();
    void setCredit(U credit);
    void resetCredit();
}

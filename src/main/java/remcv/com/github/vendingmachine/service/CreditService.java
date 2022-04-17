package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;

public interface CreditService<T> {
    boolean checkMoneyStorageCapacity(T money);
    void add(T money) throws Exception; // TODO custom exception
    void resetCredit();
}

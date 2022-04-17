package remcv.com.github.vendingmachine.repository;

import java.util.Collection;

public interface MoneyRepository<T> {
    boolean deposit(T money) throws Exception; // TODO `already full` exception
    void fillStorage(double proportion);
    T withdrawOne() throws Exception; // TODO `no coins in stock` exception
    Collection<T> withdrawAll();
    void emptyStorage();

    int getMoneyStorageCapacity();
    int computeFreeStorage(T money);
}

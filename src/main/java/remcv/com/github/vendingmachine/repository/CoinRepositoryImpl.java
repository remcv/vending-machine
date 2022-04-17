package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.model.Coin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoinRepositoryImpl implements MoneyRepository<Coin> {
    // fields
    private static final double INITIAL_FILL_PROPORTION = 0.6;
    private final Map<Coin, Queue<Coin>> coinStorage;

    // constructor
    public CoinRepositoryImpl() {
        coinStorage = new HashMap<>();

        for (Coin coin : Coin.values()) {
            coinStorage.put(coin, new ArrayDeque<>(getMoneyStorageCapacity()));
        }

        fillStorage(INITIAL_FILL_PROPORTION);
    }

    // methods
//    @Override
//    public void deposit(Coin[] coins) throws Exception {  // TODO `already full` exception
//        // check for null argument
//        if (Objects.isNull(coins)) {
//            throw new Exception("No coins were inserted");  // TODO `no coins inserted` exception
//        }
//
//        // check for coin capacity
//
//        // deposit coins into storage
//        for (Coin coin : coins) {
//            coinStorage.get(coin).size();
//        }
//    }

    @Override
    public boolean deposit(Coin money) throws Exception {
        return false;
    }

    @Override
    public void fillStorage(double proportion) {
        emptyStorage();

        for (Coin coin : Coin.values()) {
            coinStorage.get(coin).addAll(generateCoins(coin, getMoneyStorageCapacity()));
        }
    }

    @Override
    public Coin withdrawOne() throws Exception {
        return null;
    }

    @Override
    public Collection<Coin> withdrawAll() {

        return null;
    }

    @Override
    public void emptyStorage() {
        for (Coin coin : Coin.values()) {
            coinStorage.get(coin).clear();
        }
    }

    @Override
    public int computeFreeStorage(Coin money) {
        return coinStorage.get(money).size();
    }

    @Override
    public int getMoneyStorageCapacity() {
        return 150;
    }

    private Collection<Coin> generateCoins(Coin coinType, long limit) {
        return Stream.generate(() -> coinType)
                .limit(limit)
                .collect(Collectors.toList());
    }

    private boolean checkCoinCapacity(Coin[] coins) {
        return false; // TODO
    }
}

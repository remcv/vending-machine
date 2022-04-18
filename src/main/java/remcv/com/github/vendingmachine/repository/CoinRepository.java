package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.model.Coin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CoinRepository implements MoneyRepository<Coin> {
    // fields
    private static final double INITIAL_FILL_PROPORTION = 0.6;
    private final Map<Coin, Queue<Coin>> coinStorage;

    // constructor
    public CoinRepository() {
        coinStorage = new HashMap<>();

        for (Coin coin : Coin.values()) {
            coinStorage.put(coin, new ArrayDeque<>(getMoneyStorageCapacity()));
        }

        fillStorage(INITIAL_FILL_PROPORTION);
    }

    // methods
    @Override
    public boolean deposit(Coin coin) throws Exception {
        // check for storage availability
        if (coinStorage.get(coin).size() >= getMoneyStorageCapacity()) {
            throw new Exception("No coin storage available for " + coin.name());
        // deposit coin
        } else {
            return coinStorage.get(coin).add(coin);
        }
    }

    @Override
    public void fillStorage(double proportion) {
        // first empty storage
        coinStorage.forEach((k, v) -> v.clear());

        // determine the number of coins for each slot, respecting the desired fill proportion
        long numberOfCoins = Math.round(INITIAL_FILL_PROPORTION * getMoneyStorageCapacity());

        // fill each coin slot to established proportion
        for (Coin coin : Coin.values()) {
            coinStorage.get(coin).addAll(generateCoins(coin, numberOfCoins));
        }
    }

    @Override
    public Coin withdrawOne(Coin coin) throws Exception {
        try {
            return coinStorage.get(coin).remove();
        } catch (NoSuchElementException e) {
            throw new Exception(e);  // TODO custom exception + message (or from custom exception)
        }
    }

    @Override
    public Collection<Coin> withdrawAll() {
        Collection<Coin> coins = new ArrayList<>();
        coinStorage.forEach((k, v) -> coins.addAll(v));
        coinStorage.forEach((k, v) -> v.clear());
        return coins;
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
}

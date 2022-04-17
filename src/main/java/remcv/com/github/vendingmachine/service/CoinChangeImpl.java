package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.CoinRepositoryImpl;

import java.util.Objects;

public class CoinChangeImpl {

    public Coin[] computeChange(Coin[] inputCoins, CoinRepositoryImpl coinRepository, int price) throws Exception { // TODO exception input < price
        if (coinsToPrice(inputCoins) < price) {
            throw new Exception("Input is lower than price");
        } else {
            return null;
        }
    }

    int coinsToPrice(Coin[] coins) throws Exception {  // TODO handle null case
        // handle null case
        if (Objects.isNull(coins)) {
            throw new Exception("No coins were inserted");
        }

        // compute price
        int price = 0;
        for (Coin coin : coins) {
            price += coin.getValue();
        }

        return price;
    }
}

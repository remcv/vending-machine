package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoinChange implements MoneyChange<Coin> {
    // fields
    private final CreditService<Coin, Integer> coinCreditService;
    private final MoneyRepository<Coin> coinRepository;

    // constructor
    public CoinChange(CreditService<Coin, Integer> coinCreditService, MoneyRepository<Coin> coinRepository) {
        this.coinCreditService = coinCreditService;
        this.coinRepository = coinRepository;
    }

    // methods
    @Override
    public Collection<Coin> getChange() throws Exception {
        // get remaining credit
        int credit = coinCreditService.getCredit();

        // compute and withdraw corresponding coins
        List<Coin> coinsForChange = new ArrayList<>();

        for (Coin c : Coin.values()) {
            while (credit % c.getValue() > 0) {
                try {
                    coinsForChange.add(coinRepository.withdrawOne(c));
                    credit -= c.getValue();
                } catch (Exception e) {
                    if (c.equals(Coin.TEN_CENTS))
                    throw new Exception("Not enough money left for change");  // TODO custom exception
                }
            }
        }

        return coinsForChange;
    }
}

package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoinChangeService implements MoneyChangeService<Coin> {
    // fields
    private final CreditService<Coin, Integer> coinCreditService;
    private final MoneyRepository<Coin> coinRepository;

    // constructor
    public CoinChangeService(CreditService<Coin, Integer> coinCreditService, MoneyRepository<Coin> coinRepository) {
        this.coinCreditService = coinCreditService;
        this.coinRepository = coinRepository;
    }

    // methods
    @Override
    public Collection<Coin> getChange() throws ChangeException {
        // get remaining credit
        int credit = coinCreditService.getCredit();

        // compute and withdraw corresponding coins
        List<Coin> coinsForChange = new ArrayList<>();

        for (Coin c : new Coin[] {Coin.TWO_EUROS, Coin.ONE_EURO, Coin.FIFTY_CENTS, Coin.TWENTY_CENTS, Coin.TEN_CENTS}) {
            while (credit / c.getValue() > 0) {
                try {
                    coinsForChange.add(coinRepository.withdrawOne(c));
                    credit -= c.getValue();
                } catch (Exception e) {
                    if (c.equals(Coin.TEN_CENTS))
                    throw new ChangeException(ExceptionMessages.OUT_OF_CHANGE.getMessage());
                }
            }
        }

        // reset the credit of the vending machine
        coinCreditService.resetCredit();

        return coinsForChange;
    }
}

package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

public class CoinCreditService implements CreditService<Coin, Integer> {
    // fields
    private int credit;
    private final MoneyRepository<Coin> coinRepository;

    // constructor
    public CoinCreditService(MoneyRepository<Coin> coinRepository) {
        this.coinRepository = coinRepository;
    }

    // methods
    @Override
    public boolean checkMoneyStorageCapacity(Coin money) {
        return coinRepository.computeFreeStorage(money) > 0;
    }

    @Override
    public void add(Coin money) throws FullMoneyStorageException {
        if (checkMoneyStorageCapacity(money)) {
            // increase credit and deposit money
            credit += money.getValue();
            coinRepository.deposit(money);
        } else {
            throw new FullMoneyStorageException(ExceptionMessages.FULL_MONEY_STORAGE.getMessage());
        }
    }

    @Override
    public void resetCredit() {
        credit = 0;
    }

    @Override
    public Integer getCredit() {
        return credit;
    }

    @Override
    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

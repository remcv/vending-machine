package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

public class CoinCreditService implements CreditService<Coin> {
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
    public void add(Coin money) throws Exception {
        if (checkMoneyStorageCapacity(money)) {
            // increase credit and deposit money
            credit += money.getValue();
            coinRepository.deposit(money);
        } else {
            throw new Exception("Money storage is full for " + money.name());  // TODO replace with custom exception
        }
    }

    @Override
    public void resetCredit() {
        credit = 0;
    }
}

package remcv.com.github.vendingmachine;

import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.money.MoneyException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.service.CreditService;
import remcv.com.github.vendingmachine.service.ItemService;
import remcv.com.github.vendingmachine.service.MoneyChangeService;

import java.util.Collection;
import java.util.List;

public class VendingMachineImpl implements VendingMachine<Coin, Drink> {
    // fields
    private final ItemService<Drink> drinkService;
    private final MoneyChangeService<Coin> coinChangeService;
    private final CreditService<Coin, Integer> coinCreditService;

    // constructor
    public VendingMachineImpl(ItemService<Drink> drinkService, MoneyChangeService<Coin> coinChangeService,
                              CreditService<Coin, Integer> coinCreditService) {
        this.drinkService = drinkService;
        this.coinChangeService = coinChangeService;
        this.coinCreditService = coinCreditService;
    }

    // methods
    @Override
    public void insertMoney(Coin coin) throws MoneyException {
        coinCreditService.add(coin);
    }

    @Override
    public Drink buy(short slotNumber) throws BuyException {
        return drinkService.buy(slotNumber);
    }

    @Override
    public Collection<Coin> getChange() throws ChangeException {
        return coinChangeService.getChange();
    }

    @Override
    public void fillMoneyStorage(double proportion) {
        coinCreditService.fillMoneyStorage(proportion);
    }

    @Override
    public Collection<Coin> withdrawAllMoney() {
        return coinCreditService.withdrawAll();
    }

    @Override
    public void fillItemStorage(List<Drink> slotDrinks) throws FillItemsMismatchException {
        drinkService.fillItemStorage(slotDrinks);
    }

    @Override
    public Collection<Drink> emptyItemStorage() {
        return drinkService.emptyItemStorage();
    }
}

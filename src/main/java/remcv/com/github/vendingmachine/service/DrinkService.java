package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InsufficientCreditException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.repository.ItemRepository;

import java.util.Collection;
import java.util.List;

public class DrinkService implements ItemService<Drink> {
    // fields
    private final CreditService<Coin, Integer> coinCreditService;
    private final ItemRepository<Drink, Integer> drinkRepository;

    // constructor
    public DrinkService(CreditService<Coin, Integer> coinCreditService, ItemRepository<Drink, Integer> drinkRepository) {
        this.coinCreditService = coinCreditService;
        this.drinkRepository = drinkRepository;
    }

    // methods
    @Override
    public Drink buy(short slotNumber) throws BuyException {
        // check for insufficient credit
        int price = drinkRepository.getSlotPrice(slotNumber);
        int credit = coinCreditService.getCredit();

        if ((price == 0) || (credit < price)) {
            throw new InsufficientCreditException(ExceptionMessages.INSUFFICIENT_CREDIT.getMessage());
        }

        // remove the drink from storage
        Drink drink = drinkRepository.removeOne(slotNumber);

        // update credit
        coinCreditService.setCredit(credit - price);

        return drink;
    }

    @Override
    public void fillItemStorage(List<Drink> slotItems) throws FillItemsMismatchException {
        drinkRepository.fill(slotItems);
    }

    @Override
    public Collection<Drink> emptyItemStorage() {
        return drinkRepository.removeAll();
    }
}

package remcv.com.github.vendingmachine.service;

import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.repository.ItemRepository;

import java.util.Collection;
import java.util.List;

public class DrinkService implements ItemService<Drink> {
    // fields
    private final CreditService<Coin, Integer> coinCreditService;
    private final ItemRepository<Drink> drinkRepository;

    // constructor
    public DrinkService(CreditService<Coin, Integer> coinCreditService, ItemRepository<Drink> drinkRepository) {
        this.coinCreditService = coinCreditService;
        this.drinkRepository = drinkRepository;
    }

    // methods
    @Override
    public Drink buy(short slotNumber) throws Exception {  // TODO invalid slot number + other exceptions
        // check credit (0 or insufficient)
        int price = drinkRepository.getSlotPrice(slotNumber);
        int credit = coinCreditService.getCredit();

        if ((price == 0) || (credit < price)) {
            throw new Exception("Not enough credit");
        }

        // remove the drink from storage
        Drink drink = drinkRepository.removeOne(slotNumber);  // TODO throws exception

        // update credit
        coinCreditService.setCredit(credit - price);

        return drink;
    }

    @Override
    public void fillItemStorage(List<Drink> slotItems) throws Exception {
        drinkRepository.fill(slotItems);
    }

    @Override
    public Collection<Drink> emptyItemStorage() {
        return drinkRepository.removeAll();
    }
}

package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InvalidSlotException;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.model.DrinkSlot;
import remcv.com.github.vendingmachine.model.Slot;

import java.util.*;

public class DrinkRepository implements ItemRepository<Drink> {
    // fields
    private static final short NUMBER_OF_SLOTS = 50;
    private static final short SLOT_CAPACITY = 8;
    private final Map<Integer, Slot<Drink, Integer>> drinkStorage;

    // constructor
    public DrinkRepository() {
        drinkStorage = new HashMap<>(NUMBER_OF_SLOTS);
        for (int i = 1; i <= NUMBER_OF_SLOTS; ++i) {
            drinkStorage.put(i, new DrinkSlot(SLOT_CAPACITY, generateDrinkPrice()));
        }
    }

    // methods
    @Override
    public Drink removeOne(short slotNumber) throws BuyException {
        if (slotNumber > getNumberOfSlots()) {
            throw new InvalidSlotException(ExceptionMessages.INVALID_SLOT.getMessage());
        }
        return drinkStorage.get((int) slotNumber).getItem();
    }

    @Override
    public Collection<Drink> removeAll() {
        Collection<Drink> drinks = new ArrayList<>();

        for (Slot<Drink, Integer> slot : drinkStorage.values()) {
            drinks.addAll(slot.emptySlot());
        }

        return drinks;
    }

    @Override
    public void fill(List<Drink> slotItems) throws FillItemsMismatchException {
        if (slotItems.size() != drinkStorage.size()) {
            throw new FillItemsMismatchException(ExceptionMessages.INCORRECT_ITEM_FILL.getMessage());
        } else {
            drinkStorage.forEach((n, slot) -> slot.fillSlot(slotItems.get(n-1)));
        }
    }

    @Override
    public short getNumberOfSlots() {
        return NUMBER_OF_SLOTS;
    }

    @Override
    public int getSlotPrice(short slotNumber) throws InvalidSlotException {
        if (slotNumber > getNumberOfSlots()) {
            throw new InvalidSlotException(ExceptionMessages.INVALID_SLOT.getMessage());
        }
        return drinkStorage.get((int) slotNumber).getPrice();
    }

    private int generateDrinkPrice() {
        int[] prices = { 100, 120, 200, 240, 270, 310, 390, 400, 500 };

        return prices[new Random().nextInt(prices.length)];
    }
}

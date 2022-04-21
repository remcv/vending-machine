package remcv.com.github.vendingmachine.repository;

import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InvalidSlotException;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.model.DrinkSlot;
import remcv.com.github.vendingmachine.model.Slot;

import java.util.*;

public class DrinkRepository implements ItemRepository<Drink, Integer> {
    // fields
    private final short numberOfSlots;
    private final Map<Integer, Slot<Drink, Integer>> drinkStorage;

    // constructor
    public DrinkRepository(short numberOfSlots, short slotCapacity) {
        // initialize member variables
        this.numberOfSlots = numberOfSlots;

        drinkStorage = new HashMap<>(numberOfSlots);
        for (int i = 1; i <= numberOfSlots; ++i) {
            drinkStorage.put(i, new DrinkSlot(slotCapacity, generateDrinkPrice()));
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
    public Slot<Drink, Integer> getSlot(short slotNumber) throws InvalidSlotException {
        if (slotNumber > getNumberOfSlots()) {
            throw new InvalidSlotException(ExceptionMessages.INVALID_SLOT.getMessage());
        }
        return drinkStorage.get((int) slotNumber);
    }

    @Override
    public short getNumberOfSlots() {
        return numberOfSlots;
    }

    @Override
    public int getSlotPrice(short slotNumber) throws InvalidSlotException {
        return getSlot(slotNumber).getPrice();
    }

    private int generateDrinkPrice() {
        int[] prices = { 100, 120, 200, 240, 270, 310, 390, 400, 500 };

        return prices[new Random().nextInt(prices.length)];
    }
}

package remcv.com.github.vendingmachine.model;

import remcv.com.github.vendingmachine.exception.ExceptionMessages;
import remcv.com.github.vendingmachine.exception.buy.OutOfItemsException;

import java.util.*;

public class DrinkSlot implements Slot<Drink, Integer> {
    // fields
    private final short maxItems;
    private int price;
    private final Queue<Drink> drinks;

    // constructor
    public DrinkSlot(short maxItems, int price) {
        this.maxItems = maxItems;
        drinks = new ArrayDeque<>(maxItems);
        setPrice(price);
    }

    // methods
    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.NEGATIVE_PRICES.getMessage());
        } else {
            this.price = price;
        }
    }

    @Override
    public Drink getItem() throws OutOfItemsException {
        try {
            return drinks.remove();
        } catch (NoSuchElementException e) {
            throw new OutOfItemsException(ExceptionMessages.OUT_OF_ITEMS.getMessage(), e);
        }
    }

    @Override
    public short getNumberOfItems() {
        return (short) drinks.size();
    }

    @Override
    public short getMaxItems() {
        return maxItems;
    }

    @Override
    public Collection<Drink> emptySlot() {
        Collection<Drink> drinksCopy = new ArrayList<>(drinks);
        drinks.clear();
        return drinksCopy;
    }

    @Override
    public void fillSlot(Drink item) {
        drinks.addAll(getItemsToFill(item));
    }

    private Collection<Drink> getItemsToFill(Drink drink) {
        int itemsToFill = getMaxItems() - getNumberOfItems();
        Collection<Drink> drinks = new ArrayList<>();

        for(int i = 0; i < itemsToFill; ++i) {
            drinks.add(drink.duplicate());
        }

        return drinks;
    }
}

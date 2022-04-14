package remcv.com.github.vendingmachine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class SlotImpl implements Slot {
    // fields
    private int price;
    private Stack<Drink> drinks; // TODO change with Dequeue

    // constructor
    public SlotImpl(int price, Stack<Drink> drinks) {
        this.price = price;
        drinks = new Stack<Drink>();
    }

    // methods
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public Drink getDrink() throws Exception { // TODO replace with appropriate exception
        return drinks.pop();
    }

    @Override
    public short getNumberOfItems() {
        return (short) drinks.size();
    }

    @Override
    public short getMaxItems() { // TODO max items logic
        return 6;
    }

    @Override
    public void emptySlot() {
        drinks.clear();
    }

    @Override
    public void fillSlot(Drink drink) {
        drinks.addAll(getItemsToFill(drink));
    }

    private List<Drink> getItemsToFill(Drink drink) {
        int itemsToFill = getMaxItems() - getNumberOfItems();
        List<Drink> drinks = new ArrayList<>();

        for(int i = 0; i < itemsToFill; ++i) {
            drinks.add(drink.duplicate(drink));
        }

        return drinks;
    }
}

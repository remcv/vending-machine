package remcv.com.github.vendingmachine.model;

public interface Slot {
    int getPrice();
    void setPrice(int price);
    Drink getDrink() throws Exception;
    short getNumberOfItems();
    short getMaxItems(); // TODO check alternatives VendingMachine
    void emptySlot();
    void fillSlot(Drink drink);
}

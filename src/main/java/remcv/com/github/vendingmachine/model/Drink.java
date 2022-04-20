package remcv.com.github.vendingmachine.model;

/**
 * Interface that models the behaviour of a drink stored in a vending machine.
 */
public interface Drink {
    String getName();
    void setName(String name);
    int getVolume();
    void setVolume(int volume);
}

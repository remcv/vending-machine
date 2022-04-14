package remcv.com.github.vendingmachine.model;

public interface Drink {
    String getName();
    void setName(String name);
    int getVolume();
    void setVolume(int volume);
    Drink duplicate(Drink drink);
}

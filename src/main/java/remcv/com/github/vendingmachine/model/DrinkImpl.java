package remcv.com.github.vendingmachine.model;

import java.util.Objects;

public class DrinkImpl implements Drink {
    // fields
    private String name;
    private int volume;

    // constructor
    public DrinkImpl(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    // methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public Drink duplicate() {
        try {
            return (Drink) this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkImpl drink = (DrinkImpl) o;
        return volume == drink.volume && Objects.equals(name, drink.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, volume);
    }
}

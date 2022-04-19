package remcv.com.github.vendingmachine.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkSlotTest {

    @Test
    void setPriceToAPositiveNumber() {
        // given I have a DrinkSlot instance
        var drinkSlot = new DrinkSlot((short) 8, 50);

        // when I set the price to a positive integer using the setter method
        drinkSlot.setPrice(100);

        // then the price field should equal the set value
        assertEquals(100, drinkSlot.getPrice());
    }

    @Test
    void setPriceToZeroShouldReturnIllegalArgumentException() {
        // given I have a DrinkSlot instance
        var drinkSlot = new DrinkSlot((short) 8, 50);

        // when I set the price to zero
        // then an illegal argument exception should be thrown
        assertThrowsExactly(IllegalArgumentException.class, () -> drinkSlot.setPrice(0));
    }

    @Test
    void setPriceToANegativeNumberShouldReturnIllegalArgumentException() {
        // given I have a DrinkSlot instance
        var drinkSlot = new DrinkSlot((short) 8, 50);

        // when I set the price to zero
        // then an illegal argument exception should be thrown
        assertThrowsExactly(IllegalArgumentException.class, () -> drinkSlot.setPrice(-5));
    }
}
package remcv.com.github.vendingmachine.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import remcv.com.github.vendingmachine.exception.buy.OutOfItemsException;

import static org.junit.jupiter.api.Assertions.*;

class DrinkSlotTest {
    // fields
    Slot<Drink, Integer> drinkSlot;

    // methods
    @BeforeEach
    void init() {
        drinkSlot = new DrinkSlot((short) 3 , 50);
    }

    @Nested
    class SetPriceTest {
        @Test
        void setPriceToAPositiveNumber() {
            // given
            int price = 200;

            // when
            drinkSlot.setPrice(price);

            // then
            int expected = 200;
            assertEquals(expected, drinkSlot.getPrice());
        }

        @Test
        void setPriceToZeroShouldReturnIllegalArgumentException() {
            // given
            int price = 0;

            // when
            Executable priceChange = () -> drinkSlot.setPrice(price);

            // then
            assertThrows(IllegalArgumentException.class, priceChange);
        }

        @Test
        void setPriceToANegativeNumberShouldReturnIllegalArgumentException() {
            // given
            int price = -10;

            // when
            Executable priceChange = () -> drinkSlot.setPrice(price);

            // then
            assertThrows(IllegalArgumentException.class, priceChange);
        }
    }

    @Nested
    class GetItemTest {

        @Test
        void getDrinkWhenSlotHasSufficientDrinks() throws OutOfItemsException {
            // given
            Drink drink = new DrinkImpl("Orange juice", 300);
            drinkSlot.fillSlot(drink);

            // when
            Drink returnedDrink = drinkSlot.getItem();

            // then
            int expectedDrinkCount = 2;
            assertEquals(drink, returnedDrink);
            assertEquals(expectedDrinkCount, drinkSlot.getNumberOfItems());
        }

        @Test
        void getDrinkWhenNoDrinksInSlotShouldThrowOutOfItemException() {
            // given
            // when
            Executable getOneDrink = () -> drinkSlot.getItem();

            // then
            assertThrows(OutOfItemsException.class, getOneDrink);
        }
    }

    @Test
    void fillSlotTest() {
        // given
        Drink drink = new DrinkImpl("Orange juice", 300);

        // when
        drinkSlot.fillSlot(drink);

        // then
        int expected = 3;
        assertEquals(expected, drinkSlot.getNumberOfItems());
    }
}

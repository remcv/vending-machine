package remcv.com.github.vendingmachine.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InvalidSlotException;
import remcv.com.github.vendingmachine.exception.buy.OutOfItemsException;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.model.DrinkImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrinkRepositoryTest {
    // fields
    DrinkRepository drinkRepository;

    // methods
    @BeforeEach
    void init() {
        drinkRepository = new DrinkRepository((short) 3, (short) 4, new Integer[] {50, 100, 130});
    }

    @Nested
    class RemoveOneTest {

        @Test
        void removeOneDrinkWithCorrectSlotNumberAndDrinkInStock() throws FillItemsMismatchException, BuyException {
            // given
            List<Drink> drinks = new ArrayList<>();
            drinks.add(new DrinkImpl("Grape juice", 330));
            drinks.add(new DrinkImpl("Orange juice", 330));
            drinks.add(new DrinkImpl("Carrot juice", 330));
            drinkRepository.fill(drinks);
            short slotNumber = (short) 2;

            // when
            Drink orderedDrink = drinkRepository.removeOne(slotNumber);

            // then
            Drink expectedDrink = new DrinkImpl("Orange juice", 330);
            int expectedDrinksLeft = 3;
            assertEquals(orderedDrink, expectedDrink);
            assertEquals(expectedDrinksLeft, drinkRepository.getSlot(slotNumber).getNumberOfItems());
        }

        @Test
        void removeOneDrinkWithIncorrectSlotNumberShouldThrowInvalidSlotException() {
            // given
            short slotNumber = (short) 10;

            // when
            Executable removeOneDrink = () -> drinkRepository.removeOne(slotNumber);

            // then
            assertThrows(InvalidSlotException.class, removeOneDrink);
        }

        @Test
        void removeOneDrinkWithCorrectSlotNumberAndDrinkOutOfStockShouldThrowOutOfItemException() {
            // given
            short slotNumber = (short) 2;

            // when
            Executable removeOneDrink = () -> drinkRepository.removeOne(slotNumber);

            // then
            assertThrows(OutOfItemsException.class, removeOneDrink);
        }
    }

    @Test
    void removeAllTest() throws FillItemsMismatchException, InvalidSlotException {
        // given
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new DrinkImpl("Grape juice", 330));
        drinks.add(new DrinkImpl("Orange juice", 330));
        drinks.add(new DrinkImpl("Carrot juice", 330));
        drinkRepository.fill(drinks);

        // when
        drinkRepository.removeAll();

        // then
        int expected = 0;
        assertEquals(expected, drinkRepository.getSlot((short) 1).getNumberOfItems());
    }

    @Test
    void fillWithCorrectInput() throws FillItemsMismatchException, InvalidSlotException {
        // given
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new DrinkImpl("Grape juice", 330));
        drinks.add(new DrinkImpl("Orange juice", 330));
        drinks.add(new DrinkImpl("Carrot juice", 330));

        // when
        drinkRepository.fill(drinks);

        // then
        int expected = 4;
        assertEquals(expected, drinkRepository.getSlot((short) 1).getNumberOfItems());
    }

    @Test
    void fillWithIncorrectInput() {
        // given
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new DrinkImpl("Grape juice", 330));
        drinks.add(new DrinkImpl("Orange juice", 330));

        // when
        Executable fill = () -> drinkRepository.fill(drinks);

        // then
        assertThrows(FillItemsMismatchException.class, fill);
    }



}
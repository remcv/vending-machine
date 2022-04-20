package remcv.com.github.vendingmachine.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CoinRepositoryTest {
    // fields
    MoneyRepository<Coin> coinRepository;

    // methods
    @BeforeEach
    void init() {
        coinRepository = new CoinRepository(0.6, 150);
    }

    @Nested
    class DepositTest {

        @Test
        void depositA10CentsCoinInInitialConditionsShouldAddItToStorage() throws FullMoneyStorageException {
            // given
            Coin coin = Coin.TEN_CENTS;

            // when
            coinRepository.deposit(coin);

            // then
            int expectedCoins = 91;
            int actualCoins = coinRepository.getMoneySlot(coin).size();
            assertEquals(expectedCoins, actualCoins);
        }

        @Test
        void depositA20CentsCoinInInitialConditionsShouldAddItToStorage() throws FullMoneyStorageException {
            // given
            Coin coin = Coin.TWENTY_CENTS;

            // when
            coinRepository.deposit(coin);

            // then
            int expectedCoins = 91;
            int actualCoins = coinRepository.getMoneySlot(coin).size();
            assertEquals(expectedCoins, actualCoins);
        }

        @Test
        void depositA50CentsCoinInInitialConditionsShouldAddItToStorage() throws FullMoneyStorageException {
            // given
            Coin coin = Coin.FIFTY_CENTS;

            // when
            coinRepository.deposit(coin);

            // then
            int expectedCoins = 91;
            int actualCoins = coinRepository.getMoneySlot(coin).size();
            assertEquals(expectedCoins, actualCoins);
        }

        @Test
        void depositA1EuroCoinInInitialConditionsShouldAddItToStorage() throws FullMoneyStorageException {
            // given
            Coin coin = Coin.ONE_EURO;

            // when
            coinRepository.deposit(coin);

            // then
            int expectedCoins = 91;
            int actualCoins = coinRepository.getMoneySlot(coin).size();
            assertEquals(expectedCoins, actualCoins);
        }

        @Test
        void depositA2EurosCoinInInitialConditionsShouldAddItToStorage() throws FullMoneyStorageException {
            // given
            Coin coin = Coin.TWO_EUROS;

            // when
            coinRepository.deposit(coin);

            // then
            int expectedCoins = 91;
            int actualCoins = coinRepository.getMoneySlot(coin).size();
            assertEquals(expectedCoins, actualCoins);
        }

        @Test
        void depositA50CentsCoinInAFullStorage() {
            // given
            coinRepository.fillStorage(1.0);
            Coin coin = Coin.FIFTY_CENTS;

            // when
            Executable depositCoin = () -> coinRepository.deposit(coin);

            // then a FullMoneyStorageException should be thrown
            assertThrows(FullMoneyStorageException.class, depositCoin);
        }
    }

    @Test
    void fillStorageAt50PercentOf150SlotCapacityShouldStore75Coins() {
        // given
        double proportion = 0.5;

        // when
        coinRepository.fillStorage(0.5);

        // then
        int expectedCoins = 75;
        assertEquals(expectedCoins, coinRepository.getMoneySlot(Coin.TEN_CENTS).size());
        assertEquals(expectedCoins, coinRepository.getMoneySlot(Coin.TWENTY_CENTS).size());
        assertEquals(expectedCoins, coinRepository.getMoneySlot(Coin.FIFTY_CENTS).size());
        assertEquals(expectedCoins, coinRepository.getMoneySlot(Coin.ONE_EURO).size());
        assertEquals(expectedCoins, coinRepository.getMoneySlot(Coin.TWO_EUROS).size());
    }

    @Test
    void fillStorageWithAProportionGreaterThanOneShouldThrowIllegalArgumentException() {
        // given
        double proportion = 2.7;

        // when
        Executable fill = () -> coinRepository.fillStorage(proportion);

        // then
        assertThrows(IllegalArgumentException.class, fill);
    }

    @Test
    void withdrawOne20CentsCoinFrom60PercentFullStorage() throws ChangeException {
        // given
        Coin coin = Coin.TWENTY_CENTS;

        // when
        Coin resultCoin = coinRepository.withdrawOne(coin);

        // then
        int expectedRemainingCoins = 89;
        assertEquals(Coin.TWENTY_CENTS, resultCoin);
        assertEquals(expectedRemainingCoins, coinRepository.getMoneySlot(coin).size());
    }

    @Test
    void withdrawOne10CentsCoinFromEmptyStorageShouldThrowChangeException() {
        // given
        coinRepository.fillStorage(0.0);
        Coin coin = Coin.TEN_CENTS;

        // when
        Executable withdraw = () -> coinRepository.withdrawOne(coin);

        // then
        assertThrows(ChangeException.class, withdraw);
    }

    @Test
    void withdrawAllShouldEmptyTheMoneyStorage() {
        // given
        // when
        Collection<Coin> coins = coinRepository.withdrawAll();

        // then
        int expectedNumberOfCoins = 450;
        assertAll(
                () -> assertTrue(coinRepository.getMoneySlot(Coin.TEN_CENTS).isEmpty()),
                () -> assertTrue(coinRepository.getMoneySlot(Coin.TWENTY_CENTS).isEmpty()),
                () -> assertTrue(coinRepository.getMoneySlot(Coin.FIFTY_CENTS).isEmpty()),
                () -> assertTrue(coinRepository.getMoneySlot(Coin.ONE_EURO).isEmpty()),
                () -> assertTrue(coinRepository.getMoneySlot(Coin.TWO_EUROS).isEmpty())
        );
        assertEquals(expectedNumberOfCoins, coins.size());
    }

    @Test
    void computeFreeStorageTest() {
        int expectedFeeSlotStorage = 60;
        assertAll(
                () -> assertEquals(expectedFeeSlotStorage, coinRepository.computeFreeStorage(Coin.TEN_CENTS)),
                () -> assertEquals(expectedFeeSlotStorage, coinRepository.computeFreeStorage(Coin.TWENTY_CENTS)),
                () -> assertEquals(expectedFeeSlotStorage, coinRepository.computeFreeStorage(Coin.FIFTY_CENTS)),
                () -> assertEquals(expectedFeeSlotStorage, coinRepository.computeFreeStorage(Coin.ONE_EURO)),
                () -> assertEquals(expectedFeeSlotStorage, coinRepository.computeFreeStorage(Coin.TWO_EUROS))
        );
    }

}
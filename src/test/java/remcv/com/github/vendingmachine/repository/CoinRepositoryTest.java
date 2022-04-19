package remcv.com.github.vendingmachine.repository;

import org.junit.jupiter.api.Test;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;

import static org.junit.jupiter.api.Assertions.*;

class CoinRepositoryTest {

    // fields
    private final MoneyRepository<Coin> coinRepository = new CoinRepository(0.6, 150);

    // methods
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

}
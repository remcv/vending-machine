package remcv.com.github.vendingmachine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import remcv.com.github.vendingmachine.exception.money.FullMoneyStorageException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.CoinRepository;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

import static org.junit.jupiter.api.Assertions.*;

class CoinCreditServiceTest {
    // fields
    MoneyRepository<Coin> coinRepository;
    CreditService<Coin, Integer> creditService;

    // methods
    @BeforeEach
    void init() {
        coinRepository = Mockito.mock(CoinRepository.class);
        creditService = new CoinCreditService(coinRepository);
    }

    @Test
    void checkMoneyStorageCapacityTest() {
        // given
        Coin oneEuro = Coin.ONE_EURO;
        int availableCoinStorage = 15;
        Mockito.when(coinRepository.computeFreeStorage(Coin.ONE_EURO)).thenReturn(availableCoinStorage);

        // when
        boolean result = creditService.checkMoneyStorageCapacity(oneEuro);

        // then
        assertTrue(result);
    }

    @Test
    void add2EurosWhenStorageAvailableShouldUpdateCreditTo2Euros() throws FullMoneyStorageException {
        // given
        Coin twoEuros = Coin.TWO_EUROS;
        int availableCoinStorage = 15;
        Mockito.when(coinRepository.computeFreeStorage(Coin.TWO_EUROS)).thenReturn(availableCoinStorage);

        // when
        creditService.add(twoEuros);

        // then
        int expectedCredit = 200;
        int actualCredit = creditService.getCredit();
        assertEquals(expectedCredit, actualCredit);
    }

    @Test
    void add2EurosWhenStorageNotAvailableShouldThrowFullMoneyStorageException() {
        // given
        Coin twoEuros = Coin.TWO_EUROS;
        int availableCoinStorage = 0;
        Mockito.when(coinRepository.computeFreeStorage(Coin.TWO_EUROS)).thenReturn(availableCoinStorage);

        // when
        Executable addCredit = () -> creditService.add(twoEuros);

        // then
        assertThrows(FullMoneyStorageException.class, addCredit);
    }

}
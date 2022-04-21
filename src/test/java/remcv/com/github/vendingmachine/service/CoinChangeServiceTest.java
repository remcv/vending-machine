package remcv.com.github.vendingmachine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.repository.CoinRepository;
import remcv.com.github.vendingmachine.repository.MoneyRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeServiceTest {
    // fields
    CreditService<Coin, Integer> coinCreditService;
    MoneyRepository<Coin> coinRepository;
    CoinChangeService changeService;

    // methods
    @BeforeEach
    void init() {
        coinCreditService = Mockito.mock(CoinCreditService.class);
        coinRepository = Mockito.mock(CoinRepository.class);
        changeService = new CoinChangeService(coinCreditService, coinRepository);
    }

    @Test
    void getChangeFor10CentsCreditWithValidData() throws ChangeException {
        // given
        int credit = 10;
        Mockito.when(coinCreditService.getCredit()).thenReturn(credit);
        Mockito.when(coinRepository.withdrawOne(Coin.TEN_CENTS)).thenReturn(Coin.TEN_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.TWENTY_CENTS)).thenReturn(Coin.TWENTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.FIFTY_CENTS)).thenReturn(Coin.FIFTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.ONE_EURO)).thenReturn(Coin.ONE_EURO);
        Mockito.when(coinRepository.withdrawOne(Coin.TWO_EUROS)).thenReturn(Coin.TWO_EUROS);

        // when
        Collection<Coin> change = changeService.getChange();

        // then
        int expectedCollectionSize = 1;
        Coin expectedCoin = Coin.TEN_CENTS;
        assertEquals(expectedCollectionSize, change.size());
        assertTrue(change.contains(expectedCoin));
    }

    @Test
    void getChangeFor30CentsCreditWithValidData() throws ChangeException {
        // given
        int credit = 30;
        Mockito.when(coinCreditService.getCredit()).thenReturn(credit);
        Mockito.when(coinRepository.withdrawOne(Coin.TEN_CENTS)).thenReturn(Coin.TEN_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.TWENTY_CENTS)).thenReturn(Coin.TWENTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.FIFTY_CENTS)).thenReturn(Coin.FIFTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.ONE_EURO)).thenReturn(Coin.ONE_EURO);
        Mockito.when(coinRepository.withdrawOne(Coin.TWO_EUROS)).thenReturn(Coin.TWO_EUROS);

        // when
        Coin[] actualChange = new Coin[2];
        changeService.getChange().toArray(actualChange);

        // then
        Coin[] expectedChange = new Coin[] { Coin.TWENTY_CENTS, Coin.TEN_CENTS };
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void getChangeFor370CentsCreditWithValidData() throws ChangeException {
        // given
        int credit = 380;
        Mockito.when(coinCreditService.getCredit()).thenReturn(credit);
        Mockito.when(coinRepository.withdrawOne(Coin.TEN_CENTS)).thenReturn(Coin.TEN_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.TWENTY_CENTS)).thenReturn(Coin.TWENTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.FIFTY_CENTS)).thenReturn(Coin.FIFTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.ONE_EURO)).thenReturn(Coin.ONE_EURO);
        Mockito.when(coinRepository.withdrawOne(Coin.TWO_EUROS)).thenReturn(Coin.TWO_EUROS);

        // when
        Coin[] actualChange = new Coin[5];
        changeService.getChange().toArray(actualChange);

        // then
        Coin[] expectedChange = new Coin[] {
                Coin.TWO_EUROS, Coin.ONE_EURO, Coin.FIFTY_CENTS, Coin.TWENTY_CENTS, Coin.TEN_CENTS
        };
        assertArrayEquals(expectedChange, actualChange);
    }

    @Test
    void getChangeFor30CentsCreditWhenOutOfCoinStorage() throws ChangeException {
        // given
        int credit = 30;
        Mockito.when(coinCreditService.getCredit()).thenReturn(credit);
        Mockito.when(coinRepository.withdrawOne(Coin.TEN_CENTS)).thenThrow(ChangeException.class);
        Mockito.when(coinRepository.withdrawOne(Coin.TWENTY_CENTS)).thenReturn(Coin.TWENTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.FIFTY_CENTS)).thenReturn(Coin.FIFTY_CENTS);
        Mockito.when(coinRepository.withdrawOne(Coin.ONE_EURO)).thenReturn(Coin.ONE_EURO);
        Mockito.when(coinRepository.withdrawOne(Coin.TWO_EUROS)).thenReturn(Coin.TWO_EUROS);

        // when
        Executable retrieveChange = () -> changeService.getChange();

        // then
        assertThrows(ChangeException.class, retrieveChange);
    }

}
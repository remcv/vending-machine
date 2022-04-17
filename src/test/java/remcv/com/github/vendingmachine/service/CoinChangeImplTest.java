package remcv.com.github.vendingmachine.service;

import org.junit.jupiter.api.Test;
import remcv.com.github.vendingmachine.model.Coin;

import static org.junit.jupiter.api.Assertions.*;

class CoinChangeImplTest {

    @Test
    void twoFiftyCentsShouldPriceOneDollar() throws Exception {
        // given
        var coinChangeImpl = new CoinChangeImpl();
        Coin[] coins = { Coin.FIFTY_CENTS, Coin.FIFTY_CENTS };

        // when
        int conversionResult = coinChangeImpl.coinsToPrice(coins);

        // then
        assertEquals(100, conversionResult);
    }

    @Test
    void oneCoinEachShouldPriceThreeHundredAndEighty() throws Exception {
        // given
        var coinChangeImpl = new CoinChangeImpl();
        Coin[] coins = { Coin.TEN_CENTS, Coin.TWENTY_CENTS, Coin.FIFTY_CENTS, Coin.ONE_EURO, Coin.TWO_EUROS };

        // when
        int conversionResult = coinChangeImpl.coinsToPrice(coins);

        // then
        assertEquals(380, conversionResult);
    }

}
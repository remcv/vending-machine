package remcv.com.github.vendingmachine.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import remcv.com.github.vendingmachine.VendingMachine;
import remcv.com.github.vendingmachine.VendingMachineImpl;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.money.MoneyException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.model.DrinkImpl;
import remcv.com.github.vendingmachine.repository.CoinRepository;
import remcv.com.github.vendingmachine.repository.DrinkRepository;
import remcv.com.github.vendingmachine.repository.ItemRepository;
import remcv.com.github.vendingmachine.repository.MoneyRepository;
import remcv.com.github.vendingmachine.service.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerSteps {
    // fields
    Drink receivedDrink;
    VendingMachine<Coin, Drink> vendingMachine;

    // methods
    @Given("A vending machine with standard defaults")
    public void a_vending_machine_with_standard_defaults() throws FillItemsMismatchException {
        // defaults
        double fillProportion = 0.6;
        int moneyStorageCapacity = 150;
        Integer[] prices = {100, 150, 50, 70, 280, 500};

        // build a standard/default vending machine
        // repos
        MoneyRepository<Coin> coinRepository = new CoinRepository(fillProportion, moneyStorageCapacity);
        ItemRepository<Drink, Integer> drinkRepository = new DrinkRepository((short) 6, (short) 8, prices);
        drinkRepository.fill(List.of(
                new DrinkImpl("Pepsi", 500),
                new DrinkImpl("Fanta", 500),
                new DrinkImpl("Sprite", 500),
                new DrinkImpl("CocaCola", 500),
                new DrinkImpl("Mirinda", 500),
                new DrinkImpl("IceTea", 500)
        ));

        // services
        CreditService<Coin, Integer> coinCreditService = new CoinCreditService(coinRepository);
        MoneyChangeService<Coin> coinChangeService = new CoinChangeService(coinCreditService, coinRepository);
        ItemService<Drink> drinkService = new DrinkService(coinCreditService, drinkRepository);

        // vending machine
        vendingMachine = new VendingMachineImpl(drinkService, coinChangeService, coinCreditService);
    }

    @When("I insert {int} Euro credit")
    public void i_insert_euro_credit(Integer int1) throws MoneyException {
        vendingMachine.insertMoney(Coin.ONE_EURO);
    }

    @When("I buy a {int} Euro drink from slot {int}")
    public void i_buy_a_euro_drink_from_slot(Integer int1, Integer int2) throws BuyException {
        receivedDrink = vendingMachine.buy((short) 1);
    }

    @Then("I receive the desired drink from the machine and no change")
    public void i_receive_the_desired_drink_from_the_machine_and_no_change() {
        assertEquals(new DrinkImpl("Pepsi", 500), receivedDrink);
    }
}

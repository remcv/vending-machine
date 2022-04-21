package remcv.com.github.vendingmachine.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.function.Executable;
import remcv.com.github.vendingmachine.VendingMachine;
import remcv.com.github.vendingmachine.VendingMachineImpl;
import remcv.com.github.vendingmachine.exception.ChangeException;
import remcv.com.github.vendingmachine.exception.FillItemsMismatchException;
import remcv.com.github.vendingmachine.exception.buy.BuyException;
import remcv.com.github.vendingmachine.exception.buy.InsufficientCreditException;
import remcv.com.github.vendingmachine.exception.money.MoneyException;
import remcv.com.github.vendingmachine.model.Coin;
import remcv.com.github.vendingmachine.model.Drink;
import remcv.com.github.vendingmachine.model.DrinkImpl;
import remcv.com.github.vendingmachine.repository.CoinRepository;
import remcv.com.github.vendingmachine.repository.DrinkRepository;
import remcv.com.github.vendingmachine.repository.ItemRepository;
import remcv.com.github.vendingmachine.repository.MoneyRepository;
import remcv.com.github.vendingmachine.service.*;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSteps {
    // fields
    Drink receivedDrink;
    Collection<Coin> receivedChange;
    Executable performAction;
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

    @When("I insert {int} Euro and {int} Cents credit")
    public void i_insert_euro_and_cents_credit(Integer int1, Integer int2) throws MoneyException {
        vendingMachine.insertMoney(Coin.ONE_EURO);
        vendingMachine.insertMoney(Coin.FIFTY_CENTS);
    }

    @When("I buy a {int} cents drink from slot {int}")
    public void i_buy_a_cents_drink_from_slot(Integer int1, Integer int2) throws BuyException {
        receivedDrink = vendingMachine.buy((short) 1);
    }

    @When("I press get change")
    public void i_press_get_change() throws ChangeException {
        receivedChange = vendingMachine.getChange();
    }

    @Then("I receive the desired drink from the machine and {int} Cents change")
    public void i_receive_the_desired_drink_from_the_machine_and_cents_change(Integer int1) {
        assertEquals(new DrinkImpl("Pepsi", 500), receivedDrink);
        assertEquals(1, receivedChange.size());
        assertTrue(receivedChange.contains(Coin.FIFTY_CENTS));
    }

    @Then("I receive the desired drink from the machine and no change")
    public void i_receive_the_desired_drink_from_the_machine_and_no_change() throws ChangeException {
        assertEquals(new DrinkImpl("Pepsi", 500), receivedDrink);
        assertEquals(0, vendingMachine.getChange().size());
    }

    @When("I try to buy a {int} cents drink from slot {int}")
    public void i_try_to_buy_a_cents_drink_from_slot(Integer int1, Integer int2) {
        performAction = () -> vendingMachine.buy((short) 2);
    }

    @Then("I receive a change error")
    public void i_receive_a_change_error() {
        assertThrows(InsufficientCreditException.class, performAction);
    }

    @When("I fill the machine with drinks")
    public void i_fill_the_machine_with_drinks() throws FillItemsMismatchException {
        vendingMachine.emptyItemStorage();
        vendingMachine.fillItemStorage(List.of(
                new DrinkImpl("Pepsi", 500),
                new DrinkImpl("Fanta", 500),
                new DrinkImpl("Sprite", 500),
                new DrinkImpl("CocaCola", 500),
                new DrinkImpl("Mirinda", 500),
                new DrinkImpl("IceTea", 500)
        ));
    }

    @Then("All drink slots should be full")
    public void all_drink_slots_should_be_full() {
        assertEquals(48, vendingMachine.emptyItemStorage().size());
    }

}

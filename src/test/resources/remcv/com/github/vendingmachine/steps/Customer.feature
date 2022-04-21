Feature: Vending machine end-user scenarios for a customer

  Scenario: Happy scenario to buy a drink with exact credit
    Given A vending machine with standard defaults
    When I insert 1 Euro credit
    And I buy a 100 cents drink from slot 1
    Then I receive the desired drink from the machine and no change

  Scenario: Happy scenario to buy a drink with more credit than the item's price and receive correct exchange
    Given A vending machine with standard defaults
    When I insert 1 Euro and 50 Cents credit
    And I buy a 100 cents drink from slot 1
    And I press get change
    Then I receive the desired drink from the machine and 50 Cents change

  Scenario: Try to buy a drink with insufficient credit
    Given A vending machine with standard defaults
    When I insert 1 Euro credit
    And I try to buy a 100 cents drink from slot 2
    Then I receive a change error
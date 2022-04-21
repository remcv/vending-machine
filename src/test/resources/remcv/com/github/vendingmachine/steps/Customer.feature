Feature: Vending machine end-user scenarios for a customer.

  Scenario: Happy scenario to buy a drink with exact credit.
    Given A vending machine with standard defaults
    When I insert 1 Euro credit
    And I buy a 1 Euro drink from slot 1
    Then I receive the desired drink from the machine and no change
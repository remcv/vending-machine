Feature: Vending machine end-user scenarios for maintenance staff

  Scenario: Fill the drink storage of the vending machine
    Given A vending machine with standard defaults
    When I fill the machine with drinks
    Then All drink slots should be full
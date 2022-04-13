# vending-machine

## Task

Develop the main business logic of a **vending machine for drinks** including functionality 
to return change.  
The vending machine shall have several slots containing the different drinks offered; each 
slot is assigned a price.  
Provide the business logic via a meaningfully named and typed interface. Based on this 
interface, implement the business logic.  

### Working proof

Show that your code is working as intended by some exemplary **automated tests**.  

### Functionality

#### Sell drinks

The main functionality provided by your implementation could, for instance, look like this:  

`DrinkAndChange buy(DrinkChoice choice, Coin... input);`  

This function either returns: 
* the chosen drink and optionally the change in coins or 
* an exception, i.e. if:
  * the chosen drink is sold out
  * the input is less than the price or 
  * the vending machine cannot provide appropriate change.    

The vending machine shall accept coins of the following values:
* 10 cents
* 20 cents
* 50 cents
* 1 Euro (= 100 cents)
* 2 Euros (= 200 cents)

#### Refill the stock of the vending machine (drinks and change)

The stock of drinks and the stock coins for change of the vending machine is limited.
The interface should also provide functionality to:
* fill the vending machine with **drinks** and **change** as well as 
* empty both stocks

### Example usage

If a customer buys a drink with a price of 1.20 Euros and puts in 1 coin of 2 Euros, the 
vending machine could, for instance, provide the change of 80 Cents in the form of 1 coin 
of 50 Cent, 1 coin of 20 Cent and 1 coin of 10 Cent.

Please include in your implementation only as much flexibility as is obviously needed 
judging from the specification above; i.e. different drinks and different pricing should 
be supported whereas different coin values must not be supported.

The programming task neither demands you to provide any kind of graphical user interface 
nor the persisting the any state of the vending machine into a database; an in-memory 
solution is fully sufficient.

## The Journey is the Reward

The primary goal of this programming task is the realization of an elegant and 
easy-to-understand implementation that uses – as much as possible – human intelligible terms.  
In your design/implementation, use the concepts of object-orientation in general and the 
according features provided by Java and its standard API where appropriate but try to avoid 
over-usage of them.  
The realization of a functionally complete solution is subordinate; a partial solution 
is welcome as long as it is self-content and proven to work as shown by an automated test.
The task should be completed within 2-4 hours.

## You may use

* IntelliJ
* JUnit 
* Some standard project configuration 
* General libraries from Apache Commons 
* Internet access

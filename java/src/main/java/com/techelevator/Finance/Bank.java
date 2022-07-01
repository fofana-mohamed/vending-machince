package com.techelevator.Finance;

import com.techelevator.Inventory.Inventory;
import com.techelevator.UI.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Bank {

    private BigDecimal currentBalance;

    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }

    public Bank(BigDecimal currentBalance){
        this.currentBalance = currentBalance;
    }

    public BigDecimal addToBalance(BigDecimal moneyToAdd) {
        this.currentBalance = currentBalance.add(moneyToAdd);
        return this.currentBalance;
    }

    public BigDecimal subtractFromBalance(BigDecimal moneyToSubtract) {
        this.currentBalance = currentBalance.subtract(moneyToSubtract);
        return this.currentBalance;
    }

    public boolean isMoneyValid(String money){
        // Checks to see if the string is a valid integer - returns false if the string cannot be converted to an int
        if(money == null) return false;

        try{
            Integer.parseInt(money);
        } catch (NumberFormatException nfe) {return false;}
        return true;
    }

    public BigDecimal finishTransaction(List<String> itemsWanted, Inventory inventory, UserOutput output, Bank bank){
        // Add up the total cost of all of the items that the user got
        BigDecimal totalCost = BigDecimal.valueOf(0);
        for(String item: itemsWanted) {
            totalCost = totalCost.add(inventory.getPrice(item));
        }

        // Initializes our the amount of change we need to give to the user to the current balance, and the total change
        // given to zero. At the end of the program these two values will equal each other.
        BigDecimal change = bank.getCurrentBalance();
        BigDecimal changeGiven = BigDecimal.ZERO;


        // Values of the change we can give
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.1");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");


        // After the user bought their items, divide the money left over by the value of the change we can provide
        // to get the number of quarters, dimes, nickels, and pennies that the user receives back. We also keep track
        // of the total changeGiven.

        // Quarters
        int numQuarters = change.divide(quarter, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numQuarters).multiply(quarter));
        changeGiven = changeGiven.add(quarter.multiply(BigDecimal.valueOf(numQuarters)));

        // Dimes
        int numDimes = change.divide(dime, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numDimes).multiply(dime));
        changeGiven = changeGiven.add(dime.multiply(BigDecimal.valueOf(numDimes)));

        // Nickels
        int numNickels = change.divide(nickel, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numNickels).multiply(nickel));
        changeGiven = changeGiven.add(nickel.multiply(BigDecimal.valueOf(numNickels)));

        // Pennies
        int numPennies = change.multiply(new BigDecimal(100)).intValue();
        changeGiven = changeGiven.add(penny.multiply(BigDecimal.valueOf(numPennies)));

        // Print out the breakdown of the change given, and reset the current balance to zero for the next user
        output.displayChange(numQuarters, numDimes, numNickels, numPennies);
        this.currentBalance = BigDecimal.valueOf(0);
        return changeGiven;
    }
}

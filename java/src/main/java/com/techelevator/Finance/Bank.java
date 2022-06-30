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

    public boolean isMoneyValid(BigDecimal money){
        return money.equals(new BigDecimal(1)) || money.equals(new BigDecimal(2)) ||
                money.equals(new BigDecimal(5)) || money.equals(new BigDecimal(10));
    }

    public boolean finishTransaction(List<String> itemsWanted, Inventory inventory, UserOutput output){
        BigDecimal totalCost = BigDecimal.valueOf(0);
        for(String item: itemsWanted) {
            totalCost = totalCost.add(inventory.getPrice(item));
        }

        BigDecimal change = currentBalance.subtract(totalCost);
        if(change.compareTo(BigDecimal.ZERO) >= 0) {
            // Purchase complete - give change
            BigDecimal quarter = new BigDecimal("0.25");
            BigDecimal dime = new BigDecimal("0.1");
            BigDecimal nickel = new BigDecimal("0.05");

            int numQuarters = change.divide(quarter, RoundingMode.FLOOR).intValue();
            change = change.subtract(BigDecimal.valueOf(numQuarters).multiply(quarter));
            int numDimes = change.divide(dime, RoundingMode.FLOOR).intValue();
            change = change.subtract(BigDecimal.valueOf(numDimes).multiply(dime));
            int numNickels = change.divide(nickel, RoundingMode.FLOOR).intValue();
            change = change.subtract(BigDecimal.valueOf(numNickels).multiply(nickel));
            BigDecimal numPennies = change.multiply(new BigDecimal(100));

            output.displayChange(numQuarters, numDimes, numNickels, numPennies.intValue());
            this.currentBalance = BigDecimal.valueOf(0);
            return true;

        } else {
            System.out.println("You do not have enough money to buy all of your items. Please add more money.");
            return false;
        }

    }
}
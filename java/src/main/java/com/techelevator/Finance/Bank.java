package com.techelevator.Finance;

import com.techelevator.Inventory.Inventory;
import com.techelevator.UI.UserOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(money == null) return false;

        try{
            int i = Integer.parseInt(money);
        } catch (NumberFormatException nfe) {return false;}
        return true;
    }

    public BigDecimal finishTransaction(List<String> itemsWanted, Inventory inventory, UserOutput output, Bank bank){
        BigDecimal totalCost = BigDecimal.valueOf(0);
        for(String item: itemsWanted) {
            totalCost = totalCost.add(inventory.getPrice(item));
        }

        BigDecimal change = bank.getCurrentBalance();
        BigDecimal changeGiven = BigDecimal.ZERO;

        // Purchase complete - give change
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.1");
        BigDecimal nickel = new BigDecimal("0.05");

        int numQuarters = change.divide(quarter, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numQuarters).multiply(quarter));
        changeGiven = changeGiven.add(BigDecimal.valueOf(.25).multiply(BigDecimal.valueOf(numQuarters)));
        int numDimes = change.divide(dime, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numDimes).multiply(dime));
        changeGiven = changeGiven.add(BigDecimal.valueOf(.1).multiply(BigDecimal.valueOf(numDimes)));
        int numNickels = change.divide(nickel, RoundingMode.FLOOR).intValue();
        change = change.subtract(BigDecimal.valueOf(numNickels).multiply(nickel));
        changeGiven = changeGiven.add(BigDecimal.valueOf(.05).multiply(BigDecimal.valueOf(numNickels)));
        int numPennies = change.multiply(new BigDecimal(100)).intValue();
        changeGiven = changeGiven.add(BigDecimal.valueOf(.01).multiply(BigDecimal.valueOf(numPennies)));

        output.displayChange(numQuarters, numDimes, numNickels, numPennies);
        this.currentBalance = BigDecimal.valueOf(0);
        return changeGiven;
    }
}

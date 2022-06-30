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

    public boolean isMoneyValid(String money){
        if(money == null) return false;

        try{
            int i = Integer.parseInt(money);
        } catch (NumberFormatException nfe) {return false;}
        return true;
    }

    public boolean finishTransaction(List<String> itemsWanted, Inventory inventory, UserOutput output, Bank bank){
        BigDecimal totalCost = BigDecimal.valueOf(0);
        for(String item: itemsWanted) {
            totalCost = totalCost.add(inventory.getPrice(item));
        }

        BigDecimal change = bank.getCurrentBalance();

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
    }
}

package com.techelevator.application;

import java.math.BigDecimal;

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

    public void finishTransaction(){
        
    }
}

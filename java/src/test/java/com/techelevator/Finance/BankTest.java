package com.techelevator.Finance;

import com.techelevator.Inventory.Inventory;
import com.techelevator.UI.UserOutput;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class BankTest {

    @Test
    public void check_addToBalance_startingBalanceZero(){
        BigDecimal startingBalance = BigDecimal.ZERO;
        BigDecimal moneyToAdd = BigDecimal.valueOf(10);
        Bank bank = new Bank(startingBalance);
        BigDecimal expected = moneyToAdd;
        BigDecimal actual;

        actual = bank.addToBalance(moneyToAdd);
        String message = "With a starting balance of $" + startingBalance + " and adding $" + moneyToAdd + " your ending balance should be $" + expected;
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_subtractFromBalance_startingBalanceZero(){
        BigDecimal startingBalance = BigDecimal.ZERO;
        BigDecimal moneyToSubtract = BigDecimal.valueOf(10);
        Bank bank = new Bank(startingBalance);
        BigDecimal expected = moneyToSubtract.multiply(BigDecimal.valueOf(-1));
        BigDecimal actual;

        actual = bank.subtractFromBalance(moneyToSubtract);
        String message = "With a starting balance of $" + startingBalance + " and subtracting $" + moneyToSubtract + " your ending balance should be $" + expected;
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_addToBalance_startingBalanceNonZero(){
        BigDecimal startingBalance = BigDecimal.valueOf(10);
        BigDecimal moneyToAdd = BigDecimal.valueOf(5);
        Bank bank = new Bank(startingBalance);
        BigDecimal expected = startingBalance.add(moneyToAdd);
        BigDecimal actual;

        actual = bank.addToBalance(moneyToAdd);
        String message = "With a starting balance of $" + startingBalance + " and adding $" + moneyToAdd + " your ending balance should be $" + expected;
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_subtractFromBalance_startingBalanceNonZero(){
        BigDecimal startingBalance = BigDecimal.valueOf(10);
        BigDecimal moneyToSubtract = BigDecimal.valueOf(5);
        Bank bank = new Bank(startingBalance);
        BigDecimal expected = startingBalance.subtract(moneyToSubtract);
        BigDecimal actual;

        actual = bank.subtractFromBalance(moneyToSubtract);
        String message = "With a starting balance of $" + startingBalance + " and subtracting $" + moneyToSubtract + ", your ending balance should be $" + expected;
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_isMoneyValid_wholeNumber(){
        Bank bank = new Bank(BigDecimal.ZERO);
        String money = "10";
        boolean expected = true;
        boolean actual;

        actual = bank.isMoneyValid(money);
        String message = "Given the input of " + money + ", the money is valid because it is a whole number";
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_isMoneyValid_decimal(){
        Bank bank = new Bank(BigDecimal.ZERO);
        String money = "10.01";
        boolean expected = false;
        boolean actual;

        actual = bank.isMoneyValid(money);
        String message = "Given the input of " + money + ", the money is valid because it is a whole number";
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_isMoneyValid_null(){
        Bank bank = new Bank(BigDecimal.ZERO);
        String money = null;
        boolean expected = false;
        boolean actual;

        actual = bank.isMoneyValid(money);
        String message = "Given the input of " + money + ", the money is valid because it is a whole number";
        assertEquals(message, expected, actual);
    }

    @Test
    //// This is a really bad test, but it checks that I did correct math in the method
    public void check_finishTransaction(){
        Inventory inventory = new Inventory();
        UserOutput output = new UserOutput();
        Bank bank = new Bank(BigDecimal.valueOf(6.95));
        List<String> itemsWanted = Arrays.asList("A1");
        BigDecimal expected = BigDecimal.valueOf(6.95);
        BigDecimal actual;


        actual = bank.finishTransaction(itemsWanted, inventory, output, bank);
        String message = "With a starting balance of $10 and having item A1 costing 3.05, your change should be: " + expected;
        assertEquals(message, expected, actual);
    }

    @Test
    public void check_finishTransaction_multipleItems(){
        Inventory inventory = new Inventory();
        UserOutput output = new UserOutput();
        Bank bank = new Bank(BigDecimal.valueOf(2.75));
        List<String> itemsWanted = Arrays.asList("A1", "A2", "A3");
        BigDecimal expected = BigDecimal.valueOf(2.75);
        BigDecimal actual;


        actual = bank.finishTransaction(itemsWanted, inventory, output, bank);
        String message = "With a starting balance of $10 and having item A1 costing 3.05, your change should be: " + expected;
        assertEquals(message, expected, actual);
    }
}

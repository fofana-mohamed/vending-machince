package com.techelevator.Finance;

import com.techelevator.Inventory.Inventory;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogTest {

    @Test
    public void logFeed_Prints_Correctly() throws FileNotFoundException {
        //arrange
        BigDecimal feedBalance = new BigDecimal(10);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
        String expected = formatter.format(dateTime)  + " FEED MONEY: " + feedBalance + " " + feedBalance;

        //act
        Log log = new Log();
        Bank bank = new Bank(feedBalance);

        //assert
        String actual = log.logFeed(feedBalance,bank);
        String message = "Should return date time FEED MONEY feed money beginning balance";
        assertEquals(message,expected,actual);
    }

    @Test
    public void logSale_Prints_Correctly() throws FileNotFoundException {
        //arrange
        String slot = "A1";
        BigDecimal currentBalance = new BigDecimal(10);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
        String expected = formatter.format(dateTime)  + " Potato Crisps " + slot + " " + 3.05 + " " +
                currentBalance.subtract(BigDecimal.valueOf(3.05));

        //act
        Log log = new Log();


        //assert
        String actual = log.logSale(slot, new Inventory(),new Bank(currentBalance.subtract(BigDecimal.valueOf(3.05))));
        String message = "Should return date time FEED MONEY feed money beginning balance";
        assertEquals(message,expected,actual);
    }

    @Test
    public void logChange_Prints_Correctly_A1_A2() throws FileNotFoundException {
        //arrange
        BigDecimal firstSale = new BigDecimal(3.05); //Potato crisps
        BigDecimal secondSale = new BigDecimal(1.45); //Stackers
        BigDecimal beginningBalance = new BigDecimal(10);
        BigDecimal difference = beginningBalance.subtract(firstSale.add(secondSale));

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
        String expected = formatter.format(dateTime)  + " GIVE CHANGE: " + difference.setScale(2, RoundingMode.FLOOR) + " " + 0;

        //act
        Log log = new Log();
        Bank bank = new Bank(beginningBalance);
        bank.subtractFromBalance(firstSale.add(secondSale));

        //assert
        String actual = log.logChange(bank.getCurrentBalance());
        String message = "Should return date time FEED MONEY feed money beginning balance";
        assertEquals(message,expected,actual);
    }

}

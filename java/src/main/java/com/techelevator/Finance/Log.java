package com.techelevator.Finance;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.techelevator.Inventory.Inventory;
import com.techelevator.application.Bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Log {
    private PrintWriter logFile = new PrintWriter("Log.txt");
    private LocalDateTime dateTime = LocalDateTime.now();
    public Log() throws FileNotFoundException {
    }

    public void logFeed(BigDecimal amount, Bank bank) {
        try {
            logFile.println(dateTime + "FEED MONEY: " + amount + " " + bank.getCurrentBalance().add(amount));
        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }

    public void logSale(String slot, Inventory inventory, Bank bank) {
        try {
            String name = inventory.getName(slot);
            BigDecimal amount = bank.getCurrentBalance();
            BigDecimal price = inventory.getPrice(slot);
            BigDecimal change = amount.subtract(price);

            logFile.println(dateTime + inventory.getName(slot) + " " + slot + amount + " " + change);

        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }

    public void logChange(BigDecimal balance) {
        try {

            logFile.println(dateTime + "GIVE CHANGE: " + balance + " " + 0);

        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
}

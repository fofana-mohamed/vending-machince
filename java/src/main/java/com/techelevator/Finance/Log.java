package com.techelevator.Finance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Log {

    private File newFile = new File("log.txt");
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();
    public Log() throws FileNotFoundException {
    }

    public void logFeed(BigDecimal amount, Bank bank) {
        try(FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(date + " " + time + " " + "FEED MONEY: " + amount + " " + bank.getCurrentBalance().add(amount));
        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }

    public void logSale(String slot, Inventory inventory, Bank bank) {
        try (FileWriter fileWriter = new FileWriter(newFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
                String name = inventory.getName(slot);
                BigDecimal amount = bank.getCurrentBalance();
                BigDecimal price = inventory.getPrice(slot);
                BigDecimal change = amount.subtract(price);

                printWriter.println(date + " " + time + " " + inventory.getName(slot) + " " + slot + amount + " " + change);

            } catch (Exception e) {
                System.out.println("An error occurred");
        }
    }

    public void logChange(BigDecimal balance) {

        try(FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(date + " " + time + " "  + "GIVE CHANGE: " + balance + " " + 0);

        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
}

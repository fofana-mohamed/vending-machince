package com.techelevator.Finance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

public class Log {

    private File newFile = new File("log.txt");

    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
    Queue<String> logs = new LinkedList<>();
    public Log() throws FileNotFoundException {
    }

    public String logFeed(BigDecimal amount, Bank bank) {
        String log = formatter.format(dateTime) + " " + "FEED MONEY: " + amount + " " + bank.getCurrentBalance();
        logs.add(log);
//        try(FileWriter fileWriter = new FileWriter(newFile);
//            PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            printWriter.println(date + " " + time + " " + "FEED MONEY: " + amount + " " + bank.getCurrentBalance());
//        }catch (Exception e) {
//            System.out.println("An error occurred");
//        }
        return log;
    }

    public String logSale(String slot, Inventory inventory, Bank bank) {
        String name = inventory.getName(slot);
        BigDecimal amount = bank.getCurrentBalance();
        BigDecimal price = inventory.getPrice(slot);
        BigDecimal currentBalance = bank.getCurrentBalance();
//        BigDecimal change = amount.subtract(price);

        String log = formatter.format(dateTime) + " " + inventory.getName(slot) + " " + slot + " " + price + " " + amount;

        logs.add(log);
//        try (FileWriter fileWriter = new FileWriter(newFile);
//             PrintWriter printWriter = new PrintWriter(fileWriter)) {
//                String name = inventory.getName(slot);
//                BigDecimal amount = bank.getCurrentBalance();
//                BigDecimal price = inventory.getPrice(slot);
//                BigDecimal change = amount.subtract(price);
//
//                printWriter.println(date + " " + time + " " + inventory.getName(slot) + " " + slot + amount + " " + change);
//
//            } catch (Exception e) {
//                System.out.println("An error occurred");
//        }
        return log;
    }

    public String logChange(BigDecimal balance) {

        String log = formatter.format(dateTime) + " GIVE CHANGE: " + balance.setScale(2, RoundingMode.FLOOR) + " " + 0;
        logs.add(log);
//        try(FileWriter fileWriter = new FileWriter(newFile);
//            PrintWriter printWriter = new PrintWriter(fileWriter)) {
//            printWriter.println(date + " " + time + " "  + "GIVE CHANGE: " + balance + " " + 0);
//
//        }catch (Exception e) {
//            System.out.println("An error occurred");
//        }
        return log;
    }

    public void loadLogs() {
        try(FileWriter fileWriter = new FileWriter(newFile);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {

            for (String log : logs) {
                printWriter.println(log);
            }

        }catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
}

package com.techelevator.Finance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import com.techelevator.Inventory.Inventory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

/*
    This class will manage all the log and transaction
    history (Feed, Change, Sale) into a text file
 */
public class Log {

    //Instance variables
    private File newFile = new File("log.txt");

    private LocalDateTime dateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
    private Queue<String> logs = new LinkedList<>();

    //Constructor
    public Log() throws FileNotFoundException {
    }

    /*
        Will add all the feed transactions into a Queue<String>
     */
    public String logFeed(BigDecimal amount, Bank bank) {
        String log = formatter.format(dateTime) + " " + "FEED MONEY: " + amount + " " + bank.getCurrentBalance();
        logs.add(log);
        return log;
    }

    /*
        Will add all the sales  into a Queue<String>
     */
    public String logSale(String slot, Inventory inventory, Bank bank) {
        String name = inventory.getName(slot);
        BigDecimal amount = bank.getCurrentBalance();
        BigDecimal price = inventory.getPrice(slot);
        BigDecimal currentBalance = bank.getCurrentBalance();

        String log = formatter.format(dateTime) + " " + inventory.getName(slot) + " " + slot + " " + price + " " + amount;

        logs.add(log);
        return log;
    }

    /*
        Will add all the Changes given to back to the
         customer into a Queue<String>
     */
    public String logChange(BigDecimal balance) {

        String log = formatter.format(dateTime) + " GIVE CHANGE: " + balance.setScale(2, RoundingMode.FLOOR) + " " + 0;
        logs.add(log);
        return log;
    }

    /*
        Will take all logs from the
        queue and writes it into a text file
        in the order in which they occurred
     */
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

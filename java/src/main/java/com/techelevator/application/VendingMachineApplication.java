package com.techelevator.application;

import com.techelevator.Finance.Bank;
import com.techelevator.Finance.SalesReport;
import com.techelevator.Inventory.Inventory;
import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineApplication {

    public void run() throws IOException {

        // Create instances of the classes, and initialize the total sales to 0.
        UserInput input = new UserInput();
        UserOutput output = new UserOutput();
        Bank bank = new Bank(new BigDecimal(0));
        Inventory inventory = new Inventory();
        List<String> itemsWanted = new ArrayList<>();
        SalesReport salesReport = new SalesReport();
        BigDecimal totalSales = BigDecimal.ZERO;

        while(true) {
            // display home screen and get user choice
            int userChoice = input.homeScreen();


            // Displays all of the items in the inventory
            if(userChoice == 1){
                output.displayItems(inventory);
            }

            // Purchase
            else if(userChoice == 2) {

                while (true){
                    // Display the purchase screen, and get user input
                    int purchaseChoice = input.purchaseScreen(bank, output, inventory);

                    // Prompts the user to input money into the system.
                    if(purchaseChoice == 1) {
                        input.feedMoney(bank);
                    }

                    // Prompts the user to select the product that they want to buy. Subtracts the purchase from the
                    // current balance of the bank, and adds the purchase to total sales.
                    else if(purchaseChoice == 2) {
                        String item = input.selectProduct(inventory, bank);
                        if(!item.equals("")) {
                            itemsWanted.add(item);
                            totalSales = totalSales.add(inventory.getPrice(item));
                        }
                    }

                    // Finishes the transaction by logging all of the actions in the Log.txt file, returns the change to
                    // the user, and restarts the banking balance and items wanted to empty/0.
                    else if(purchaseChoice == 3){
                        input.recordClosedTransaction(bank);
                        bank.finishTransaction(itemsWanted, inventory, output, bank);
                        for(String item: itemsWanted) {
                            salesReport.addToArchives(item, 1);
                        }
                        itemsWanted = new ArrayList<>();
                        break;
                    }

                    // Return to Home Screen
                    else if(purchaseChoice == 4) {
                        break;
                    }

                }

            }

            // Prints out the total amount of sales for each item, and the total amount of money spent at the vending
            // machine per run. This option is private and is not displayed to the user.
            else if (userChoice == 4) {
                output.displaySalesReport(inventory,salesReport,totalSales);
            }

            // Exit
            else {
                output.exit();
                break;
            }

        }

    }

}

package com.techelevator.application;

import com.techelevator.Finance.Bank;
import com.techelevator.Finance.SalesReport;
import com.techelevator.Inventory.Inventory;
import com.techelevator.UI.UserInput;
import com.techelevator.UI.UserOutput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineApplication {

    public void run() {

        UserInput input = new UserInput();
        UserOutput output = new UserOutput();
        Bank bank = new Bank(new BigDecimal(0));
        Inventory inventory = new Inventory();
        List<String> itemsWanted = new ArrayList<>();
        SalesReport salesReport = new SalesReport();

        while(true) {
            // display home screen and get user choice
            int userChoice = input.homeScreen();


            // Display Items
            if(userChoice == 1){
                output.displayItems(inventory);
            }
            // Purchase
            else if(userChoice == 2) {
                while (true){
                    int purchaseChoice = input.purchaseScreen(bank, output);

                    // Feed Money
                    if(purchaseChoice == 1) {
                        input.feedMoney(bank);
                    }

                    // Select Product
                    else if(purchaseChoice == 2) {
                        String item = input.selectProduct(inventory);
                        if(item != "") itemsWanted.add(item);
                    }

                    // Finish Transaction
                    else if(purchaseChoice == 3){
                        if(bank.finishTransaction(itemsWanted, inventory, output)) {
                            for(String item: itemsWanted) {
                                salesReport.addToArchives(item, 1);
                            }
                        }
                        itemsWanted = new ArrayList<>();
                        break;
                    }

                    else break;
                }
            }
            // Exit
            else {
                output.displaySalesReport(inventory,salesReport);
                output.exit();
                break;
            }

        }
    }
}

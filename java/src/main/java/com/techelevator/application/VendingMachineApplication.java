package com.techelevator.application;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Inventory.InventoryLoader;
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

        while(true) {
            // display home screen and get user choice
            int userChoice = input.homeScreen();


            // Display Items
            if(userChoice == 1){
                output.displayItems();
            }
            // Purchase
            else if(userChoice == 2) {

                int purchaseChoice = input.purchaseScreen(bank);

                // Feed Money
                if(purchaseChoice == 1) {
                    input.feedMoney(bank);
                }

                // Select Product
                else if(purchaseChoice == 2) {
                    input.selectProduct(inventory);
                }

                // Finish Transaction
                else{
                    break;
                }
            }
            // Exit
            else {
                break;
            }

        }
    }
}

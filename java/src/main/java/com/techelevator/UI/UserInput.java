package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;
import com.techelevator.Finance.Log;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {

    private Scanner input = new Scanner(System.in);

    Log log = new Log();
    Color color = new Color();
    public UserInput() throws IOException{
    }


    public int homeScreen(){
        // Print out the home screen for the user, and get their input. If the received input is valid, it will take
        // them to the screen they specified. If the received input is not valid, it will prompt them for another choice

        while(true){
            System.out.println();
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println(color.getTextBlue() + "           MAIN MENU              " + color.getTextReset());
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println();
            System.out.print("Please a make a selection...  -->> ");
            String destination = input.nextLine();
            System.out.println();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4"))
            {
                return Integer.parseInt(destination);
            }
            else System.out.println("Input was not valid: please try again");
        }
    }

    public int purchaseScreen(Bank bank, UserOutput output, Inventory inventory){
        // Print out the purchase screen for the user, and get their input. If the input is valid, it will take them to
        // the screen they specified. If the received input is not valid, it will prompt them for another choice.

        while(true){
            System.out.println();
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println(color.getTextBlue() + "         PURCHASE MENU            " + color.getTextReset());
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("(4) Go Back");
            System.out.println(color.getTextBlue() + "----------------------------------" + color.getTextReset());
            System.out.println();
            output.displayItems(inventory);
            System.out.println();
            output.showCurrentBalance(bank);
            System.out.println();
            System.out.print("Please a make a selection...  -->> ");
            String destination = input.nextLine();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4"))
            {
                return Integer.parseInt(destination);
            }
            else {
                System.out.println(color.getTextRed() + "!!!Invalid Input: please try again!!!" + color.getTextReset());
            }
        }
    }

    public void feedMoney(Bank bank){
        // Prompts the user to input a value for the money that they want to use in the system, and checks if the input
        // is valid. If it is a valid input, the money is added to the bank and the transaction is logged. If it is not,
        // the user is prompted for a different input

        while(true){
            System.out.print("Insert $$$ [$1,$5,$10,$20]  -->> ");
            String strMoney = input.nextLine();
            boolean isValidBill = strMoney.equals("1") || strMoney.equals("5") || strMoney.equals("10")
                                                || strMoney.equals("20");
            if(bank.isMoneyValid(strMoney) && isValidBill) {
                if(Integer.parseInt(strMoney) > 0){
                    BigDecimal money = new BigDecimal(strMoney);
                    bank.addToBalance(money);
                    log.logFeed(money,bank);
                }
                else{
                    System.out.println(color.getTextRed() + "!!!Invalid: please try again with " +
                            "a non-negative dollar amount!!!" + color.getTextReset());
                }
                return;
            }
            else {
                System.out.println(color.getTextRed() + "!!!Invalid: please try again " +
                        "with a whole dollar amount!!!" + color.getTextReset());
                System.out.println();
            }
        }
    }

    public String selectProduct(Inventory inventory, Bank bank){
        // Prompts the user for which item that they would like to purchase. If the input is valid, it checks to make sure
        // that the item is in stock and that the user has enough money for it before the user can purchase it. If the
        // input is not valid, is out of stock, or the user does not have enough money, the user is told the issue.

        //UserOutput.displayItems();
        System.out.println();
        System.out.print("Which item would you like to purchase? Please input a valid slot number...  -->> ");
        String slot = input.nextLine();
        for(String item: inventory.getItemList()) {
            if(slot.equals(item)){
                if(inventory.getQuantity(slot) == 0) {
                    System.out.println(color.getTextRed() + "Item is out of stock: please " +
                            "pick a different item." + color.getTextReset());
                    return "";
                }
                if(bank.getCurrentBalance().compareTo(inventory.getPrice(slot)) < 0) {
                    System.out.println(color.getTextYellow() + "!!!You have insufficient funds " +
                            "for this purchase!!!" + color.getTextReset());
                    return "";
                }
                else {
                    inventory.changeQuantity(slot, inventory.getQuantity(slot) - 1);
                    bank.subtractFromBalance(inventory.getPrice(slot));
                    System.out.println();
                    System.out.println(inventory.getSound(slot));
                    System.out.println();
                    log.logSale(slot,inventory,bank);
                    return slot;
                }
            }
        }
        System.out.println(color.getTextRed() + "Invalid slot number: please try again." + color.getTextReset());
        return "";
    }

    public void recordClosedTransaction(Bank bank) {
        // logs the change into the text file
        log.logChange(bank.getCurrentBalance());
        log.loadLogs();
    }
}

package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;
import com.techelevator.Finance.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {

    private Scanner input = new Scanner(System.in);

    Log log = new Log();
    Color color = new Color();
    public UserInput() throws IOException {
    }
//    private final Scanner input = new Scanner(System.in);


    public int homeScreen(){
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
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4")) {
                return Integer.parseInt(destination);
            }
            else System.out.println("Input was not valid: please try again");
        }
    }

    public int purchaseScreen(Bank bank, UserOutput output){
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
            output.showCurrentBalance(bank);
            System.out.println();
            System.out.print("Please a make a selection...  -->> ");
            String destination = input.nextLine();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4")) {
                return Integer.parseInt(destination);
            }
            else {
                System.out.println(color.getTextRed() + "!!!Invalid Input: please try again!!!" + color.getTextReset());
            }
        }
    }

    public void feedMoney(Bank bank){
        while(true){
            System.out.print("Insert $$$ [$1,$5,$10,$20]  -->> ");
            String strMoney = input.nextLine();
            if(bank.isMoneyValid(strMoney)) {
                if(Integer.parseInt(strMoney) > 0){
                    BigDecimal money = new BigDecimal(strMoney);
                    bank.addToBalance(money);
                    log.logFeed(money,bank);
                    return;
                }
                else{
                    System.out.println(color.getTextRed() + "!!!Invalid: please try again with " +
                            "a non-negative dollar amount!!!" + color.getTextReset());
                    return;
                }
            }
            else {
                System.out.println(color.getTextRed() + "!!!Invalid: please try again " +
                        "with a whole dollar amount!!!" + color.getTextReset());
                System.out.println();
            }
        }
    }

    public String selectProduct(Inventory inventory, Bank bank){
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
                    System.out.println(color.getTextYellow() + "!!!You insufficient funds " +
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
        log.logChange(bank.getCurrentBalance());
        log.loadLogs();
    }
}

package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInput {

    private Scanner input = new Scanner(System.in);

    public int homeScreen(){
        while(true){
            System.out.println();
            System.out.println("MAIN MENU");
            System.out.println("----------------------------------");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println();
            System.out.println("Input number corresponding to what you want to do:");
            System.out.println();
            String destination = input.nextLine();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4")) {
                return Integer.parseInt(destination);
            }
            else System.out.println("Input was not valid: please try again");
        }
    }

    public int purchaseScreen(Bank bank, UserOutput output){
        while(true){
            System.out.println();
            System.out.println("PURCHASE MENU");
            System.out.println("----------------------------------");
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("(4) Go Back");
            System.out.println();
            output.showCurrentBalance(bank);
            System.out.println();
            System.out.println("Input number corresponding to what you want to do:");
            String destination = input.nextLine();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3") || destination.equals("4")) {
                return Integer.parseInt(destination);
            }
            else {
                System.out.println("Input was not valid: please try again");
            }
        }
    }

    public void feedMoney(Bank bank){
        while(true){
            System.out.println("How much money would you like to input? Please input the money in whole dollar amounts.");
            String strMoney = input.nextLine();
            if(bank.isMoneyValid(strMoney)) {
                BigDecimal money = new BigDecimal(strMoney);
                bank.addToBalance(money);
                return;
            }
            else {
                System.out.println("Input was not valid: please try again with a whole dollar amount");
                System.out.println();
            }
        }
    }

    public String selectProduct(Inventory inventory, Bank bank){
        //UserOutput.displayItems();
        System.out.println();
        System.out.println("Which item would you like to purchase? Please input a valid slot number.");
        String slot = input.nextLine();
        for(String item: inventory.getItemList()) {
            if(slot.equals(item)){
                if(inventory.getQuantity(slot) == 0) {
                    System.out.println("Item is out stock: please pick a different item.");
                    return "";
                }
                else {
                    inventory.changeQuantity(slot, inventory.getQuantity(slot) - 1);
                    bank.addToBalance(inventory.getPrice(slot).multiply(new BigDecimal(-1)));
                    System.out.println();
                    System.out.println(inventory.getSound(slot));
                    System.out.println();
                    return slot;
                }
            }
        }
        System.out.println("Invalid slot number: please try again.");
        return "";
    }
}

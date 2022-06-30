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
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
            System.out.println();
            System.out.println("Input number corresponding to what you want to do:");
            System.out.println();
            String destination = input.nextLine();
            if(destination.equals("1") || destination.equals("2") || destination.equals("3")) {
                return Integer.parseInt(destination);
            }
            else System.out.println("Input was not valid: please try again");
        }
    }

    public int purchaseScreen(Bank bank, UserOutput output){
        while(true){
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("(4) Go Back");
            System.out.println();
            output.showCurrentBalance(bank);
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
            System.out.println("How much money would you like to input? Please input 1, 2, 5 or 10 corresponding to the amount of dollars.");
            BigDecimal money = new BigDecimal(input.nextLine());
            if(bank.isMoneyValid(money)) {
                bank.addToBalance(money);
                return;
            }
            else {
                System.out.println("Input was not valid: please try again");
            }
        }
    }

    public String selectProduct(Inventory inventory){
        //UserOutput.displayItems();
        System.out.println();
        System.out.println("Which item would you like to purchase? Please input a valid slot number.");
        String slot = input.nextLine();
        for(String item: inventory.getItemList()) {
            if(slot.equals(item)){
                System.out.println(inventory.getQuantity(slot));
                if(inventory.getQuantity(slot) == 0) {
                    System.out.println("Item is out stock: please try again.");
                    return "";
                }
                else {
                    inventory.getSound(slot);
                    return slot;
                }
            }
        }
        System.out.println("Invalid slot number: please try again.");
        return "";
    }
}

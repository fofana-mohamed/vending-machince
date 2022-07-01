package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;
import com.techelevator.Finance.Bank;
import com.techelevator.Finance.SalesReport;

import java.math.BigDecimal;

public class UserOutput {

    Color color = new Color();

    public void displayItems(Inventory inventory) {
        // Prints out the inventory and the quantity left of each item

        for (String slot : inventory.getItemList()) {
            String name = inventory.getName(slot);
            BigDecimal price = inventory.getPrice(slot);

            System.out.println(slot + "|" + name + "|" + price);
        }
    }

    public void exit() {
        // Prints out a goodbye

        System.out.println();
        System.out.println();
        System.out.println("Thank you for your business. Goodbye!");
    }
    public void showCurrentBalance(Bank bank) {
        // Prints out the current balance of the bank

        System.out.println("Current Money provided: "
                + color.getTextGreen() + "[$" + bank.getCurrentBalance() + "]" + color.getTextReset());
    }

    public void displaySalesReport(Inventory inventory, SalesReport sales, BigDecimal totalSales) {
        // Prints out the inventory and the current quantity of each item that is leftover in the vending machine. It
        // also prints out the total sales of the vending machine at the end.

        System.out.println();
        System.out.println(color.getTextBlue() + "***Archived Total Sales*** " + color.getTextReset());
        System.out.println();
        for (String slot : inventory.getItemList()) {
            String name = inventory.getName(slot);
            boolean wasPurchased = sales.getTotalSales().containsKey(slot);

            if (wasPurchased) {
                int quantity = sales.getTotalSales().get(slot);
                System.out.println(inventory.getName(slot) + "|" + quantity);
            } else {
                System.out.println(inventory.getName(slot) + "|" + 0);
            }
        }
        System.out.println();
        System.out.println("Total Sales: " + color.getTextGreen() +
                "$" + totalSales + color.getTextReset());
    }
    public void displayChange(int quarters, int dimes, int nickels, int pennies) {
        // Prints out the change that the user should receive

        System.out.println("You received " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, "
                + pennies + " pennies in change for your transaction");
        System.out.println();
    }
}

package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;
import com.techelevator.application.Bank;
import com.techelevator.Finance.SalesReport;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserOutput {
//    private Inventory inventory = new Inventory();
//    private List<String> list = inventory.getItemList();
    public void displayItems(Inventory inventory) {
        for (String slot : inventory.getItemList()) {
            String name = inventory.getName(slot);
            BigDecimal price = inventory.getPrice(slot);

            System.out.println(slot + "|" + name + "|" + price);
        }
    }

    public void exit() {
        System.out.println("Thank you for your business. Goodbye!");
    }
    public void showCurrentBalance(Bank bank) {
        System.out.println("Current Money provided: " + bank.getCurrentBalance());
    }

    public void displaySalesReport(Inventory inventory, SalesReport sales) {
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
    }
}

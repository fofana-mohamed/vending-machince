package com.techelevator.UI;

import com.techelevator.Inventory.Inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserOutput {
    private Inventory inventory = new Inventory();
    private List<String> list = inventory.getItemList();
    public void displayItems() {
        for (String slot : list) {
            String name = inventory.getName(slot);
            BigDecimal price = inventory.getPrice(slot);

            System.out.println(slot + "|" + name + "|" + price);
        }
    }

    public void exit() {
        System.out.println("Thank you for your business. Goodbye!");
    }
}

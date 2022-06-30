package com.techelevator.Finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
    private static Map<String, Integer> sales = new HashMap<>();

    public void addToArchives(String slot, int quantity) {
        if (sales.containsKey(slot)) {
            int currentQuantity = sales.get(slot);
            sales.put(slot, quantity + currentQuantity);
        } else {
            sales.put(slot, quantity);
        }
    }

    public Map<String, Integer> getTotalSales() {
        return sales;
    }
}

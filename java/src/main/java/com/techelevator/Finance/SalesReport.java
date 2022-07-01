package com.techelevator.Finance;

import java.util.HashMap;
import java.util.Map;

/*
    This class will manage the reporting
    of all sales made for each customer.
 */
public class SalesReport {

    //Instance variables
    private static Map<String, Integer> sales = new HashMap<>();

    /*
        Will take each product and at it to the archives
        along with their quantities.
     */
    public void addToArchives(String slot, int quantity) {
        if (sales.containsKey(slot)) {
            int currentQuantity = sales.get(slot);
            sales.put(slot, quantity + currentQuantity);
        } else {
            sales.put(slot, quantity);
        }
    }

    /*
        Returns a map of all sales made
        during a transaction
     */
    public Map<String, Integer> getTotalSales() {
        return sales;
    }
}

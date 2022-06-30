package com.techelevator.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryLoader {
    //Instance variables
    private List<String > productLine;
    File productFile = new File("vendingmachine.csv");

    //Constructor
    public InventoryLoader() {
        loadInventory();
    }

    //Class Methods
    private void loadInventory() {
        productLine = new ArrayList<>();

        try (Scanner dataInput = new Scanner(productFile)) {
            while(dataInput.hasNext()) {
                String line = dataInput.nextLine();
                productLine.add(line);
            }
        }catch (Exception e) {
            System.out.println("There was an issue opening the intended file");
        }
    }

    public List<String> getProductLine() {
        return productLine;
    }
}

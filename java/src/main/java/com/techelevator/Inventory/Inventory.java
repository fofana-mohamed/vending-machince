package com.techelevator.Inventory;

import java.math.BigDecimal;
import java.util.*;

/*
    This class will take a list of product lines
    and work to manage the vending machine's inventory
 */

public class Inventory {
    //Instance Variables
    private Map<String, String> numberName;
    private Map<String, BigDecimal> numberPrice;
    private Map<String, String> numberType;
    private Map<String, Integer> numberQuantity;
    private List<String> itemList;


    //Constructor
    public Inventory() {
        lineSplitter(new InventoryLoader().getProductLine());
    }

    //Class Methods

    /*
        Splits the list of lined from the input file and loads
        each line into 4 different map with the slot number as
        their keys.
     */
    private void lineSplitter(List<String> list) {
        itemList = new ArrayList<>();
        numberQuantity = new HashMap<>();
        numberPrice = new HashMap<>();
        numberName = new HashMap<>();
        numberType = new HashMap<>();

        for (String element : list) {
            String[] hold = element.split("\\|");
            itemList.add(hold[0]);
            numberName.put(hold[0], hold[1]);
            numberPrice.put(hold[0], BigDecimal.valueOf(Double.parseDouble(hold[2])));
            numberType.put(hold[0], hold[3]);
            numberQuantity.put(hold[0], 5);
        }
    }

    //Getters
    public String getName(String itemNumber) {
        return numberName.getOrDefault(itemNumber, "Invalid Code");
    }
    public BigDecimal getPrice(String itemNumber) {
        return numberPrice.get(itemNumber);
    }
    public String getType(String itemNumber) {
        return numberType.getOrDefault(itemNumber, "Invalid Code");
    }
    public int getQuantity(String itemNumber) {
        return numberQuantity.get(itemNumber);
    }
    public boolean isInStock(String itemNumber) {
        if (numberQuantity.get(itemNumber) > 0) {
            return true;
        }
        return false;
    }


    /*
        returns the sound of each slot number
        depending on their food type.
     */
    public String getSound(String itemNumber) {
        String type = numberType.get(itemNumber).toLowerCase(Locale.ROOT);

        if(type.equals("chip")) {
            return "Crunch Crunch, Yum!";
        }
        if(type.equals("candy")) {
            return "Munch Munch, Yum!";
        }
        if(type.equals("drink")) {
            return "Glug, Glug, Yum!";
        }
        if(type.equals("gum")) {
            return "Chew, Chew, Yum!";
        }
        return "Not a valid selection";
    }

    public List<String> getItemList() {return itemList;}

    /*
        Changes/updates the quantity of each slot number
     */
    public int changeQuantity(String itemNumber, int quantity) {
        int currentQuantity = numberQuantity.get(itemNumber);
        if (quantity > currentQuantity) {return -1;}
        else {
            numberQuantity.put(itemNumber, quantity);
        }

        return numberQuantity.get(itemNumber);
    }
}

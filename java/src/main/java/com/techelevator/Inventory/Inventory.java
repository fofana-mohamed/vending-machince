package com.techelevator.Inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    //Instance Variable
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
    private void lineSplitter(List<String> list) {
        itemList = new ArrayList<>();
        for (String element : list) {
            String[] hold = element.split("\\|");
            itemList.add(hold[0]);
            numberName.put(hold[0], hold[1]);
            numberPrice.put(hold[0], BigDecimal.valueOf(Integer.parseInt(hold[2])));
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

    public String getSound(String itemNumber) {
        String type = numberType.get(itemNumber);

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

    public int changeQuantity(String itemNumber, int quantity) {
        int currentQuantity = numberQuantity.get(itemNumber);
        if (quantity > currentQuantity) {return -1;}
        else {
            numberQuantity.put(itemNumber, numberQuantity.get(itemNumber) - quantity);
        }

        return numberQuantity.get(itemNumber);
    }
}

package com.techelevator.Inventory;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InventoryTest {

    @Test
    public void getName_Should_Return_Corresponding_Name() {
        String slot = "A1";
        String expected = "Potato Crisps";

        Inventory inventory = new Inventory();

        String actual = inventory.getName(slot);

        String message = "The slot number should return the corresponding name";
        assertEquals(message, expected,actual);
    }

    @Test
    public void getPrice_Should_Return_Corresponsing_Price() {
        String slot = "A1";
        BigDecimal expected = new BigDecimal(3.05).setScale(2,RoundingMode.CEILING);

        Inventory inventory = new Inventory();

        BigDecimal actual = inventory.getPrice(slot);

        String message = "The slot number should return the corresponding price";
        assertEquals(message,expected,actual);
    }

    @Test
    public void getQuantity_Check_Beginning_Quantity() {
        String slot = "A1";
        int expected = 5;

        Inventory inventory = new Inventory();

        int actual = inventory.getQuantity(slot);

        String message = "The beginning quantity for each product should be 5";
        assertEquals(message,expected,actual);
    }

    @Test
    public void changeQuantity_Check_Should_Return_Updated_Quantity() {
        String slot = "A1";
        int expected = 2;

        Inventory inventory = new Inventory();

        inventory.changeQuantity(slot, 2);
        int actual = inventory.getQuantity(slot);

        String message = "Should return the updated quantity after a purchase";
        assertEquals(message,expected,actual);
    }

    @Test
    public void isInStock_Should_Return_True() {
        String slot = "A1";
        boolean expected = true;

        Inventory inventory = new Inventory();

        boolean actual = inventory.isInStock(slot);

        String message = "Should return true when quantity > 0";
        assertEquals(message,expected,actual);
    }

    @Test
    public void isInStock_Should_Return_False() {
        String slot = "A1";
        boolean expected = false;

        Inventory inventory = new Inventory();

        inventory.changeQuantity(slot, 0);
        boolean actual = inventory.isInStock(slot);

        String message = "Should return false when quantity = 0";
        assertEquals(message,expected,actual);
    }

    @Test
    public void getSound_Check() {
        String slot = "A1";
        String expected = "Crunch Crunch, Yum!";

        Inventory inventory = new Inventory();

        String actual = inventory.getSound(slot);

        String message = "Should return the corresponding food sound by type";
        assertEquals(message,expected,actual);
    }




}

package com.example.cs230assignment;

/**
 * Describes a class to create an Item
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Item extends Entity {
    private int itemValue;

    public Item(String itemNamePass, int[] coordPass, int itemValuePass) {
        super(itemNamePass, coordPass);
        this.itemValue = itemValuePass;
    }

    public int getItemValue() {
        return this.itemValue;
    }

    public void setItemValue(int itemValuePass) {
        this.itemValue = itemValuePass;
    }

    public void draw() {
        System.out.println("Item");
    }
}

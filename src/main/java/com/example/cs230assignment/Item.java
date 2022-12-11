package com.example.cs230assignment;

/**
 * Describes a class to create an Item
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Item extends Entity {
    private int itemValue;
    private String itemName;

    /**
     * This is the constructor for the item class
     * 
     * @param itemNamePass  the name of the item
     * @param x             the x coordinate of the item
     * @param y             the y coordinate of the item
     * @param itemValuePass the value of the item
     */
    public Item(String itemNamePass, int x, int y, int itemValuePass) {
        super("item", x, y);
        this.itemName = itemNamePass;
        this.itemValue = itemValuePass;
    }

    /**
     * @return int
     */
    public int getItemValue() {
        return this.itemValue;
    }

    /**
     * @param itemValuePass the value of the item to be set
     */
    public void setItemValue(int itemValuePass) {
        this.itemValue = itemValuePass;
    }

    /**
     * @return String
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * draw the item
     */
    public void draw() {
        System.out.println("Item");
    }

    /**
     * @return boolean
     */
    public boolean isCollected() {
        return false;
    }
}

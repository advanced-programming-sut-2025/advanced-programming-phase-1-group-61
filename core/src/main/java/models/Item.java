package models;

import models.enums.ItemType;

import java.util.Arrays;

public class Item {
    private ItemType itemType;
    private int price;
    private boolean isEdible;
    private int energy;

    public Item(ItemType itemType) {
        this.itemType = itemType;
        this.price = itemType.getPrice();
        this.isEdible = itemType.isEdible();
        this.energy = itemType.getEnergy();
    }

    public Item() {
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        isEdible = edible;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public static ItemType getItem(String itemName) {
        for(ItemType type : ItemType.values()) {
            if(type.name().equalsIgnoreCase(itemName)) return type;
        }
        return null;
    }
}

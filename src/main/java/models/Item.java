package models;

import models.enums.ItemType;

public class Item {
    private ItemType itemType;
    private int price;
    private int quantity;

    public Item(ItemType itemType) {
        this.itemType = itemType;
        this.price = itemType.getPrice();
    }
}

package models.food;

import models.Item;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class FridgeItem {
    private ItemType item;
    private int quantity;

    public FridgeItem(ItemType item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

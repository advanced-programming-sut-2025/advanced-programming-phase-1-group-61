package models.food;

import models.Item;
import models.enums.ItemType;
import models.food.FridgeItem;
import models.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator extends Resource {
    private List<FridgeItem> items = new ArrayList<>();

    public Refrigerator() {
        super("");
    }

    public void addItem(ItemType item, int quantity) {
        for (FridgeItem fridgeItem : items) {
            if (fridgeItem.getItem().equals(item)) {
                fridgeItem.setQuantity(fridgeItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(new FridgeItem(item, quantity));
    }
    public int getCountOfItemInFridge(ItemType item) {
        for (FridgeItem fridgeItem : items) {
            if (fridgeItem.getItem().equals(item)) {
                return fridgeItem.getQuantity();
            }
        }
        return 0;
    }

    public void removeItemFromFridge(ItemType item, int quantity) {
        for (FridgeItem fridgeItem : items) {
            if (fridgeItem.getItem().equals(item)) {
                int remaining = fridgeItem.getQuantity() - quantity;
                if (remaining > 0) {
                    fridgeItem.setQuantity(remaining);
                } else {
                    items.remove(fridgeItem);
                }
                return;
            }
        }
    }

    public List<FridgeItem> getItems() {
        return items;
    }
}

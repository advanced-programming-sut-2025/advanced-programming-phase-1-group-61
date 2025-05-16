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

    public List<FridgeItem> getItems() {
        return items;
    }
}
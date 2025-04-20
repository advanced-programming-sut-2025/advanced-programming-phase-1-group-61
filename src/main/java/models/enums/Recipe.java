package models.enums;

import models.Item;
import java.util.List;

public enum Recipe {
    AXE(List.of(new Item(ItemType.Wood), new Item(ItemType.Iron)));

    private final List<Item> items;

    Recipe(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

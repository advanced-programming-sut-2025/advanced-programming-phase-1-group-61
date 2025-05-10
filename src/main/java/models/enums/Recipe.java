package models.enums;

import models.Item;

import java.util.HashMap;
import java.util.List;

public enum Recipe {
    AXE(List.of(new Item(ItemType.Iron)) , List.of(2));

    private HashMap<Item , Integer> items = new HashMap<>();

    Recipe(List<Item> resources,List<Integer> quantity) {
        HashMap<Item ,Integer> items = new HashMap<>();
        for (int i = 0; i < resources.size(); i++) {
           items.put(resources.get(i) , quantity.get(i));
        }
        this.items = items;
    }

    public HashMap<Item, Integer> getRecipe() {
        return items;
    }
}

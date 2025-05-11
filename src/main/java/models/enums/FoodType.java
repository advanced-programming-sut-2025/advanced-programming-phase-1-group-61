package models.enums;

import models.Item;

import java.util.HashMap;
import java.util.Map;

public enum FoodType {
    FIRED_EGG("fried egg",35,50, Map.of(new Item(ItemType.Egg),1));
    private final String name;
    private final int sellPrice;
    private final Map<Item,Integer> ingredient;
    private int energy;
    FoodType(String name, int sellPrice, int energy, Map<Item,Integer> ingredient) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.energy = energy;
        this.ingredient = ingredient;
    }
}

package models.enums;

import models.Item;
import java.util.HashMap;
import java.util.Map;

public enum FoodType {
    FRIED_EGG("fried egg", 35, 50, Map.of(new Item(ItemType.Egg), 1)),
    BAKED_FISH("baked fish", 100, 75, Map.of(
            new Item(ItemType.Sardine), 1,
            new Item(ItemType.Salmon), 1,
            new Item(ItemType.Wheat), 1)),
    SALAD("salad", 110, 113, Map.of(
            new Item(ItemType.Leek), 1,
            new Item(ItemType.Dandelion), 1)),
    OMELET("omelet", 125, 100, Map.of(
            new Item(ItemType.Egg), 1,
            new Item(ItemType.Milk), 1)),
    PUMPKIN_PIE("pumpkin pie", 385, 225, Map.of(
            new Item(ItemType.Pumpkin), 1,
            new Item(ItemType.WheatFlour), 1,
            new Item(ItemType.Milk), 1,
            new Item(ItemType.Sugar), 1)),
    SPAGHETTI("spaghetti", 120, 75, Map.of(
            new Item(ItemType.WheatFlour), 1,
            new Item(ItemType.Tomato), 1)),
    PIZZA("pizza", 300, 150, Map.of(
            new Item(ItemType.WheatFlour), 1,
            new Item(ItemType.Tomato), 1,
            new Item(ItemType.Cheese), 1)),
    TORTILLA("tortilla", 50, 50, Map.of(
            new Item(ItemType.Corn), 1)),
    MAKI_ROLL("maki roll", 220, 100, Map.of(
            new Item(ItemType.Fish), 1,
            new Item(ItemType.Rice), 1,
            new Item(ItemType.Fiber), 1)),
    TRIPLE_SHOT_ESPRESSO("triple shot espresso", 450, 200, Map.of(
            new Item(ItemType.Coffee), 3)),
    COOKIE("cookie", 140, 90, Map.of(
            new Item(ItemType.WheatFlour), 1,
            new Item(ItemType.Sugar), 1,
            new Item(ItemType.Egg), 1)),
    HASH_BROWNS("hash browns", 120, 90, Map.of(
            new Item(ItemType.Potato), 1,
            new Item(ItemType.Oil), 1)),
    PANCAKES("pancakes", 80, 90, Map.of(
            new Item(ItemType.WheatFlour), 1,
            new Item(ItemType.Egg), 1)),
    FRUIT_SALAD("fruit salad", 450, 263, Map.of(
            new Item(ItemType.Blueberry), 1,
            new Item(ItemType.Melon), 1,
            new Item(ItemType.Apricot), 1)),
    RED_PLATE("red plate", 400, 240, Map.of(
            new Item(ItemType.RedCabbage), 1,
            new Item(ItemType.Radish), 1)),
    BREAD("bread", 60, 50, Map.of(
            new Item(ItemType.WheatFlour), 1)),
    SALMON_DINNER("salmon dinner", 300, 125, Map.of(
            new Item(ItemType.Salmon), 1,
            new Item(ItemType.Amaranth), 1,
            new Item(ItemType.Kale), 1)),
    VEGETABLE_MEDLEY("vegetable medley", 120, 165, Map.of(
            new Item(ItemType.Tomato), 1,
            new Item(ItemType.Beet), 1)),
    FARMERS_LUNCH("farmer's lunch", 150, 200, Map.of(
            new Item(ItemType.Omelet), 1,
            new Item(ItemType.Parsnip), 1)),
    SURVIVAL_BURGER("survival burger", 180, 125, Map.of(
            new Item(ItemType.Bread), 1,
            new Item(ItemType.Carrot), 1,
            new Item(ItemType.EggPlant), 1)),
    DISH_O_THE_SEA("dish o' the sea", 220, 150, Map.of(
            new Item(ItemType.Sardine), 2,
            new Item(ItemType.HashBrowns), 1)),
    SEAFOAM_PUDDING("seafoam pudding", 300, 175, Map.of(
            new Item(ItemType.Flounder), 1,
            new Item(ItemType.MidnightCarp), 1)),
    MINERS_TREAT("miner's treat", 200, 125, Map.of(
            new Item(ItemType.Carrot), 2,
            new Item(ItemType.Sugar), 1,
            new Item(ItemType.Milk), 1));

    private final String name;
    private final int sellPrice;
    private final int energy;
    private final Map<Item, Integer> ingredients;

    FoodType(String name, int sellPrice, int energy, Map<Item, Integer> ingredients) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.energy = energy;
        this.ingredients = ingredients;
    }

    // Getters
    public String getName() { return name; }
    public int getSellPrice() { return sellPrice; }
    public int getEnergy() { return energy; }
    public Map<Item, Integer> getIngredients() { return ingredients; }
}
package models.enums;

import models.Item;
import models.character.Buff;

import java.util.HashMap;
import java.util.Map;

public enum CookingRecipes {
    FriedEgg("fried egg", 35, 50, null, new HashMap<>() {{
        put(ItemType.Egg, 1);
    }}, "starter"),

    BakedFish("baked fish", 100, 75, null, new HashMap<>() {{
        put(ItemType.Sardine, 1);
        put(ItemType.Salmon, 1);
        put(ItemType.Wheat, 1);
    }}, "starter"),

    Salad("salad", 110, 113, null, new HashMap<>() {{
        put(ItemType.Leek, 1);
        put(ItemType.Dandelion, 1);
    }}, "starter"),

    Omelet("omelet", 125, 100, null, new HashMap<>() {{
        put(ItemType.Egg, 1);
        put(ItemType.Milk, 1);
    }}, "stardrop saloon"),

    PumpkinPie("pumpkin pie", 385, 225, null, new HashMap<>() {{
        put(ItemType.Pumpkin, 1);
        put(ItemType.WheatFlour, 1);
        put(ItemType.Milk, 1);
        put(ItemType.Sugar, 1);
    }}, "stardrop saloon"),

    Spaghetti("spaghetti", 120, 75, null, new HashMap<>() {{
        put(ItemType.WheatFlour, 1);
        put(ItemType.Tomato, 1);
    }}, "stardrop saloon"),

    Pizza("pizza", 300, 150, null, new HashMap<>() {{
        put(ItemType.WheatFlour, 1);
        put(ItemType.Tomato, 1);
        put(ItemType.Cheese, 1);
    }}, "stardrop saloon"),

    Tortilla("tortilla", 50, 50, null, new HashMap<>() {{
        put(ItemType.Corn, 1);
    }}, "stardrop saloon"),

    MakiRoll("maki roll", 220, 100, null, new HashMap<>() {{
        put(ItemType.Fish, 1);
        put(ItemType.Rice, 1);
        put(ItemType.Fiber, 1);
    }}, "stardrop saloon"),

    TripleShotEspresso("triple shot espresso", 450, 200, new Buff(100, 5, false, false, false, false), new HashMap<>() {{
        put(ItemType.Coffee, 3);
    }}, "stardrop saloon"),

    Cookie("cookie", 140, 90, null, new HashMap<>() {{
        put(ItemType.WheatFlour, 1);
        put(ItemType.Sugar, 1);
        put(ItemType.Egg, 1);
    }}, "stardrop saloon"),

    HashBrowns("hash browns", 120, 90, new Buff(0, 5, true, false, false, false), new HashMap<>() {{
        put(ItemType.Potato, 1);
        put(ItemType.Oil, 1);
    }}, "stardrop saloon"),

    Pancakes("pancakes", 80, 90, new Buff(0, 11, false, false, true, false), new HashMap<>() {{
        put(ItemType.WheatFlour, 1);
        put(ItemType.Egg, 1);
    }}, "stardrop saloon"),

    FruitSalad("fruit salad", 450, 263, null, new HashMap<>() {{
        put(ItemType.Blueberry, 1);
        put(ItemType.Melon, 1);
        put(ItemType.Apricot, 1);
    }}, "stardrop saloon"),

    RedPlate("red plate", 400, 240, new Buff(50, 3, false, false, false, false), new HashMap<>() {{
        put(ItemType.RedCabbage, 1);
        put(ItemType.Radish, 1);
    }}, "stardrop saloon"),

    Bread("bread", 60, 50, null, new HashMap<>() {{
        put(ItemType.WheatFlour, 1);
    }}, "stardrop saloon"),

    SalmonDinner("salmon dinner", 300, 125, null, new HashMap<>() {{
        put(ItemType.Salmon, 1);
        put(ItemType.Amaranth, 1);
        put(ItemType.Kale, 1);
    }}, "leah reward"),

    VegetableMedley("vegetable medley", 120, 165, null, new HashMap<>() {{
        put(ItemType.Tomato, 1);
        put(ItemType.Beet, 1);
    }}, "forging level2"),

    FarmersLunch("farmer's lunch", 150, 200, new Buff(0, 5, true, false, false, false), new HashMap<>() {{
        put(ItemType.Omelet, 1);
        put(ItemType.Parsnip, 1);
    }}, "farming level1"),

    SurvivalBurger("survival burger", 180, 125, new Buff(0, 5, false, false, true, false), new HashMap<>() {{
        put(ItemType.Bread, 1);
        put(ItemType.Carrot, 1);
        put(ItemType.EggPlant, 1);
    }}, "forging level3"),

    DishOTheSea("dish o' the sea", 220, 150, new Buff(0, 5, false, false, false, true), new HashMap<>() {{
        put(ItemType.Sardine, 2);
        put(ItemType.HashBrowns, 1);
    }}, "farming level2"),

    SeaFormPudding("seafoam pudding", 300, 175, new Buff(0, 10, false, false, false, true), new HashMap<>() {{
        put(ItemType.Flounder, 1);
        put(ItemType.MidnightCarp, 1);
    }}, "fishing level3"),

    MinersTreat("miner's treat", 200, 125, new Buff(0, 5, false, true, false, false), new HashMap<>() {{
        put(ItemType.Carrot, 2);
        put(ItemType.Sugar, 1);
        put(ItemType.Milk, 1);
    }}, "mining level1");

    private final String name;
    private final int sellPrice;
    private final int energy;
    private final Map<ItemType, Integer> ingredients;
    private final Buff buff;
    private final String source;

    CookingRecipes(String name, int sellPrice, int energy, Buff buff, Map<ItemType, Integer> ingredients, String source) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.energy = energy;
        this.ingredients = ingredients;
        this.buff = buff;
        this.source = source;
    }

    public String getName() { return name; }
    public int getSellPrice() { return sellPrice; }
    public int getEnergy() { return energy; }
    public Map<ItemType, Integer> getIngredients() { return ingredients; }
    public Buff getBuff() { return buff; }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("Name: " + name + "\n");
        message.append("ingredients:\n");
        for (ItemType itemType : ingredients.keySet()) {
            message.append(itemType.getDisPlayName()).append(" ");
        }
        message.append("Sell price: ").append(sellPrice).append("\n");
        return message.toString();
    }

    public static CookingRecipes getCookingRecipes(String type) {
        try {
            return CookingRecipes.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

package models.enums;

import models.Item;
import models.character.Buff;

import java.util.Map;

public enum CookingRecipes {
    FriedEgg("fried egg", 35, 50,null, Map.of((ItemType.Egg), 1),"starter"),
    BakedFish("baked fish", 100, 75,null, Map.of(
           (ItemType.Sardine), 1,
            (ItemType.Salmon), 1,
            (ItemType.Wheat), 1),"starter"),
    Salad("salad", 110, 113,null, Map.of(
            (ItemType.Leek), 1,
            (ItemType.Dandelion), 1),"starter"),
    Omelet("omelet", 125, 100,null, Map.of(
            (ItemType.Egg), 1,
            (ItemType.Milk), 1),"stardrop saloon"),
    PumpkinPie("pumpkin pie", 385, 225,null, Map.of(
            (ItemType.Pumpkin), 1,
            (ItemType.WheatFlour), 1,
            (ItemType.Milk), 1,
            (ItemType.Sugar), 1),"stardrop saloon"),
    Spaghetti("spaghetti", 120, 75,null, Map.of(
            (ItemType.WheatFlour), 1,
            (ItemType.Tomato), 1),"stardrop saloon"),
    Pizza("pizza", 300, 150,null, Map.of(
            (ItemType.WheatFlour), 1,
            (ItemType.Tomato), 1,
            (ItemType.Cheese), 1),"stardrop saloon"),
    Tortilla("tortilla", 50, 50,null, Map.of(
            (ItemType.Corn), 1),"stardrop saloon"),
    MakiRoll("maki roll", 220, 100,null, Map.of(
            (ItemType.Fish), 1,
            (ItemType.Rice), 1,
            (ItemType.Fiber), 1),"stardrop saloon"),
    TripleShotEspresso("triple shot espresso", 450, 200,new Buff(100,5,false,false,false,false), Map.of(
            (ItemType.Coffee), 3),"stardrop saloon"),
    Cookie("cookie", 140, 90,null, Map.of(
            (ItemType.WheatFlour), 1,
            (ItemType.Sugar), 1,
            (ItemType.Egg), 1),"stardrop saloon"),
    HashBrowns("hash browns", 120, 90,new Buff(0,5,true,false,false,false), Map.of(
            (ItemType.Potato), 1,
            (ItemType.Oil), 1),"stardrop saloon"),
    Pancakes("pancakes", 80, 90,new Buff(0,11,false,false,true,false), Map.of(
            (ItemType.WheatFlour), 1,
            (ItemType.Egg), 1),"stardrop saloon"),
    FruitSalad("fruit salad", 450, 263,null, Map.of(
            (ItemType.Blueberry), 1,
            (ItemType.Melon), 1,
            (ItemType.Apricot), 1),"stardrop saloon"),
    RedPlate("red plate", 400, 240,new Buff(50,3,false,false,false,false), Map.of(
            (ItemType.RedCabbage), 1,
            (ItemType.Radish), 1),"stardrop saloon"),
    Bread("bread", 60, 50,null, Map.of(
            (ItemType.WheatFlour), 1),"stardrop saloon"),
    SalmonDinner("salmon dinner", 300, 125,null, Map.of(
            (ItemType.Salmon), 1,
            (ItemType.Amaranth), 1,
            (ItemType.Kale), 1),"leah reward"),
    VegetableMedley("vegetable medley", 120, 165,null, Map.of(
            (ItemType.Tomato), 1,
            (ItemType.Beet), 1),"forging level2"),
    FarmersLunch("farmer's lunch", 150, 200,new Buff(0,5,true,false,false,false), Map.of(
            (ItemType.Omelet), 1,
            (ItemType.Parsnip), 1),"farming level1"),
    SurvivalBurger("survival burger", 180, 125,new Buff(0,5,false,false,true,false), Map.of(
            (ItemType.Bread), 1,
            (ItemType.Carrot), 1,
            (ItemType.EggPlant), 1),"forging level3"),
    DishOTheSea("dish o' the sea", 220, 150,new Buff(0,5,false,false,false,true), Map.of(
            (ItemType.Sardine), 2,
            (ItemType.HashBrowns), 1),"farming level2"),
    SeaFormPudding("seafoam pudding", 300, 175,new Buff(0,10,false,false,false,true), Map.of(
            (ItemType.Flounder), 1,
            (ItemType.MidnightCarp), 1),"fishing level3"),
    MinersTreat("miner's treat", 200, 125,new Buff(0,5,false,true,false,false), Map.of(
            (ItemType.Carrot), 2,
            (ItemType.Sugar), 1,
            (ItemType.Milk), 1),"mining level1");

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
        StringBuilder message = new StringBuilder("Name: "+name+"\n");
        message.append("ingredients:\n");
        for (ItemType itemType : ingredients.keySet()) {
            message.append(itemType.getDisPlayName() + " ");
        }
        message.append("Sell price: ").append(sellPrice + "\n");
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
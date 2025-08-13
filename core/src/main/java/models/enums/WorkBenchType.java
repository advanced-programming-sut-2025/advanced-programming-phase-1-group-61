package models.enums;

import com.badlogic.gdx.graphics.Texture;
import models.workBench.RecipeInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum WorkBenchType {

    BeeHouse(Map.of(
        ItemType.Honey, new RecipeInfo(
            "It's a sweet syrup produced by bees.",
            75,
            96,
            Map.of(),
            "350g"
        )
    ), "WorkBench/Bee_House.png" , ItemType.BeeHouse),

    Loom(Map.of(
        ItemType.Cloth, new RecipeInfo(
            "A bolt of fine wool cloth.",
            0,
            4,
            Map.of(ItemType.Wool, 1),
            "470g"
        )
    ), "WorkBench/Loom.png" , ItemType.Loom),

    CheesePress(Map.of(
        ItemType.Cheese, new RecipeInfo(
            "It's your basic cheese.",
            100,
            3,
            Map.of(ItemType.Milk, 1),
            "230g / 345g"
        ),
        ItemType.GoatCheese, new RecipeInfo(
            "Soft cheese made from goat's milk.",
            100,
            3,
            Map.of(ItemType.GoatMilk, 1),
            "400g / 600g"
        )
    ), "WorkBench/Cheese_Press.png" , ItemType.GoatCheese),

    MayonnaiseMachine(Map.of(
        ItemType.Mayonnaise, new RecipeInfo(
            "It looks spreadable.",
            50,
            3,
            Map.of(ItemType.Egg, 1),
            "190g / 237g"
        ),
        ItemType.DuckMayonnaise, new RecipeInfo(
            "It's a rich, yellow mayonnaise.",
            75,
            3,
            Map.of(ItemType.DuckEgg, 1),
            "37g"
        ),
        ItemType.DinosaurMayonnaise, new RecipeInfo(
            "It's thick and creamy, with a vivid green hue. It smells like grass and leather.",
            125,
            3,
            Map.of(ItemType.DinosaurEgg, 1),
            "800g"
        )
    ), "WorkBench/Mayonnaise_Machine.png",ItemType.DinosaurMayonnaise),

    Keg(Map.of(
        ItemType.Beer, new RecipeInfo(
            "Drink in moderation.",
            50,
            24,
            Map.of(ItemType.Wheat, 1),
            "200g"
        ),
        ItemType.Vinegar, new RecipeInfo(
            "An aged fermented liquid used in many cooking recipes.",
            13,
            10,
            Map.of(ItemType.Rice, 1),
            "100g"
        ),
        ItemType.Coffee, new RecipeInfo(
            "It smells delicious. This is sure to give you a boost.",
            75,
            2,
            Map.of(ItemType.CoffeeBean, 5),
            "150g"
        ),
        ItemType.Juice, new RecipeInfo(
            "A sweet, nutritious beverage.",
            -1,
            96,
            Map.of(ItemType.Carrot, 1),
            "2.25 × Ingredient Base Price"
        ),
        ItemType.Mead, new RecipeInfo(
            "A fermented beverage made from honey. Drink in moderation.",
            100,
            10,
            Map.of(ItemType.Honey, 1),
            "300g"
        ),
        ItemType.PaleAle, new RecipeInfo(
            "Drink in moderation.",
            50,
            72,
            Map.of(ItemType.Hops, 1),
            "300g"
        ),
        ItemType.Wine, new RecipeInfo(
            "Drink in moderation.",
            -1,
            168,
            Map.of(ItemType.Orange, 1),
            "3 × Fruit Base Price"
        )
    ), "WorkBench/Keg.png" , ItemType.Keg),

    PreservesJar(Map.of(
        ItemType.Pickles, new RecipeInfo(
            "A jar of your home-made pickles.",
            -1,
            6,
            Map.of(ItemType.Carrot, 1),
            "2 × Base Price + 50"
        ),
        ItemType.Jelly, new RecipeInfo(
            "Gooey.",
            -1, // 2 × Base Fruit Energy
            72,
            Map.of(ItemType.Orange, 1),
            "2 × Base Fruit Price + 50"
        )
    ), "WorkBench/Preserves_Jar.png",ItemType.PreservesJar),

    Dehydrator(Map.of(
        ItemType.DriedMushrooms, new RecipeInfo(
            "A package of gourmet mushrooms.",
            50,
            12,
            Map.of(ItemType.CommonMushroom, 5),
            "7.5 × Mushroom Base Price + 25g"
        ),
        ItemType.DriedFruit, new RecipeInfo(
            "Chewy pieces of dried fruit.",
            75,
            12,
            Map.of(ItemType.Orange, 5),
            "7.5 × Fruit Base Price + 25g"
        ),
        ItemType.Raisins, new RecipeInfo(
            "It's said to be the Junimos' favorite food.",
            125,
            12,
            Map.of(ItemType.Grape, 5),
            "600g"
        )
    ), "WorkBench/Dehydrator.png" ,ItemType.Dehydrator),

    FishSmoker(Map.of(
        ItemType.SmokedFish, new RecipeInfo(
            "A whole fish, smoked to perfection.",
            -1,
            1,
            Map.of(ItemType.Fish, 1, ItemType.Coal, 1),
            "2 × Fish Price"
        )
    ), "WorkBench/Fish_Smoker_On.png",ItemType.FishSmoker),

    CharcoalKlin(Map.of(
        ItemType.Coal, new RecipeInfo(
            "Turns 10 pieces of wood into one piece of coal.",
            0,
            1,
            Map.of(ItemType.Wood, 10),
            "50g"
        )
    ), "WorkBench/Charcoal_Kiln_Off.png",ItemType.CharcoalKlin),


    Furnace(Map.of(
        ItemType.Iron, new RecipeInfo(
            "Turns ore and coal into metal bars.",
            0,
            4,
            Map.of(ItemType.IronOre, 5, ItemType.Coal, 1),
            "10 × Ore Price"
        ),
        ItemType.Copper , new RecipeInfo("Turns ore and coal into metal bars.",
            0,
            4,
            Map.of(ItemType.CopperOre, 5, ItemType.Coal, 1),
            "10 × Ore Price")
    ), "WorkBench/Furnace_On.png",ItemType.Furnace);

    private final Map<ItemType, RecipeInfo> recipes;
    private final String textureInternalPath;
    private transient Texture texture;
    private final ItemType workBench;

    WorkBenchType(Map<ItemType, RecipeInfo> recipes, String textureInternalPath,ItemType workBench) {
        this.recipes = recipes;
        this.textureInternalPath = textureInternalPath;
        this.workBench = workBench;
    }

    public Map<ItemType, RecipeInfo> getRecipes() {
        return recipes;
    }

    public Texture getTexture(){
        if(texture == null){
            try {
                texture = new Texture(textureInternalPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return texture;
    }

    public ItemType getWorkBench() {
        return workBench;
    }
    public static WorkBenchType getByWorkBenchItem(ItemType item) {
        for (WorkBenchType type : values()) {
            if (type.getWorkBench() == item) {
                return type;
            }
        }
        return null;
    }

}

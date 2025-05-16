package models.enums;



import java.util.HashMap;
import java.util.List;

public enum Recipe {
    CHERRY_BOMB(List.of(ItemType.CopperOre, ItemType.Coal), List.of(4, 1), 50),
    BOMB(List.of(ItemType.IronOre, ItemType.Coal), List.of(4, 1), 50),
    MEGA_BOMB(List.of(ItemType.GoldOre, ItemType.Coal), List.of(4, 1), 50),
    SPRINKLER(List.of(ItemType.CopperBar, ItemType.IronBar), List.of(1, 1), 0),
    QUALITY_SPRINKLER(List.of(ItemType.IronBar, ItemType.GoldBar), List.of(1, 1), 0),
    IRIDIUM_SPRINKLER(List.of(ItemType.GoldBar, ItemType.IridiumBar), List.of(1, 1), 0),
    CHARCOAL_KILN(List.of(ItemType.Wood, ItemType.CopperBar), List.of(20, 2), 0),
    FURNACE(List.of(ItemType.CopperOre, ItemType.Stone), List.of(20, 25), 0),
    SCARECROW(List.of(ItemType.Wood, ItemType.Coal, ItemType.Fiber), List.of(50, 1, 20), 0),
    DELUXE_SCARECROW(List.of(ItemType.Wood, ItemType.Coal, ItemType.Fiber, ItemType.IridiumOre), List.of(50, 1, 20, 1), 0),
    BEE_HOUSE(List.of(ItemType.Wood, ItemType.Coal, ItemType.IronBar), List.of(40, 8, 1), 0),
    CHEESE_PRESS(List.of(ItemType.Wood, ItemType.Stone, ItemType.CopperBar), List.of(45, 45, 1), 0),
    KEG(List.of(ItemType.Wood, ItemType.CopperBar, ItemType.IronBar), List.of(30, 1, 1), 0),
    LOOM(List.of(ItemType.Wood, ItemType.Fiber), List.of(60, 30), 0),
    MAYONNAISE_MACHINE(List.of(ItemType.Wood, ItemType.Stone, ItemType.CopperBar), List.of(15, 15, 1), 0),
    OIL_MAKER(List.of(ItemType.Wood, ItemType.GoldBar, ItemType.IronBar), List.of(100, 1, 1), 0),
    PRESERVES_JAR(List.of(ItemType.Wood, ItemType.Stone, ItemType.Coal), List.of(50, 40, 8), 0),
    DEHYDRATOR(List.of(ItemType.Wood, ItemType.Stone, ItemType.Fiber), List.of(30, 20, 30), 0),
    GRASS_STARTER(List.of(ItemType.Wood, ItemType.Fiber), List.of(1, 1), 0),
    FISH_SMOKER(List.of(ItemType.Wood, ItemType.IronBar, ItemType.Coal), List.of(50, 3, 10), 0),
    MYSTIC_TREE_SEED(List.of(ItemType.Acorns, ItemType.MapleSeed, ItemType.PineCones, ItemType.MahoganySeed), List.of(5, 5, 5, 5), 100);

    private final HashMap<ItemType, Integer> items;
    private final int price;

    Recipe(List<ItemType> resources, List<Integer> quantities, int price) {
        this.items = new HashMap<>();
        for (int i = 0; i < resources.size(); i++) {
            this.items.put(resources.get(i), quantities.get(i));
        }
        this.price = price;
    }

    public HashMap<ItemType, Integer> getRecipe() {
        return items;
    }

    public int getPrice() {
        return price;
    }
}
package models.enums;



import java.util.HashMap;
import java.util.List;

public enum Recipe {
    CherryBomb(List.of(ItemType.CopperOre, ItemType.Coal), List.of(4, 1), 50),
    Bomb(List.of(ItemType.IronOre, ItemType.Coal), List.of(4, 1), 50),
    MegaBomb(List.of(ItemType.GoldOre, ItemType.Coal), List.of(4, 1), 50),
    Sprinkler(List.of(ItemType.CopperBar, ItemType.IronBar), List.of(1, 1), 0),
    QualitySprinkler(List.of(ItemType.IronBar, ItemType.GoldBar), List.of(1, 1), 0),
    IridiumSprinkler(List.of(ItemType.GoldBar, ItemType.IridiumBar), List.of(1, 1), 0),
    CharcoalKlin(List.of(ItemType.Wood, ItemType.CopperBar), List.of(20, 2), 0),
    Furnace(List.of(ItemType.CopperOre, ItemType.Stone), List.of(20, 25), 0),
    Scarecrow(List.of(ItemType.Wood, ItemType.Coal, ItemType.Fiber), List.of(50, 1, 20), 0),
    DeluxeScarecrow(List.of(ItemType.Wood, ItemType.Coal, ItemType.Fiber, ItemType.IridiumOre), List.of(50, 1, 20, 1), 0),
    BeeHouse(List.of(ItemType.Wood, ItemType.Coal, ItemType.IronBar), List.of(40, 8, 1), 0),
    CheesePress(List.of(ItemType.Wood, ItemType.Stone, ItemType.CopperBar), List.of(45, 45, 1), 0),
    Keg(List.of(ItemType.Wood, ItemType.CopperBar, ItemType.IronBar), List.of(30, 1, 1), 0),
    Loom(List.of(ItemType.Wood, ItemType.Fiber), List.of(60, 30), 0),
    MayonnaiseMachine(List.of(ItemType.Wood, ItemType.Stone, ItemType.CopperBar), List.of(15, 15, 1), 0),
    OilMaker(List.of(ItemType.Wood, ItemType.GoldBar, ItemType.IronBar), List.of(100, 1, 1), 0),
    PreservesJar(List.of(ItemType.Wood, ItemType.Stone, ItemType.Coal), List.of(50, 40, 8), 0),
    Dehydrator(List.of(ItemType.Wood, ItemType.Stone, ItemType.Fiber), List.of(30, 20, 30), 0),
    GrassStarter(List.of(ItemType.Wood, ItemType.Fiber), List.of(1, 1), 0),
    FishSmoker(List.of(ItemType.Wood, ItemType.IronBar, ItemType.Coal), List.of(50, 3, 10), 0),
    MysticTreeSeed(List.of(ItemType.Acorns, ItemType.MapleSeed, ItemType.PineCones, ItemType.MahoganySeed), List.of(5, 5, 5, 5), 100);

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

    @Override
    public String toString() {
        return "{" +
                "items=" + items +
                ", price=" + price +
                '}';
    }
    public static Recipe getRecipe(String type) {
        try {
            return Recipe.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
package models.shops;

import models.Item;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackSmith implements Mutual{
    private static final BlackSmith blackSmith=new BlackSmith();
    private final ArrayList<ShopItem> items;
    private final HashMap<String,Integer> upgrades;
    private BlackSmith(){
        items=new ArrayList<>(List.of(
                new ShopItem(ItemType.CopperOre,Integer.MAX_VALUE,75,"A common ore that can be smelted into bars."),
                new ShopItem(ItemType.IronOre,Integer.MAX_VALUE,150,"A fairly common ore that can be smelted into bars."),
                new ShopItem(ItemType.Coal,Integer.MAX_VALUE,150,"A combustible rock that is useful for crafting and smelting."),
                new ShopItem(ItemType.GoldOre,Integer.MAX_VALUE,400,"A precious ore that can be smelted into bars.")
        ));
        upgrades=new HashMap<>(Map.of(
                "copper",5,
                "steel",5,
                "gold",5,
                "iridium",5
        ));
    }
    public static BlackSmith getBlackSmith() {
        return blackSmith;
    }
    public ArrayList<ShopItem> getItems() {
        return items;
    }
    public HashMap<String,Integer> getUpgrades() {
        return upgrades;
    }
}

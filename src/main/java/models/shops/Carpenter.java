package models.shops;

import models.Item;
import models.enums.CageType;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Carpenter implements Mutual{
    private static final Carpenter instance = new Carpenter();
    private final ArrayList<ShopItem> permanentItems;
    private final ArrayList<ShopCages> farmBuildings;
    private Carpenter() {
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Wood,Integer.MAX_VALUE,10,"A sturdy, yet flexible plant material with a wide variety of uses."),
                new ShopItem(ItemType.Stone,Integer.MAX_VALUE,20,"A common material with many uses in crafting and building.")
        ));
        farmBuildings = new ArrayList<>(List.of(
                new ShopCages(CageType.BARN,1,6000, Map.of(ItemType.Wood,350,ItemType.Stone,150)),
                new ShopCages(CageType.BIG_BARN,1,12000, Map.of(ItemType.Wood,450,ItemType.Stone,200)),
                new ShopCages(CageType.DELUXE_BARN,1,25000, Map.of(ItemType.Wood,550,ItemType.Stone,300)),
                new ShopCages(CageType.COOP,1,4000, Map.of(ItemType.Wood,300,ItemType.Stone,100)),
                new ShopCages(CageType.BIG_COOP,1,10000, Map.of(ItemType.Wood,4000,ItemType.Stone,150)),
                new ShopCages(CageType.DELUXE_COOP,1,20000, Map.of(ItemType.Wood,500,ItemType.Stone,200)),
                new ShopCages(CageType.WELL,1,1000, Map.of(ItemType.Stone,75)),
                new ShopCages(CageType.SHIPPING_BIN,1,250,Map.of(ItemType.Wood,150))
        ));
    }
    public static Carpenter getCarpenter() {
        return instance;
    }
    public ArrayList<ShopItem> getPermanentItems() {
        return permanentItems;
    }
    public ArrayList<ShopCages> getFarmBuildings() {
        return farmBuildings;
    }
}

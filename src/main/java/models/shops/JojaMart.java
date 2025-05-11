package models.shops;

import models.Item;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class JojaMart implements Mutual{
    private static final JojaMart instance = new JojaMart();
    private final ArrayList<ShopItem> permanentShopItems;
    private final ArrayList<ShopItem> springShopItems;
    private final ArrayList<ShopItem> summerShopItems;
    private final ArrayList<ShopItem> fallShopItems;
    private final ArrayList<ShopItem> winterShopItems;
    private JojaMart() {
        permanentShopItems = new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.JojaCola),Integer.MAX_VALUE,75),
                new ShopItem(new Item(ItemType.AncientSeed),1,500),
                new ShopItem(new Item(ItemType.GrassStarter),Integer.MAX_VALUE,125),
                new ShopItem(new Item(ItemType.Sugar),Integer.MAX_VALUE,125),
                new ShopItem(new Item(ItemType.WheatFlour),Integer.MAX_VALUE,125),
                new ShopItem(new Item(ItemType.Rice), Integer.MAX_VALUE,250)
        ));
        springShopItems = new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.ParsnipSeed), 5, 25),
                new ShopItem(new Item(ItemType.BeanStarter), 5, 75),
                new ShopItem(new Item(ItemType.CauliflowerSeed), 5, 100),
                new ShopItem(new Item(ItemType.PotatoSeed), 5, 62),
                new ShopItem(new Item(ItemType.StrawberrySeed), 5, 100),
                new ShopItem(new Item(ItemType.TulipBulb), 5, 25),
                new ShopItem(new Item(ItemType.KaleSeed), 5, 87),
                new ShopItem(new Item(ItemType.CoffeeBean), 1, 200),
                new ShopItem(new Item(ItemType.CarrotSeed), 10, 5),
                new ShopItem(new Item(ItemType.RhubarbSeed), 5, 100),
                new ShopItem(new Item(ItemType.JazzSeed), 5, 37)
        ));
        summerShopItems = new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.TomatoSeed), 5, 62),
                new ShopItem(new Item(ItemType.PepperSeed), 5, 50),
                new ShopItem(new Item(ItemType.WheatSeed), 10, 12),
                new ShopItem(new Item(ItemType.SummerSquashSeed), 10, 10),
                new ShopItem(new Item(ItemType.RadishSeed), 5, 50),
                new ShopItem(new Item(ItemType.MelonSeed), 5, 100),
                new ShopItem(new Item(ItemType.HopsStarter), 5, 75),
                new ShopItem(new Item(ItemType.PoppySeed), 5, 125),
                new ShopItem(new Item(ItemType.SpangleSeed), 5, 62),
                new ShopItem(new Item(ItemType.StarfruitSeed), 5, 400),
                new ShopItem(new Item(ItemType.CoffeeBean), 1, 200),
                new ShopItem(new Item(ItemType.SunflowerSeed), 5, 125)
        ));
        fallShopItems = new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.CornSeed), 5, 18),
                new ShopItem(new Item(ItemType.EggplantSeed), 5, 25),
                new ShopItem(new Item(ItemType.PumpkinSeed), 5, 125),
                new ShopItem(new Item(ItemType.BroccoliSeed), 5, 15),
                new ShopItem(new Item(ItemType.AmaranthSeed), 5, 87),
                new ShopItem(new Item(ItemType.GrapeStarter), 5, 75),
                new ShopItem(new Item(ItemType.BeetSeed), 5, 20),
                new ShopItem(new Item(ItemType.YamSeed), 5, 75),
                new ShopItem(new Item(ItemType.BokChoySeed), 5, 62),
                new ShopItem(new Item(ItemType.CranberrySeed), 5, 300),
                new ShopItem(new Item(ItemType.SunflowerSeed), 5, 125),
                new ShopItem(new Item(ItemType.FairySeed), 5, 250),
                new ShopItem(new Item(ItemType.RareSeed), 1, 1000),
                new ShopItem(new Item(ItemType.WheatSeed), 5, 12)
        ));
        winterShopItems=new ArrayList<>(List.of(
                new ShopItem(new Item(ItemType.PowderMelonSeed),10,20)
        ));
    }
    public static JojaMart getJojaMart() {
        return instance;
    }
    public ArrayList<ShopItem> getPermanentShopItems() {
        return permanentShopItems;
    }
    public ArrayList<ShopItem> getFallShopItems() {
        return fallShopItems;
    }
    public ArrayList<ShopItem> getWinterShopItems() {
        return winterShopItems;
    }
    public ArrayList<ShopItem> getSummerShopItems() {
        return summerShopItems;
    }
    public ArrayList<ShopItem> getSpringShopItems(){
        return springShopItems;
    }
}

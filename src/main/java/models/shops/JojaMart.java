package models.shops;

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
                new ShopItem(ItemType.JojaCola,Integer.MAX_VALUE,75),
                new ShopItem(ItemType.AncientSeed,1,500),
                new ShopItem(ItemType.GrassStarter,Integer.MAX_VALUE,125),
                new ShopItem(ItemType.Sugar,Integer.MAX_VALUE,125),
                new ShopItem(ItemType.WheatFlour,Integer.MAX_VALUE,125),
                new ShopItem(ItemType.Rice, Integer.MAX_VALUE,250)
        ));
        springShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.ParsnipSeed, 5, 25),
                new ShopItem(ItemType.BeanStarter, 5, 75),
                new ShopItem(ItemType.CauliflowerSeed, 5, 100),
                new ShopItem(ItemType.PotatoSeed, 5, 62),
                new ShopItem(ItemType.StrawberrySeed, 5, 100),
                new ShopItem(ItemType.TulipBulb, 5, 25),
                new ShopItem(ItemType.KaleSeed, 5, 87),
                new ShopItem(ItemType.CoffeeBean, 1, 200),
                new ShopItem(ItemType.CarrotSeed, 10, 5),
                new ShopItem(ItemType.RhubarbSeed, 5, 100),
                new ShopItem(ItemType.JazzSeed, 5, 37)
        ));
        summerShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.TomatoSeed, 5, 62),
                new ShopItem(ItemType.PepperSeed, 5, 50),
                new ShopItem(ItemType.WheatSeed, 10, 12),
                new ShopItem(ItemType.SummerSquashSeed, 10, 10),
                new ShopItem(ItemType.RadishSeed, 5, 50),
                new ShopItem(ItemType.MelonSeed, 5, 100),
                new ShopItem(ItemType.HopsStarter, 5, 75),
                new ShopItem(ItemType.PoppySeed, 5, 125),
                new ShopItem(ItemType.SpangleSeed, 5, 62),
                new ShopItem(ItemType.StarfruitSeed, 5, 400),
                new ShopItem(ItemType.CoffeeBean, 1, 200),
                new ShopItem(ItemType.SunflowerSeed, 5, 125)
        ));
        fallShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.CornSeed, 5, 18),
                new ShopItem(ItemType.EggplantSeed, 5, 25),
                new ShopItem(ItemType.PumpkinSeed, 5, 125),
                new ShopItem(ItemType.BroccoliSeed, 5, 15),
                new ShopItem(ItemType.AmaranthSeed, 5, 87),
                new ShopItem(ItemType.GrapeStarter, 5, 75),
                new ShopItem(ItemType.BeetSeed, 5, 20),
                new ShopItem(ItemType.YamSeed, 5, 75),
                new ShopItem(ItemType.BokChoySeed, 5, 62),
                new ShopItem(ItemType.CranberrySeed, 5, 300),
                new ShopItem(ItemType.SunflowerSeed, 5, 125),
                new ShopItem(ItemType.FairySeed, 5, 250),
                new ShopItem(ItemType.RareSeed, 1, 1000),
                new ShopItem(ItemType.WheatSeed, 5, 12)
        ));
        winterShopItems=new ArrayList<>(List.of(
                new ShopItem(ItemType.PowderMelonSeed,10,20)
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

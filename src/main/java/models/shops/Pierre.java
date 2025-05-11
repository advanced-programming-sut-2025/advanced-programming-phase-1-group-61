package models.shops;

import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Pierre implements Mutual{
    private final static Pierre instance = new Pierre();
    private final ArrayList<ShopItem> yearRoundItems;
    private Pierre() {
        yearRoundItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Rice, Integer.MAX_VALUE, 200, "A basic grain often served under vegetables."),
                new ShopItem(ItemType.WheatFlour, Integer.MAX_VALUE, 100, "A common cooking ingredient made from crushed wheat seeds."),
                new ShopItem(ItemType.Bouquet, 2, 1000, "A gift that shows your romantic interest. (Unlocked after reaching level 2 friendship with a player)"),
                new ShopItem(ItemType.WeddingRing, 2, 10000, "It's used to ask for another farmer's hand in marriage. (Unlocked after reaching level 3 friendship with a player)"),
                new ShopItem(ItemType.Sugar, Integer.MAX_VALUE, 100, "Adds sweetness to pastries and candies. Too much can be unhealthy."),
                new ShopItem(ItemType.Oil, Integer.MAX_VALUE, 200, "All purpose cooking oil."),
                new ShopItem(ItemType.Vinegar, Integer.MAX_VALUE, 200, "An aged fermented liquid used in many cooking recipes."),
                new ShopItem(ItemType.DeluxeRetainingSoil, Integer.MAX_VALUE, 150, "This soil has a 100% chance of staying watered overnight. Mix into tilled soil."),
                new ShopItem(ItemType.GrassStarter, Integer.MAX_VALUE, 100, "Place this on your farm to start a new patch of grass."),
                new ShopItem(ItemType.SpeedGro, Integer.MAX_VALUE, 100, "Makes the plants grow 1 day earlier."),
                new ShopItem(ItemType.AppleSapling, Integer.MAX_VALUE, 4000, "Takes 28 days to produce a mature Apple tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.ApricotSapling, Integer.MAX_VALUE, 2000, "Takes 28 days to produce a mature Apricot tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.CherrySapling, Integer.MAX_VALUE, 3400, "Takes 28 days to produce a mature Cherry tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.OrangeSapling, Integer.MAX_VALUE, 4000, "Takes 28 days to produce a mature Orange tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.PeachSapling, Integer.MAX_VALUE, 6000, "Takes 28 days to produce a mature Peach tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.PomegranateSapling, Integer.MAX_VALUE, 6000, "Takes 28 days to produce a mature Pomegranate tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty."),
                new ShopItem(ItemType.BasicRetainingSoil, Integer.MAX_VALUE, 100, "This soil has a chance of staying watered overnight. Mix into tilled soil."),
                new ShopItem(ItemType.QualityRetainingSoil, Integer.MAX_VALUE, 150, "This soil has a good chance of staying watered overnight. Mix into tilled soil.")
        ));
    }
    public static Pierre getPierre() {
        return instance;
    }

    public ArrayList<ShopItem> getYearRoundItems() {
        return yearRoundItems;
    }
}
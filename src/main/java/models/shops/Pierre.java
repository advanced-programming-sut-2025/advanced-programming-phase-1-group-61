package models.shops;

import models.enums.BackpackType;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Pierre implements Mutual{
    private final static Pierre instance = new Pierre();
    private final ArrayList<ShopItem> yearRoundItems;
    private final ArrayList<ShopItem> springItems;
    private final ArrayList<ShopItem> summerItems;
    private final ArrayList<ShopItem> fallItems;
    private final ArrayList<ShopBackpacks> shopBackpacks;
    private final float outOfSeasonCoefficient=1.5f;
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
        shopBackpacks = new ArrayList<>(List.of(
                new ShopBackpacks(BackpackType.BIG,1,2000,"Unlocks the 2nd row of inventory (12 more slots, total 24)."),
                new ShopBackpacks(BackpackType.DELCUS,1,10000,"Unlocks the 3rd row of inventory (infinite slots).")
        ));
        springItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.ParsnipSeed, 5, 20, "Plant these in the spring. Takes 4 days to mature."),
                new ShopItem(ItemType.BeanStarter, 5, 60, "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.CauliflowerSeed, 5, 80, "Plant these in the spring. Takes 12 days to produce a large cauliflower."),
                new ShopItem(ItemType.PotatoSeed, 5, 50, "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest."),
                new ShopItem(ItemType.TulipBulb, 5, 20, "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors."),
                new ShopItem(ItemType.KaleSeed, 5, 70, "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe."),
                new ShopItem(ItemType.JazzSeed, 5, 30, "Plant in spring. Takes 7 days to produce a blue puffball flower."),
                new ShopItem(ItemType.GarlicSeed, 5, 40, "Plant these in the spring. Takes 4 days to mature."),
                new ShopItem(ItemType.RiceShoot, 5, 40, "Plant these in the spring. Takes 8 days to mature. Grows faster if planted near a body of water. Harvest with the scythe.")
        ));
        summerItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.MelonSeed, 5, 80, "Plant these in the summer. Takes 12 days to mature."),
                new ShopItem(ItemType.TomatoSeed, 5, 50, "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.BlueberrySeed, 5, 80, "Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.PepperSeed, 5, 40, "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.WheatSeed, 5, 10, "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe."),
                new ShopItem(ItemType.RadishSeed, 5, 40, "Plant these in the summer. Takes 6 days to mature."),
                new ShopItem(ItemType.PoppySeed, 5, 100, "Plant in summer. Produces a bright red flower in 7 days."),
                new ShopItem(ItemType.SpangleSeed, 5, 50, "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors."),
                new ShopItem(ItemType.HopsStarter, 5, 60, "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.CornSeed, 5, 150, "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.SunflowerSeed, 5, 200, "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest."),
                new ShopItem(ItemType.RedCabbageSeed, 5, 100, "Plant these in the summer. Takes 9 days to mature.")
        ));
        fallItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.EggplantSeed, 5, 20, "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.CornSeed, 5, 150, "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.PumpkinSeed, 5, 100, "Plant these in the fall. Takes 13 days to mature."),
                new ShopItem(ItemType.BokChoySeed, 5, 50, "Plant these in the fall. Takes 4 days to mature."),
                new ShopItem(ItemType.YamSeed, 5, 60, "Plant these in the fall. Takes 10 days to mature."),
                new ShopItem(ItemType.CranberrySeed, 5, 240, "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.SunflowerSeed, 5, 200, "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest."),
                new ShopItem(ItemType.FairySeed, 5, 200, "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors."),
                new ShopItem(ItemType.AmaranthSeed, 5, 70, "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe."),
                new ShopItem(ItemType.GrapeStarter, 5, 60, "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.WheatSeed, 5, 10, "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe."),
                new ShopItem(ItemType.ArtichokeSeed, 5, 30, "Plant these in the fall. Takes 8 days to mature.")
        ));
    }
    public static Pierre getPierre() {
        return instance;
    }

    public ArrayList<ShopItem> getYearRoundItems() {
        return yearRoundItems;
    }

    public ArrayList<ShopItem> getFallItems() {
        return fallItems;
    }
    public ArrayList<ShopItem> getSpringItems(){
        return springItems;
    }
    public ArrayList<ShopItem> getSummerItems(){
        return summerItems;
    }
}
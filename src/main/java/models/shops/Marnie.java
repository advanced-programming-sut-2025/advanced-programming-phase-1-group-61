package models.shops;

import models.Item;
import models.enums.AnimalType;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ToolType;
import models.tool.Tool;

import java.util.*;

public class Marnie implements Mutual{
    private final static Marnie instance = new Marnie();
    private final ArrayList<ShopItem> permanentItems;
    private final ArrayList<ShopTool> permanentTools;
    private final ArrayList<ShopAnimals> permanentAnimals;
    private Marnie() {
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Hay,Integer.MAX_VALUE,50,"Dried grass used as animal food.")
        ));
        permanentTools = new ArrayList<>(List.of(
                new ShopTool(new Tool(ToolType.MilkPail),1,1000,"Gather milk from your animals."),
                new ShopTool(new Tool(ToolType.Shear),1,1000,"Use this to collect wool from sheep")
        ));
        permanentAnimals = new ArrayList<>(List.of(
                new ShopAnimals(AnimalType.HEN, CageType.COOP,2,800,"Well cared-for chickens lay eggs every day. Lives in the coop."),
                new ShopAnimals(AnimalType.COW, CageType.BARN,2,1500,"Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn."),
                new ShopAnimals(AnimalType.GOAT, CageType.BIG_BARN,2,1400,"Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn."),
                new ShopAnimals(AnimalType.DUCK, CageType.BIG_COOP,2,1200,"Happy lay duck eggs every other day. Lives in the coop."),
                new ShopAnimals(AnimalType.SHEEP, CageType.DELUXE_BARN,2,8000,"Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn."),
                new ShopAnimals(AnimalType.RABBIT, CageType.DELUXE_COOP,2,8000,"These are wooly rabbits! They shed precious wool every few days. Lives in the coop."),
                new ShopAnimals(AnimalType.DINOSAUR, CageType.BIG_COOP,2,14000,"The Dinosaur is a farm animal that lives in a Big Coop"),
                new ShopAnimals(AnimalType.PIG, CageType.DELUXE_BARN,2,16000,"These pigs are trained to find truffles! Lives in the barn.")
        ));
    }
    public static Marnie getMarnie() {
        return instance;
    }
    public ArrayList<ShopItem> getPermanentItems() {
        return permanentItems;
    }
    public ArrayList<ShopTool> getPermanentTools() {
        return permanentTools;
    }
    public ArrayList<ShopAnimals> getPermanentAnimals() {
        return permanentAnimals;
    }
}

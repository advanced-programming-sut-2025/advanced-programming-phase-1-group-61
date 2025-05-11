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
                new ShopItem(ItemType.Hay,Integer.MAX_VALUE,50)
        ));
        permanentTools = new ArrayList<>(List.of(
                new ShopTool(new Tool(ToolType.MilkPail),1,1000),
                new ShopTool(new Tool(ToolType.Shear),1,1000)
        ));
        permanentAnimals = new ArrayList<>(List.of(
                new ShopAnimals(AnimalType.HEN, CageType.COOP,2,800),
                new ShopAnimals(AnimalType.COW, CageType.BARN,2,1500),
                new ShopAnimals(AnimalType.GOAT, CageType.BIG_BARN,2,1400),
                new ShopAnimals(AnimalType.DUCK, CageType.BIG_COOP,2,1200),
                new ShopAnimals(AnimalType.SHEEP, CageType.DELUXE_BARN,2,8000),
                new ShopAnimals(AnimalType.RABBIT, CageType.DELUXE_COOP,2,8000),
                new ShopAnimals(AnimalType.DINOSAUR, CageType.BIG_COOP,2,14000),
                new ShopAnimals(AnimalType.PIG, CageType.DELUXE_BARN,2,16000)
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

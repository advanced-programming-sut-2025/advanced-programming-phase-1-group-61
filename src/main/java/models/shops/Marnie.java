package models.shops;

import models.App;
import models.Item;
import models.animal.Animal;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.BuildingType;
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
                new ShopItem(new Item(ItemType.Hay),Integer.MAX_VALUE,50)
        ));
        permanentTools = new ArrayList<>(List.of(
                new ShopTool(new Tool(ToolType.MilkPail),1,1000),
                new ShopTool(new Tool(ToolType.Shear),1,1000)
        ));
        Character character= App.getCurrentGame().getCurrentCharacter();
        permanentAnimals = new ArrayList<>(List.of(
                new ShopAnimals(new Animal(AnimalType.HEN,character, BuildingType.COOP),2,800),
                new ShopAnimals(new Animal(AnimalType.COW,character, BuildingType.BARN),2,1500),
                new ShopAnimals(new Animal(AnimalType.GOAT,character, BuildingType.BIG_BARN),2,1400),
                new ShopAnimals(new Animal(AnimalType.DUCK,character, BuildingType.BIG_COOP),2,1200),
                new ShopAnimals(new Animal(AnimalType.SHEEP,character, BuildingType.DELUXE_BARN),2,8000),
                new ShopAnimals(new Animal(AnimalType.RABBIT,character, BuildingType.DELUXE_COOP),2,8000),
                new ShopAnimals(new Animal(AnimalType.DINOSAUR,character, BuildingType.BIG_COOP),2,14000),
                new ShopAnimals(new Animal(AnimalType.PIG,character, BuildingType.DELUXE_BARN),2,16000)
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

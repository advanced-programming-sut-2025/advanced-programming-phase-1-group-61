package models.shops;

import models.building.Shop;
import models.enums.AnimalType;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ToolType;
import models.tool.Tool;

import java.util.*;

public class Marnie extends Shop {
    private final ArrayList<ShopItem> permanentItems;
    private final ArrayList<ShopTool> permanentTools;
    private final ArrayList<ShopAnimals> permanentAnimals;

    public Marnie(String type, String name, int X, int Y) {
        super(type, name, X, Y);
        this.owner="Marnie";
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Hay,Integer.MAX_VALUE,50,"Dried grass used as animal food.")
        ));
        permanentTools = new ArrayList<>(List.of(
                new ShopTool(ToolType.MilkPail,1,1000,"Gather milk from your animals."),
                new ShopTool(ToolType.Shear,1,1000,"Use this to collect wool from sheep")
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

    @Override
    public String showAllProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("permanent items:").append("\n");
        for(ShopItem item : permanentItems) {
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("permanent tools:").append("\n");
        for(ShopTool tool : permanentTools) {
            builder.append("Name: ")
                    .append(tool.getTool().getTool())
                    .append(" | Price: ")
                    .append(tool.getPrice())
                    .append("\n");
        }
        builder.append("permanent animals:").append("\n");
        for(int i=0; i<permanentAnimals.size(); i++) {
            ShopAnimals animal = permanentAnimals.get(i);
            builder.append("Name: ")
                    .append(animal.getAnimal().getDisplayName())
                    .append(" | Price: ")
                    .append(animal.getPrice());
            if(i!=permanentAnimals.size()-1) builder.append("\n");
        }
        return builder.toString();
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

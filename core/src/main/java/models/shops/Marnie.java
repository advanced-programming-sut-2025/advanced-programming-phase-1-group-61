package models.shops;

import models.App;
import models.animal.Animal;
import models.building.Shop;
import models.character.Character;
import models.enums.*;

import java.util.*;

public class Marnie extends Shop {
    private final ArrayList<ShopItem> permanentItems;
    private final ArrayList<ShopTool> permanentTools;
    private final ArrayList<ShopAnimals> permanentAnimals;

    public Marnie( String name, int X, int Y) {
        super( name, X, Y,ShopType.Marnie);
        this.owner="Marnie";
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Hay,Integer.MAX_VALUE,50,"Dried grass used as animal food.")
        ));
        permanentTools = new ArrayList<>(List.of(
                new ShopTool(ToolType.MilkPail,1,1000,"Gather milk from your animals."),
                new ShopTool(ToolType.Shear,1,1000,"Use this to collect wool from sheep")
        ));
        permanentAnimals = new ArrayList<>(List.of(
                new ShopAnimals(AnimalType.HEN, CageType.Coop,2,800,"Well cared-for chickens lay eggs every day. Lives in the coop."),
                new ShopAnimals(AnimalType.COW, CageType.Barn,2,1500,"Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn."),
                new ShopAnimals(AnimalType.GOAT, CageType.BigBarn,2,1400,"Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn."),
                new ShopAnimals(AnimalType.DUCK, CageType.BigCoop,2,1200,"Happy lay duck eggs every other day. Lives in the coop."),
                new ShopAnimals(AnimalType.SHEEP, CageType.DeluxeBarn,2,8000,"Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn."),
                new ShopAnimals(AnimalType.RABBIT, CageType.DeluxCoop,2,8000,"These are wooly rabbits! They shed precious wool every few days. Lives in the coop."),
                new ShopAnimals(AnimalType.DINOSAUR, CageType.BigCoop,2,14000,"The Dinosaur is a farm animal that lives in a Big Coop"),
                new ShopAnimals(AnimalType.PIG, CageType.DeluxeBarn,2,16000,"These pigs are trained to find truffles! Lives in the barn.")
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

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("permanent items:").append("\n");
        for(ShopItem item : permanentItems) {
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append(" | Stock: ")
                        .append(item.getStock())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("permanent tools:").append("\n");
        for(ShopTool tool : permanentTools) {
            if(tool.getStock()>0) {
                builder.append("Name: ")
                        .append(tool.getTool().getTool())
                        .append(" | Price: ")
                        .append(tool.getPrice())
                        .append(" | Stock: ")
                        .append(tool.getStock())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("permanent animals:").append("\n");
        for(int i=0; i<permanentAnimals.size(); i++) {
            ShopAnimals animal = permanentAnimals.get(i);
            if(animal.getStock()>0) {
                builder.append("Name: ")
                        .append(animal.getAnimal().getDisplayName())
                        .append(" | Price: ")
                        .append(animal.getPrice())
                        .append(" | Stock: ")
                        .append(animal.getStock());
                if (i != permanentAnimals.size() - 1) builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        Character character= App.getCurrentGame().getCurrentCharacter();
        for(ShopItem item : permanentItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                character.getInventory().addItem(item.getItem(),count);
                return "Successfully purchased!";
            }
        }
        for(ShopTool tool : permanentTools){
            if(tool.getTool().getTool().equals(product)){
                if(count> tool.getStock()) return "not enough stock!";
                tool.setStock(tool.getStock()-count);
                character.getInventory().addTool(tool.getTool());
                return "Successfully purchased!";
            }
        }
        for(ShopAnimals animal : permanentAnimals){
            if(animal.getAnimal().getDisplayName().equals(product)){
                if(count> animal.getStock()) return "not enough stock!";
                AnimalType type=animal.getAnimal();
                String house=Animal.getHouse(type);
                String animalName="bought animal";
                if(house==null) return "no empty house for this animal";
                if(character.getMoney()<animal.getPrice()) return "not enough money!";
                if(Animal.buy(type,house,animalName)) {
                    animal.setStock(animal.getStock() - count);
                    return "Successfully purchased!";
                }
            }
        }
        return "successfully purchased";
    }

    @Override
    public void restoreStocks() {
        for(ShopItem item : permanentItems) item.restoreStock();
        for(ShopTool tool : permanentTools) tool.restoreStock();
        for(ShopAnimals animal : permanentAnimals) animal.restoreStock();
    }

    public int getOpenHour(){
        return 9;
    }
    public int getCloseHour(){
        return 15;
    }
    public ArrayList<ShopItem> getShopItems() {
        return permanentItems;
    }
    public ArrayList<ShopTool> getShopTools() {
        return permanentTools;
    }
    public ArrayList<ShopAnimals> getShopAnimals() {
        return permanentAnimals;
    }
}

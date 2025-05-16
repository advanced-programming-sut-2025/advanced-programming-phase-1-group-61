package models.shops;

import models.building.Shop;

import java.util.ArrayList;
import java.util.List;

public class FishShop extends Shop {
    private final ArrayList<ShopFishingPoleUpgrades> fishingPoles;
    public FishShop( String name, int X, int Y) {
        super( name, X, Y);
        this.owner="Willy";
        fishingPoles = new ArrayList<>(List.of(
                new ShopFishingPoleUpgrades("training",25,1,1,"It's a lot easier to use than other rods, but can only catch basic fish.","Training Rod"),
                new ShopFishingPoleUpgrades("bamboo",500,1,1,"Use in the water to catch fish.","Bamboo Pole"),
                new ShopFishingPoleUpgrades("fiberGlass",1800,1,2,"Use in the water to catch fish.","Fiberglass Rod"),
                new ShopFishingPoleUpgrades("iridium",7500,1,1,"Use in the water to catch fish.","Iridium Rod")
        ));
    }

    @Override
    public String showAllProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("fishing poles:\n");
        for(ShopFishingPoleUpgrades pole : fishingPoles){
            builder.append("Name: ")
                    .append(pole.getDescription())
                    .append(" | Price: ")
                    .append(pole.getPrice())
                    .append("\n");
        }
        return builder.toString();
    }

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("fishing poles:\n");
        for(ShopFishingPoleUpgrades pole : fishingPoles){
            if(pole.getStock()>0) {
                builder.append("Name: ")
                        .append(pole.getDescription())
                        .append(" | Price: ")
                        .append(pole.getPrice())
                        .append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        for(ShopFishingPoleUpgrades pole : fishingPoles){
            if(pole.getDisplayName().equals(product)) {
                if(pole.getStock()<count) return "not enough stock!";
                pole.setStock(pole.getStock()-count);
                return "successfully purchased!";
            }
        }
        return "please enter a valid product!";
    }

    @Override
    public void restoreStocks() {
        for(ShopFishingPoleUpgrades pole : fishingPoles) pole.restoreStock();
    }
}

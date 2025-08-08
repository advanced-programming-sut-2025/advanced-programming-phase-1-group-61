package models.shops;

import io.github.camera.Main;
import models.App;
import models.building.Shop;
import models.character.Character;
import models.enums.Recipe;
import models.enums.ShopType;
import models.enums.ToolType;

import java.util.ArrayList;
import java.util.List;

public class FishShop extends Shop {
    private  ArrayList<ShopFishingPoleUpgrades> fishingPoles;
    private  ArrayList<ShopRecipes> shopRecipes;

    public FishShop() {
    }

    public FishShop(String name, int X, int Y) {
        super( name, X, Y,ShopType.FishShop);
        this.owner="Willy";
        fishingPoles = new ArrayList<>(List.of(
                new ShopFishingPoleUpgrades("training",25,1,1,"It's a lot easier to use than other rods, but can only catch basic fish.","Training Rod"),
                new ShopFishingPoleUpgrades("bamboo",500,1,1,"Use in the water to catch fish.","Bamboo Pole"),
                new ShopFishingPoleUpgrades("fiberGlass",1800,1,2,"Use in the water to catch fish.","Fiberglass Rod"),
                new ShopFishingPoleUpgrades("iridium",7500,1,1,"Use in the water to catch fish.","Iridium Rod")
        ));
        shopRecipes = new ArrayList<>(List.of(
                new ShopRecipes(Recipe.FishSmoker,1,10000,"A recipe to make Fish Smoker")
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
        builder.append("recipes:\n");
        for(ShopRecipes recipe:shopRecipes){
            builder.append("Name: ")
                    .append(recipe.getRecipe().name())
                    .append(" | Price: ")
                    .append(recipe.getPrice())
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
        builder.append("recipes:\n");
        for(ShopRecipes recipe:shopRecipes){
            if(recipe.getStock()>0) {
                builder.append("Name: ")
                        .append(recipe.getRecipe().name())
                        .append(" | Price: ")
                        .append(recipe.getPrice())
                        .append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
        for(ShopFishingPoleUpgrades pole : fishingPoles){
            if(pole.getDisplayName().equals(product)) {
                if(pole.getStock()<count) return "not enough stock!";
                pole.setStock(pole.getStock()-count);
                character.getInventory().upgradeFishingPole(product);
                return "successfully purchased!";
            }
        }
        for(ShopRecipes recipe : shopRecipes){
            if(recipe.getRecipe().name().equals(product)){
                if(count> recipe.getStock()) return "not enough stock!";
                recipe.setStock(recipe.getStock()-count);
                character.getRecipes().add(recipe.getRecipe());
                return "Successfully purchased!";
            }
        }
        return "please enter a valid product!";
    }

    @Override
    public void restoreStocks() {
        for(ShopFishingPoleUpgrades pole : fishingPoles) pole.restoreStock();
        for(ShopRecipes recipe : shopRecipes) recipe.restoreStock();
    }

    public int getOpenHour(){
        return 9;
    }
    public int getCloseHour(){
        return 16;
    }
    public ArrayList<ShopFishingPoleUpgrades> getFishingPoles() {
        return fishingPoles;
    }
    public ArrayList<ShopRecipes> getShopRecipes() {
        return shopRecipes;
    }
}

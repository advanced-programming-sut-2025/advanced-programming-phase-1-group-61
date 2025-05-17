package models.shops;

import models.App;
import models.building.Shop;
import models.character.Character;
import models.enums.CookingRecipes;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class StarDrop extends Shop {
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopCookingRecipes> recipes;

    public StarDrop(String name, int X, int Y) {
        super(name, X, Y);
        this.items = new ArrayList<>(List.of(
                new ShopItem(ItemType.Beer, Integer.MAX_VALUE, 400, "Drink in moderation."),
                new ShopItem(ItemType.Salad, Integer.MAX_VALUE, 220, "A healthy garden salad."),
                new ShopItem(ItemType.Bread, Integer.MAX_VALUE, 120, "A crusty baguette."),
                new ShopItem(ItemType.Spaghetti, Integer.MAX_VALUE, 240, "An old favorite."),
                new ShopItem(ItemType.Pizza, Integer.MAX_VALUE, 600, "It's popular for all the right reasons."),
                new ShopItem(ItemType.Coffee, Integer.MAX_VALUE, 300, "It smells delicious. This is sure to give you a boost.")
        ));

        this.recipes = new ArrayList<>(List.of(
                new ShopCookingRecipes(CookingRecipes.HashBrowns, 1, 50, "A recipe to make Hashbrowns"),
                new ShopCookingRecipes(CookingRecipes.Omelet, 1, 100, "A recipe to make Omelet"),
                new ShopCookingRecipes(CookingRecipes.Pancakes, 1, 100, "A recipe to make Pancakes"),
                new ShopCookingRecipes(CookingRecipes.Bread, 1, 100, "A recipe to make Bread"),
                new ShopCookingRecipes(CookingRecipes.Tortilla, 1, 100, "A recipe to make Tortilla"),
                new ShopCookingRecipes(CookingRecipes.Pizza, 1, 150, "A recipe to make Pizza"),
                new ShopCookingRecipes(CookingRecipes.MakiRoll, 1, 300, "A recipe to make Maki Roll"),
                new ShopCookingRecipes(CookingRecipes.TripleShotEspresso, 1, 5000, "A recipe to make Triple Shot Espresso"),
                new ShopCookingRecipes(CookingRecipes.Cookie, 1, 300, "A recipe to make Cookie")
        ));
    }

    @Override
    public String showAllProducts() {
        StringBuilder builder=new StringBuilder();
        builder.append("items:").append("\n");
        for(ShopItem item:items){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("recipes:").append("\n");
        for(int i=0;i<recipes.size();i++){
            ShopCookingRecipes recipe=recipes.get(i);
            builder.append("Name: ")
                    .append(recipe.getRecipe().name())
                    .append(" | Price: ")
                    .append(recipe.getPrice());
            if(i!=recipes.size()-1) builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder=new StringBuilder();
        builder.append("items:").append("\n");
        for(ShopItem item:items){
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append("\n");
            }
        }
        builder.append("recipes:").append("\n");
        for(int i=0;i<recipes.size();i++){
            ShopCookingRecipes recipe=recipes.get(i);
            if(recipe.getStock()>0) {
                builder.append("Name: ")
                        .append(recipe.getRecipe().name())
                        .append(" | Price: ")
                        .append(recipe.getPrice());
                if (i != recipes.size() - 1) builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        Character character= App.getCurrentGame().getCurrentCharacter();
        for(ShopItem item:items){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                character.getInventory().addItem(item.getItem(),count);
                return "Successfully purchased!";
            }
        }
        for(ShopCookingRecipes recipe:recipes){
            if(recipe.getRecipe().name().equals(product)){
                if(count> recipe.getStock()) return "not enough stock!";
                recipe.setStock(recipe.getStock()-count);
                character.getCookingRecipes().add(recipe.getRecipe());
                return "Successfully purchased!";
            }
        }
        return "Please enter a valid product";
    }

    @Override
    public void restoreStocks() {
        for(ShopItem item:items) item.restoreStock();
        for(ShopCookingRecipes recipe:recipes) recipe.restoreStock();
    }
}

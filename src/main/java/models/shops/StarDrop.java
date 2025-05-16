package models.shops;


import models.building.Shop;
import models.enums.ItemType;
import models.enums.Recipe;

import java.util.ArrayList;
import java.util.List;

public class StarDrop extends Shop {
    //needs recipe
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopRecipes> recipes;

    public StarDrop( String name, int X, int Y) {
        super( name, X, Y);
        this.items = new ArrayList<>(List.of(
                new ShopItem(ItemType.Beer,Integer.MAX_VALUE,400,"Drink in moderation.")
        ));
        this.recipes = new ArrayList<>(List.of(

        ));
    }

    @Override
    public String showAllProducts() {
        return "";
    }

    @Override
    public String showAllAvailableProducts() {
        return "";
    }

    @Override
    public String purchaseProduct(String product, int count) {
        return "";
    }

    @Override
    public void restoreStocks() {

    }
}

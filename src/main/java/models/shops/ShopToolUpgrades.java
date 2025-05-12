package models.shops;

import models.enums.ItemType;

import java.util.ArrayList;

public class ShopToolUpgrades {
    private final String upgradeName;
    private final int limit;
    private final int price;
    private int stock;
    private final ItemType ingredientItem;
    private final int quantityOfIngredient;
    public ShopToolUpgrades(String upgradeName, int limit, int price, ItemType ingredientItem, int quantityOfIngredient) {
        this.upgradeName = upgradeName;
        this.limit = limit;
        this.price = price;
        this.ingredientItem = ingredientItem;
        this.quantityOfIngredient = quantityOfIngredient;
        stock=limit;
    }
    public String getUpgradeName() {
        return upgradeName;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public ItemType getIngredientItem() {
        return ingredientItem;
    }
    public int getQuantityOfIngredient() {
        return quantityOfIngredient;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}

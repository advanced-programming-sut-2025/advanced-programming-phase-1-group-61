package models.shops;

import models.enums.Recipe;

public class ShopRecipes {
    private final Recipe recipe;
    private final int limit;
    private final int price;
    private final String description;
    private int stock;
    public ShopRecipes(Recipe recipe, int limit, int price, String description) {
        this.recipe = recipe;
        this.limit = limit;
        this.price = price;
        this.description = description;
        this.stock = limit;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void restoreStock(){
        this.stock=0;
    }
}

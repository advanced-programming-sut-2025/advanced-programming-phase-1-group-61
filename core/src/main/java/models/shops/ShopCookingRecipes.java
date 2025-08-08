package models.shops;

import models.enums.CookingRecipes;

public class ShopCookingRecipes {
    private  CookingRecipes recipe;
    private  int limit;
    private  int price;
    private int stock;
    private  String description;

    public ShopCookingRecipes() {
    }

    public ShopCookingRecipes(CookingRecipes recipe, int limit, int price, String description) {
        this.recipe = recipe;
        this.limit = limit;
        this.price = price;
        this.description = description;
        this.stock = limit;
    }
    public CookingRecipes getRecipe() {
        return recipe;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public void restoreStock(){
        stock=limit;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getDescription() {
        return description;
    }
}

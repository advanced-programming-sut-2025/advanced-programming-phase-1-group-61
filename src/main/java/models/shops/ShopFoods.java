package models.shops;

import models.food.Food;

public class ShopFoods {
    private final Food food;
    private final int dailyLimit;
    private final int price;
    private final String description;
    private int stock;
    public ShopFoods(Food food, int dailyLimit, int price,String description) {
        this.food = food;
        this.dailyLimit = dailyLimit;
        this.price = price;
        stock=dailyLimit;
        this.description = description;
    }
    public Food getFood() {
        return food;
    }
    public int getDailyLimit() {
        return dailyLimit;
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
}

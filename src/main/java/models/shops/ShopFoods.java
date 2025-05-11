package models.shops;

import models.food.Food;

public class ShopFoods {
    private final Food food;
    private final int dailyLimit;
    private final int price;
    public ShopFoods(Food food, int dailyLimit, int price) {
        this.food = food;
        this.dailyLimit = dailyLimit;
        this.price = price;
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
}

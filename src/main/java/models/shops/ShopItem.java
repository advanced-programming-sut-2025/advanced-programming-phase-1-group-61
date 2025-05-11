package models.shops;

import models.Item;

public class ShopItem {
    private final Item item;
    private final int dailyLimit;
    private final int price;
    public ShopItem(Item item, int limit, int price) {
        this.item = item;
        this.dailyLimit = limit;
        this.price = price;
    }
    public Item getItem() {
        return item;
    }
    public int getDailyLimit() {
        return dailyLimit;
    }
    public int getPrice() {
        return price;
    }
}

package models.shops;

import models.Item;
import models.enums.ItemType;

public class ShopItem {
    private final ItemType item;
    private final int dailyLimit;
    private final int price;
    public ShopItem(ItemType item, int limit, int price) {
        this.item = item;
        this.dailyLimit = limit;
        this.price = price;
    }
    public ItemType getItem() {
        return item;
    }
    public int getDailyLimit() {
        return dailyLimit;
    }
    public int getPrice() {
        return price;
    }
}

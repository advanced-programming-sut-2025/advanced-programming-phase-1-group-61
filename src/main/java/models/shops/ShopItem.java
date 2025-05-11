package models.shops;

import models.Item;
import models.enums.ItemType;

public class ShopItem {
    private final ItemType item;
    private final int dailyLimit;
    private final int price;
    private final String description;
    public ShopItem(ItemType item, int limit, int price,String description) {
        this.item = item;
        this.dailyLimit = limit;
        this.price = price;
        this.description = description;
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
    public String getDescription() {
        return description;
    }
}

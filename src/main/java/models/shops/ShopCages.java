package models.shops;

import models.enums.CageType;
import models.enums.ItemType;

import java.util.Map;

public class ShopCages {
    private final CageType cageType;
    private final int limit;
    private final int price;
    private final Map<ItemType, Integer> priceItems;
    private int stock;
    public ShopCages(CageType cageType, int limit, int price, Map<ItemType, Integer> priceItems) {
        this.cageType = cageType;
        this.limit = limit;
        this.price = price;
        this.priceItems = priceItems;
        stock=limit;
    }
    public CageType getCageType() {
        return cageType;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
    public Map<ItemType, Integer> getPriceItems() {
        return priceItems;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}

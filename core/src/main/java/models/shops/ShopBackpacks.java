package models.shops;

import models.enums.BackpackType;

public class ShopBackpacks {
    private  BackpackType backpackType;
    private  int price;
    private  int limit;
    private  String description;
    private int stock;

    public ShopBackpacks() {
    }

    public ShopBackpacks(BackpackType backpackType, int limit, int price, String description) {
        this.backpackType = backpackType;
        this.limit = limit;
        this.price = price;
        this.description = description;
        stock=limit;
    }
    public BackpackType getBackpackType() {
        return backpackType;
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
        stock=limit;
    }
}

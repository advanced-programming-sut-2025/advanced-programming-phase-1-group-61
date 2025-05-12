package models.building;


import models.enums.ShopType;

public class Shop extends Building {
    private final ShopType shopType;
    public Shop(String type, String name , int X, int Y,ShopType shopType) {
        super(String.valueOf(type), name, X, Y);
        this.shopType = shopType;
    }
    public ShopType getShopType() {
        return shopType;
    }
}

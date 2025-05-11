package models.enums;

import models.shops.*;

public enum ShopType {
    BlackSmith("Blacksmith","Clint", 9,16, models.shops.BlackSmith.getBlackSmith()),
    JojaMart("JojaMart","Morris", 9,23, models.shops.JojaMart.getJojaMart()),
    Pierre("Pierre's General Store","Pierre", 9,17),
    Carpenter("Carpenter's Shop","Robin", 9,20),
    FishShop("Fish Shop","Willy", 9,17),
    MarnieRanch("Marnie's Ranch","Marnie", 9,16),
    StarDropSaloon("The Stardrop Saloon","Gus", 12, 24);
    private final String name;
    private final int openTime;
    private final int closeTime;
    private final String owner;
    private final Mutual shop;
    ShopType(String name,String owner, int openTime, int closeTime, Mutual shop) {
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
        this.shop = shop;
    }
}

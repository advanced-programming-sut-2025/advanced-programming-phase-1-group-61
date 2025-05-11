package models.building;

import models.enums.BuildingType;
import models.enums.ShopType;

public class Shop extends Building {
    public Shop(String type, String name , int X, int Y) {
        super(BuildingType.valueOf(type), name, X, Y);
    }
}

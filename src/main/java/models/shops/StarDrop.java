package models.shops;

import models.enums.FoodType;
import models.enums.ItemType;
import models.food.Food;

import java.util.ArrayList;
import java.util.List;

public class StarDrop implements Mutual{
    private final static StarDrop instance = new StarDrop();
    private StarDrop() {

    }
    public static StarDrop getStarDrop() {
        return instance;
    }
}

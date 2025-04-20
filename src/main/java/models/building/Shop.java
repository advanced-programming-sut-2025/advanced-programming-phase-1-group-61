package models.building;

import models.Item;
import models.enums.ShopType;
import models.food.Food;
import models.resource.Resource;
import models.tool.Tool;

import java.util.ArrayList;

public class Shop {
    ShopType type;
    private ArrayList<Food> foods=new ArrayList<>();
    private ArrayList<Item> resources=new ArrayList<>();
    private ArrayList<Tool> tools=new ArrayList<>();
}

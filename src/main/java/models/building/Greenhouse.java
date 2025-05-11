package models.building;

import models.enums.BuildingType;

public class Greenhouse extends Building{

    public Greenhouse(String type, String name, int x, int y) {
        super(BuildingType.valueOf(type), name,x,y);
    }
}

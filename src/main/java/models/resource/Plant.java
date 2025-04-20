package models.resource;

import models.enums.PlantType;

public class Plant extends Resource {
    private PlantType type;
    private int age;
    private int water ;

    public Plant(PlantType type) {
        this.type = type;
        this.age = 0;
        this.water = 50;
    }
}

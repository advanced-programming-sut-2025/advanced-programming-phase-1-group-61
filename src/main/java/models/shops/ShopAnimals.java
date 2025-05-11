package models.shops;

import models.animal.Animal;
import models.enums.AnimalType;
import models.enums.BuildingType;

public class ShopAnimals {
    private final AnimalType animal;
    private final BuildingType buildingType;
    private final int limit;
    private final int price;
    public ShopAnimals(AnimalType animal,BuildingType buildingType, int limit, int price) {
        this.animal = animal;
        this.limit = limit;
        this.price = price;
        this.buildingType = buildingType;
    }
    public AnimalType getAnimal() {
        return animal;
    }
    public BuildingType getBuildingType() {
        return buildingType;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
}

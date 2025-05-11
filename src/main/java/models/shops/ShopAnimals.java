package models.shops;

import models.enums.AnimalType;
import models.enums.CageType;

public class ShopAnimals {
    private final AnimalType animal;
    private final CageType cageType;
    private final int limit;
    private final int price;
    private final String description;
    public ShopAnimals(AnimalType animal, CageType cageType, int limit, int price, String description) {
        this.animal = animal;
        this.limit = limit;
        this.price = price;
        this.cageType = cageType;
        this.description = description;
    }
    public AnimalType getAnimal() {
        return animal;
    }
    public CageType getBuildingType() {
        return cageType;
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
}

package models.shops;

import models.enums.AnimalType;
import models.enums.CageType;

public class ShopAnimals {
    private  AnimalType animal;
    private CageType cageType;
    private int limit;
    private int price;
    private String description;
    private int stock;

    public ShopAnimals() {
    }

    public ShopAnimals(AnimalType animal, CageType cageType, int limit, int price, String description) {
        this.animal = animal;
        this.limit = limit;
        this.price = price;
        this.cageType = cageType;
        this.description = description;
        stock=limit;
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

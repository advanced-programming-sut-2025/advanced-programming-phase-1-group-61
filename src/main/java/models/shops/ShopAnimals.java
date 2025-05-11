package models.shops;

import models.animal.Animal;

public class ShopAnimals {
    private final Animal animal;
    private final int limit;
    private final int price;
    public ShopAnimals(Animal animal, int limit, int price) {
        this.animal = animal;
        this.limit = limit;
        this.price = price;
    }
    public Animal getAnimal() {
        return animal;
    }
    public int getLimit() {
        return limit;
    }
    public int getPrice() {
        return price;
    }
}

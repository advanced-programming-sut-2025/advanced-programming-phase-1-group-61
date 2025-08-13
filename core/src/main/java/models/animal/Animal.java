package models.animal;

import io.github.camera.Main;
import models.App;

import models.Item;
import models.RandomNumber;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.ItemType;
import java.util.*;


public class Animal {

    private  AnimalType type;
    private  String name;
    private boolean hunger ;
    private int friendship = 0;
    private final List<ItemType> products = new ArrayList<>();
    private int price;


    public Animal() {
    }

    public Animal(AnimalType type,String name) {
        this.type = type;
        this.name = name;
        this.price = type.getPrice();
        this.hunger = true;
    }

    public int getPrice() {
        return price;
    }

    public static boolean buy(AnimalType Type, String name) {
        Character Owner = Main.getApp().getCurrentGame().getCurrentCharacter();
        Animal animal = new Animal(Type , name);
        Owner.addAnimal(animal, name);
        return true;
    }

    public void feed() {
        hunger = false;
    }

    public String getName() {
        return name;
    }

    public int getFriendship() {
        return friendship;
    }

    public AnimalType getType() {
        return type;
    }

    public void changeDayActivity(){
        System.out.println("Changing day for " + name + " hunger=" + hunger + " current products=" + products.size());
            if(products.size() < 5){
                products.add(type.getProduct());
                System.out.println("Product added: " + type.getProduct());
            }
        hunger = true;
        price += 3;
        System.out.println(price);
    }

    public List<ItemType> getProduct() {
        System.out.println("products removed");
        List<ItemType> productsToCollect = new ArrayList<>(products);
        products.clear();
        return productsToCollect;
    }


    public List<ItemType> getProducts() {
        return products;
    }
}

package models.animal;

import models.Item;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {
    private static ArrayList<Animal> allAnimals = new ArrayList<>();
    private AnimalType type;
    protected int hunger;
    protected Character owner;
    protected Building house;
    public Item getProduct(){
        //todo
        return null;
    }
    public void pet(){
        //todo
    }
    public void feed(){
        //todo
    }
    public void move(){
        //todo
    }
}

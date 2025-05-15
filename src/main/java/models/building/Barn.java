package models.building;

import models.animal.Animal;


import java.util.ArrayList;

public class Barn extends Building {
    private ArrayList<Animal> animals = new ArrayList<>();
    public Barn(String type, String name, int X, int Y, int ownerId) {
        super(type,name,X,Y);
        this.space = getSpace(type);
        this.size = this.space;
        this.baseType="Barn";
    }
    private int getSpace(String type) {
        return switch (type) {
            case "Burn" -> 4;
            case "Big Burn" -> 8;
            case "Deluxe Burn" -> 12;
            default -> -1;
        };
    }

    @Override
    public boolean addInput(Object input){
        if (input instanceof Animal animal){
            animals.add(animal);
            space--;
                return true;
        }
        return false;
    }
    @Override
    public void removeInput(Object input){
        if (input instanceof Animal animal){
            animals.remove(animal);
            space++;
        }
    }
}

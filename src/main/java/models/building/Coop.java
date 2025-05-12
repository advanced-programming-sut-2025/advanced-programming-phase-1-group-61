package models.building;

import models.animal.Animal;


import java.util.ArrayList;

public class Coop extends Building {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private int ownerId;
    public Coop(String type, String name, int X, int Y, int owner) {
        super(type, name, X, Y);
        this.ownerId = owner;
        this.space = getSpace(type);
        this.size = this.space;
        this.baseType="Coop";
        this.resourceType ="Coop";
    }

    private int getSpace(String type) {
        return switch (type) {
            case "Coop" -> 4;
            case "Big Coop" -> 8;
            case "Deluxe Coop" -> 12;
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
}

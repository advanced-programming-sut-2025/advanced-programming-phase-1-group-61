package models.building;

import models.animal.Animal;


import java.util.ArrayList;

public class Barn extends Building {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private int ownerId;
    public Barn(String type, String name, int X, int Y, int ownerId) {
        super(type,name,X,Y);
        this.ownerId = ownerId;
        this.baseType="Barn";
        this.resourceType ="Barn";
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

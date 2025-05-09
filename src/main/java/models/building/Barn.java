package models.building;

import models.animal.Animal;
import models.character.Character;

import java.util.ArrayList;

public class Barn extends Building {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private Character owner;
    public Barn(String type,String name,int X,int Y,Character owner) {
        super(type,name,X,Y);
        this.owner = owner;
        this.space=getSpace(type);
        this.size=this.space;
        this.baseType="Barn";
    }

    private int getSpace(String type) {
        return switch (type) {
            case "Barn" -> 4;
            case "Big Barn" -> 8;
            case "Deluxe Barn" -> 12;
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

package models.building;

import models.animal.Animal;
import models.character.Character;

import java.util.ArrayList;

public class Coop extends Building {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private Character owner;
    public Coop(String type, String name, int X, int Y, Character owner) {
        super(type, name, X, Y);
        this.owner = owner;
        this.space = getSpace(type);
        this.size = this.space;
        this.baseType="Coop";
        this.Resourcetype="Coop";
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

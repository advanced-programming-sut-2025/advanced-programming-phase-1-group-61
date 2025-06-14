package models.building;

import models.animal.Animal;
import models.enums.CageType;


import java.util.ArrayList;

public class Coop extends Building {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private CageType cageType ;
    public Coop(CageType cageType, String name, int X, int Y) {
        super(name, X, Y);
        this.cageType = cageType;
        this.space = getSpace(cageType.name());
        this.size = this.space;
        this.baseType="Coop";
    }

    private int getSpace(String type) {
        return switch (type) {
            case "Coop" -> 4;
            case "BigCoop" -> 8;
            case "DeluxCoop" -> 12;
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

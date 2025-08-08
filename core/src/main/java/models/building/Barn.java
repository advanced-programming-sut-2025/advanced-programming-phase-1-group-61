package models.building;

import models.animal.Animal;
import models.enums.CageType;


import java.util.ArrayList;

public class Barn extends Building {
    private ArrayList<Animal> animals = new ArrayList<>();
    private CageType cageType;

    public Barn() {
    }

    public Barn(CageType cageType, String name, int X, int Y) {
        super(name,X,Y);
        this.cageType = cageType;
        this.space = getSpace(cageType.name());
        this.size = this.space;
        this.baseType="Barn";
    }
    private int getSpace(String type) {
        return switch (type) {
            case "Barn" -> 4;
            case "BigBarn" -> 8;
            case "DeluxeBarn" -> 12;
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

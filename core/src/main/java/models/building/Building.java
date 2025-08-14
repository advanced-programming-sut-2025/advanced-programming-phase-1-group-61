package models.building;

import models.animal.Animal;
import models.enums.CageType;
import models.resource.Resource;

import java.util.ArrayList;
import java.util.List;

public class Building extends Resource{
    private CageType type;
    private List<Animal>  animalList ;
    public Building( ) {
    }

    public Building(CageType type) {
        this.type = type;
        this.animalList = new ArrayList<>();
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
    public void addAnimal(Animal animal){
        if(type.getSize() >= animalList.size()){
            animalList.add(animal);
        }
    }

    public CageType getType() {
        return type;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }
}

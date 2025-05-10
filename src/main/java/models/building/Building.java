package models.building;

import models.character.Character;

public class Building {
    protected int space= 0;
    protected String type;
    protected String name;
    public Building(String type, String name){
        this.type = type;
        this.name = name;
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public int getSpace(){
        return space;
    }
    public boolean addInput(Object input){
        return false;
    }

}

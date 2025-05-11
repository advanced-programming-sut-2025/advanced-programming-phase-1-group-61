package models.building;

import models.character.Character;
import models.character.NPC;

public class Building {
    protected int X;
    protected int Y;
    protected int space= 0;
    protected int size=0;
    protected String type;
    protected String name;
    protected String baseType="";
    public Building(String type, String name , int X, int Y) {
        this.type = type;
        this.name = name;
        this.X = X;
        this.Y = Y;
    }
    public int getSize(){
        return size;
    }
    public String getBaseType(){
        return baseType;
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
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }

}

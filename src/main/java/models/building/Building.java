package models.building;

import models.character.Character;
import models.character.NPC;
import models.resource.Resource;

public class Building extends Resource{
    protected int X;
    protected int Y;
    protected int space= 0;
    protected String type;
    protected int size;
    protected String name;
    protected String baseType="";
    public Building(String type, String name , int X, int Y) {
        this.type = type;
        this.name = name;
        this.X = X;
        this.Y = Y;
        this.size = 10;
        this.space = size;
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

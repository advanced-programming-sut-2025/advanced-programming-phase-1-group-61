package models.building;

import models.resource.Resource;

public class Building extends Resource{
    protected int X;
    protected int Y;
    protected int space= 0;


    protected int size;
    protected String name;
    protected String baseType;
    public Building( String name , int X, int Y) {
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
    public String getName(){
        return name;
    }
    public int getSpace(){
        return space;
    }
    public boolean addInput(Object input){
        return false;
    }
    public void removeInput(Object input){
    }
    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }
    public void draw(){
    }
}

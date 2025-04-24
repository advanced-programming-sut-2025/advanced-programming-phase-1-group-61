package models.character;

import models.Item;
import models.User;
import models.animal.Animal;
import models.enums.Recipe;
import models.tool.Tool;

import java.util.ArrayList;

public class Character {
    private int userId;
    private Inventory inventory;
    private int energy;
    private int x;
    private int y;
    private Skill skill;
    private Tool currentTool;
    private ArrayList<Animal> animals=new ArrayList<>();
    private ArrayList<Recipe> recipes=new ArrayList<>();
    public Character(int userId){
        this.userId=userId;
        currentTool=null;
    }
    public void setTool(Tool newTool){
        currentTool=newTool;
    }

    public void faint(){
        //TODO

    }
    public Item fish(){
        return null;
    }
    public void trade(Character character,Item item,int quantity){
        //TODO
    }
    public void artisanUse(){}
    public void craft(){}
    public void cook(){}
}

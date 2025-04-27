package models.tool;

import models.Item;

import java.util.ArrayList;

public class Backpack{
    private ArrayList<Item> items=new ArrayList<>();
    private ArrayList<Tool> tools=new ArrayList<>();
    public void showItems(){
        //todo
    }
    public void addItem(Item item){
        //todo
    }
    public boolean checkToolInBackPack(Tool tool){
        for(Tool t : tools){
            if(t.getType().toString().equals(tool.getType().toString())) return true;
        }
        return false;
    }
    public String getAllTools(){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<tools.size();i++){
            if(i!=tools.size()-1) builder.append(tools.get(i).getType().toString()).append(", ");
            else builder.append(tools.get(i).getType().toString());
        }
        return builder.toString();
    }
}

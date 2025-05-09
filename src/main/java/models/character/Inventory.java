package models.character;

import models.Item;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final HashMap<Item,Integer> allItems=new HashMap<>();
    private final ArrayList<Tool> tools=new ArrayList<>();
    public void addItem(Item item,int count){
        for(int i=0;i<allItems.size();i++){
            Item it=(Item) allItems.keySet().toArray()[i];
            if(it.getItemType().equals(item.getItemType())){
                allItems.put(item,(int)allItems.entrySet().toArray()[i]+count);
                return;
            }
        }
        allItems.put(item,count);
    }
}

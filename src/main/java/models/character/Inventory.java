package models.character;

import models.Item;
import models.enums.BackpackType;
import models.enums.TrashcanType;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final HashMap<Item, Integer> items=new HashMap<>();
    private final ArrayList<Tool> tools=new ArrayList<>();
    private BackpackType backpackType=BackpackType.PRIMARY;
    private Trashcan trashcan=new Trashcan();
    public void addItem(Item item,int count){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item.getItemType())){
                items.put(item,(int)items.entrySet().toArray()[i]+count);
                return;
            }
        }
        items.put(item,count);
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
    public String getItemsInfo(){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<items.size();i++){
            Item item=(Item) items.keySet().toArray()[i];
            int count=items.get(item);
            builder.append(item.getItemType().name()).append(": ").append(count);
            if(i!=items.size()-1) builder.append("\n");
        }
        return builder.toString();
    }
    public HashMap<Item,Integer> getItems(){
        return items;
    }
    public ArrayList<Tool> getTools(){
        return tools;
    }
    public void removeItem(Item item){
        for (int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item.getItemType()))
                items.remove(it);
        }
    }
    public void removeItem(Item item,int count){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item.getItemType()))
                items.put(it,items.get(it) -count);
        }
    }
    public int getCountOfItem(Item item){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item.getItemType())) return items.get(it);
        }
        return 0;
    }
    public BackpackType getBackpackType() {
        return backpackType;
    }
    public void setBackpackType(BackpackType backpackType) {
        this.backpackType = backpackType;
    }
    public Trashcan getTrashcan() {
        return trashcan;
    }
    public void setTrashcan(Trashcan trashcan) {
        this.trashcan = trashcan;
    }
}

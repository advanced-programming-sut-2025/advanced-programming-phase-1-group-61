package models.character;

import models.Item;
import models.enums.BackpackType;
import models.enums.ItemType;
import models.enums.ToolType;
import models.tool.Axe;
import models.tool.Hoe;
import models.tool.Pickaxe;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final HashMap<Item, Integer> items=new HashMap<>();
    private final ArrayList<Tool> tools=new ArrayList<>();
    private BackpackType backpackType=BackpackType.PRIMARY;
    private Trashcan trashcan=new Trashcan();

    public Inventory() {
        tools.add(new Axe());
        tools.add(new Pickaxe());
        tools.add(new Hoe());
    }

    public void addItem(ItemType item, int count){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item)){
                items.put(new Item(item),(int)items.entrySet().toArray()[i]+count);
                return;
            }
        }
        if(backpackType.getSize() > items.size()){
            items.put(new Item(item),count);
        }
    }
    public boolean checkToolInInventory(ToolType tool){
        for(Tool t : tools){
            if(t.getType().equals(tool)) return true;
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
            builder.append(item.getItemType().getDisPlayName()).append(": ").append(count);
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
    public void removeItem(ItemType item){
        for (int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item))
                items.remove(it);
        }
    }
    public void removeItem(ItemType item,int count){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item))
                items.put(it,items.get(it) -count);
        }
    }
    public int getCountOfItem(ItemType item){
        for(int i=0;i<items.size();i++){
            Item it=(Item) items.keySet().toArray()[i];
            if(it.getItemType().equals(item)) return items.get(it);
        }
        return 0;
    }

    public Tool getToolByType(ToolType type){
        for (Tool tool : tools) {
            if(tool.getType().equals(type)){
                return tool;
            }
        }
        return null;
    }

    public void addTool(ToolType tool){
        tools.add(new Tool(tool));
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
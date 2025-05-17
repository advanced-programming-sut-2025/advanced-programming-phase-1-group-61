package models.character;

import models.Item;
import models.enums.BackpackType;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ToolType;
import models.tool.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final HashMap<ItemType, Integer> items=new HashMap<>();
    private final ArrayList<Tool> tools=new ArrayList<>();
    private final HashMap<CageType,Integer> allCages=new HashMap<>();
    private BackpackType backpackType=BackpackType.PRIMARY;
    private Trashcan trashcan=new Trashcan();

    public Inventory() {
        tools.add(new Axe());
        tools.add(new Pickaxe());
        tools.add(new Hoe());
    }

    public void addItem(ItemType item, int count){

       if(items.containsKey(item)){
           int i = items.get(item);
           items.put(item , i+count);
       }else {
           if(backpackType.getSize() > items.size()){
               items.put(item , count);
           }
       }
    }
    public void addCage(CageType cage, int count){
        if(allCages.containsKey(cage)){
            int i = allCages.get(cage);
            allCages.put(cage , i+count);
        }else {
            if(backpackType.getSize() > allCages.size()){
                allCages.put(cage , count);
            }
        }
    }
    public int getCageTypeNumber(CageType cageType){
        if(allCages.containsKey(cageType)){
            int count = allCages.get(cageType);
            return count;
        }
        return 0;
    }
    public void removeCage(int amount,CageType cageType){
        allCages.replace(cageType , getCageTypeNumber(cageType) -amount);
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
            ItemType itemType=(ItemType) items.keySet().toArray()[i];
            int count=items.get(itemType);
            Item item = new Item(itemType);
            builder.append(item.getItemType().getDisPlayName()).append(": ").append(count);
            if(i!=items.size()-1) builder.append("\n");
        }
        return builder.toString();
    }
    public HashMap<ItemType,Integer> getItems(){
        return items;
    }
    public ArrayList<Tool> getTools(){
        return tools;
    }
    public void removeItem(ItemType item) {
        items.remove(item);
    }
    public void removeItem(ItemType item, int count) {
        if (items.containsKey(item)) {
            int current = items.get(item);
            int newCount = current - count;
            if (newCount > 0) {
                items.put(item, newCount);
            } else {
                items.remove(item);
            }
        }
    }
    public int getCountOfItem(ItemType item){
        for(int i=0;i<items.size();i++){
            ItemType it=(ItemType) items.keySet().toArray()[i];
            if(it.equals(item)) return items.get(it);
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
        switch (tool){
            case FishingPole -> tools.add(new FishingPole());
            case Axe -> tools.add(new Axe());
            case Hoe -> tools.add(new Hoe());
            case MilkPail ->  tools.add(new MilkPail());
            case PickAxe -> tools.add(new Pickaxe());
            case Scythe -> tools.add(new Scythe());
            case Shear -> tools.add(new Shear());
            case WateringCan -> tools.add(new WateringCan());
        }
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
    public void upgradeFishingPole(String upgradeName){
        for(Tool t : tools){
            if(t.getType().equals(ToolType.FishingPole)){
                t.setLevel(upgradeName);
                return;
            }
        }
    }
}
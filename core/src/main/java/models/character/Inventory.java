package models.character;

import models.Item;
import models.enums.BackpackType;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ToolType;
import models.tool.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventory {

    private BackpackType  backpackType = BackpackType.PRIMARY;
    private List<InventorySlot> slots = new ArrayList<>();



    private final ArrayList<Tool> tools=new ArrayList<>();
    private final HashMap<CageType,Integer> allCages= new HashMap<>();

    private Trashcan trashcan=new Trashcan();

    public Inventory() {
        for (int i = 0; i < backpackType.getSize(); i++) {
            slots.add(new InventorySlot(0 , null));
        }
        tools.add(new Axe());
        tools.add(new Pickaxe());
        tools.add(new Hoe());
    }

    public List<InventorySlot> getSlots() {
        return slots;
    }

    public void addItem(ItemType item, int count){
        int emptySlot =0;
        for (InventorySlot slot : slots) {
            if(item.equals(slot.getItemType())){
                slot.setCount(slot.getCount()+count);
            }
            if(slot.isEmpty()){
                emptySlot++;
            }
        }
        if(emptySlot < backpackType.getSize()){
            for (InventorySlot slot : slots) {
                if(slot.isEmpty()){
                    slot.setItemType(item);
                    slot.setCount(count);
                    break;
                }
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

    public InventorySlot getSlotByItem(ItemType type){
        for (InventorySlot slot : slots) {
            if(type.equals(slot.getItemType())){
                return slot;
            }
        }
        return null;
    }


    public ArrayList<Tool> getTools(){
        return tools;
    }

    public int getCountOfItem(ItemType item){
        InventorySlot slot = getSlotByItem(item);
        if(slot != null){
            return slot.getCount();
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
    public void removeItem(ItemType type){
        return;
    }
    public void removeItem(ItemType type , int count){
        return;
    }
}

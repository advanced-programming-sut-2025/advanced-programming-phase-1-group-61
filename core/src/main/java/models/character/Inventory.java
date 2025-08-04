package models.character;

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


    private final HashMap<CageType,Integer> allCages= new HashMap<>();

    private Trashcan trashcan=new Trashcan();

    public Inventory() {
        for (int i = 0; i < backpackType.getSize(); i++) {
            slots.add(new InventorySlot(0 , null , i));
        }
        slots.get(0).setObjectInSlot(new Axe() , 1);
        slots.get(1).setObjectInSlot(new Pickaxe() , 1);
    }

    public List<InventorySlot> getSlots() {
        return slots;
    }

    public void addItem(ItemType item, int count,int index){
        for (InventorySlot slot : slots) {
            if(slot.getIndex() == index){
                if(slot.getObjectInSlot() == null){
                    slot.setObjectInSlot(item , count);
                }
            }
        }
    }
    public void addItem(ItemType item, int count){
        for (InventorySlot slot : slots) {
            if(slot.getObjectInSlot() == null){
                slot.setObjectInSlot(item , count);
            }
        }
    }


    public boolean checkToolInInventory(ToolType tool){
        for (InventorySlot slot : slots) {
            if(slot.getObjectInSlot() instanceof Tool){
                ToolType tool1 = (ToolType) slot.getObjectInSlot();
                if(tool1 .equals(tool)){
                    return true;
                }
            }
        }
        return false;
    }


    public InventorySlot getSlotByItem(ItemType type){
        for (InventorySlot slot : slots) {
            if(slot.getObjectInSlot() instanceof ItemType){
                ItemType type1 = (ItemType) slot.getObjectInSlot();
                if(type.equals(type1)){
                    return slot;
                }
            }
        }
        return null;
    }



    public int getCountOfItem(ItemType item){
        InventorySlot slot = getSlotByItem(item);
        if(slot != null){
            return slot.getCount();
        }
        return 0;
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
        for (InventorySlot slot : slots) {
            if(slot.getObjectInSlot() != null){
                if(slot.getObjectInSlot() instanceof Tool){
                    if(((Tool) slot.getObjectInSlot()).getType().equals(ToolType.FishingPole)){
                        ((Tool) slot.getObjectInSlot()).setLevel(upgradeName);
                    }
                }
            }
        }
    }

    public void removeItem(ItemType type , int count){
       //TODO
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

    public void addTool(ToolType tool) {
        for (InventorySlot slot : slots) {
            if(slot.getObjectInSlot() == null){
                slot.setObjectInSlot( tool , 1);
            }
        }
    }
}

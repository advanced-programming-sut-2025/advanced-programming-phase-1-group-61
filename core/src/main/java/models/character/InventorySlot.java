package models.character;


import models.enums.ItemType;
import models.tool.Tool;

public class InventorySlot {
    private int count;
    private Object object;
    private int index;
    private ItemType itemType;
    private Tool tool;


    public InventorySlot(int count , Object object,int index) {
        this.count = count;
        this.object = object;
        this.index = index;
        if(object instanceof ItemType){
            this.itemType = (ItemType) object;
            tool = null;
        } else if (object instanceof Tool) {
            itemType = null;
            tool = (Tool) object;
        }
    }



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void clear() {
        object = null;
        count = 0;
    }

    public boolean isEmpty() {
        return object == null || count == 0;
    }

    public Object getObjectInSlot() {
        return object;
    }

    public void setObjectInSlot(Object object , int count) {
        this.object = object;
        if(object instanceof Tool){
            this.tool = (Tool) object;
            itemType = null;
        }else if(object instanceof ItemType) {
            this.itemType = (ItemType) object;
            tool = null;
        }
    }


    public int getIndex() {
        return index;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Tool getTool() {
        return tool;
    }


}

package models.character;


import models.enums.ItemType;

public class InventorySlot {
    private int count;
    private ItemType itemType;


    public InventorySlot(int count , ItemType itemType) {
        this.count = count;
        this.itemType = itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void clear() {
        itemType = null;
        count = 0;
    }

    public boolean isEmpty() {
        return itemType == null || count == 0;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}

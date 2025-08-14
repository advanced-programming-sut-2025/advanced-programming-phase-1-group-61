package models.NPC;

import models.enums.ItemType;

public class ItemWithCount {
    private ItemType item;
    private int count;
    public ItemWithCount(ItemType item, int count) {
        this.item = item;
        this.count = count;
    }
    public ItemType getItem() {
        return item;
    }
    public void setItem(ItemType item) {
        this.item = item;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}

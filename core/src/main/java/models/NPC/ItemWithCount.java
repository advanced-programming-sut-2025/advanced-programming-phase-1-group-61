package models.NPC;

import models.enums.ItemType;

public class ItemWithCount {
    private ItemType item;
    private int count;
    private boolean done=false;
    private String doneBy="";
    private String takenBy="";
    private boolean isTaken=false;
    public ItemWithCount(ItemType item, int count) {
        this.item = item;
        this.count = count;
    }

    public ItemWithCount() {
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
    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }
    public String getDoneBy() {
        return doneBy;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public boolean isDone() {
        return done;
    }
    public boolean isTaken() {
        return isTaken;
    }
    public void setTaken(boolean taken) {
        isTaken = taken;
    }
    public String getTakenBy() {
        return takenBy;
    }
    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }
}

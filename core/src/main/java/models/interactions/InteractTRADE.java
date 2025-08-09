package models.interactions;

import models.enums.ItemType;

public class InteractTRADE extends Interact{

    private ItemType Item1;
    private int amount1;
    private ItemType Item2;
    private int amount2;
    private String kind;
    private boolean handeled = false;
    public InteractTRADE(String type,String kind,Integer owner, Integer friend, ItemType item1 ,Integer amount1,
                         ItemType item2 ,Integer amount2, String value,int id) {
        super(type, friend,owner, value,id);
        this.amount1 = amount1;
        this.Item1 = item1;
        this.Item2 = item2;
        this.amount2 = amount2;
        this.kind = kind;
    }

    public InteractTRADE() {
    }

    public ItemType getItem1() {
        return Item1;
    }

    public int getAmount1() {
        return amount1;
    }

    public ItemType getItem2() {
        return Item2;
    }

    public int getAmount2() {
        return amount2;
    }

    public String getKind() {
        return kind;
    }

    public boolean isHandeled() {
        return handeled;
    }

    public void setHandeled(boolean handeled) {
        this.handeled = handeled;
    }
}

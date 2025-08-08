package models.interactions;

import models.enums.ItemType;

public class InteractMARRIGE extends Interact {
    ItemType Item;
    public InteractMARRIGE(String type,Integer owner, Integer friend, ItemType item , String value,int id) {
        super(type, friend,owner, value,id);
        this.Item = item;
    }

    public InteractMARRIGE() {
    }

    public ItemType getItem() {
        return Item;
    }
}

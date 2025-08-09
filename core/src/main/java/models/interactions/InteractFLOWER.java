package models.interactions;

import models.enums.ItemType;

public class InteractFLOWER extends Interact {
    ItemType flower;
    public InteractFLOWER(String type, Integer owner, Integer friend, ItemType flower, String value,int id) {
        super(type,owner, friend, value,id);
        this.flower = flower;
    }

    public InteractFLOWER() {
    }
}

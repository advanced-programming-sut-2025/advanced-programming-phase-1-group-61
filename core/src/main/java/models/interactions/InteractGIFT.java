package models.interactions;

import models.App;
import models.character.Character;
import models.enums.ItemType;

public class InteractGIFT extends Interact {
    ItemType Item;
    int amount;
    public InteractGIFT(String type,Integer owner, Integer friend, ItemType item ,Integer amount, String value,int id) {
        super(type, friend,owner, value,id);
        this.amount = amount;
        this.Item = item;
    }

}

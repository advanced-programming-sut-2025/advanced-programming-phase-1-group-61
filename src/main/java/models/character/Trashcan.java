package models.character;

import models.App;
import models.Item;
import models.enums.TrashcanType;

import java.util.*;

public class Trashcan {
    private TrashcanType type=TrashcanType.PRIMARY;
    private final HashMap<Item, Integer> allItems=new HashMap<>();
    public TrashcanType getType() {
        return type;
    }
    public void setType(TrashcanType type) {
        this.type = type;
    }
    public void removeItem(Item item,int count) {
        if(allItems.get(item)==null) return;
        if(allItems.get(item)<count) {
            allItems.remove(item);
        }
        else allItems.put(item, allItems.get(item)-count);
        Character character=App.getCurrentGame().getCurrentCharacter();
        character.setMoney(character.getMoney()+type.getReturnPercentage()*count*item.getPrice()/100);
    }
}

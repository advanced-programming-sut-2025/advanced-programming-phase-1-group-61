package models.character;

import io.github.camera.Main;
import models.App;
import models.Item;
import models.enums.ItemType;
import models.enums.TrashcanType;

import java.util.*;

public class Trashcan {
    private TrashcanType type=TrashcanType.PRIMARY;
    public TrashcanType getType() {
        return type;
    }
    public void setType(TrashcanType type) {
        this.type = type;
    }
    public void removeItem(ItemType item, int count ) {
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
        int numOfRemovedItem = character.getInventory().removeItem(item , count);
        character.setMoney((int) (character.getMoney() + (numOfRemovedItem * item.getPrice())*type.getReturnPercentage()));
    }
}

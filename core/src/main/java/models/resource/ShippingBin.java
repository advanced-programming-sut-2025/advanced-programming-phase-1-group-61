package models.resource;

import io.github.camera.Main;
import models.App;
import models.character.Character;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ShippingBin extends Resource{
    private List<ItemType> itemTypes ;

    public ShippingBin() {
        this.itemTypes = new ArrayList<>();
    }

    public List<ItemType> getItemTypes() {
        return itemTypes;
    }
    public void removeItemType(ItemType itemType){
        Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
        character.setMoney(character.getMoney() + itemType.getPrice());
        itemTypes.remove(itemType);
    }
    public void changeDayActivity(){
        for (ItemType type : itemTypes) {
            removeItemType(type);
        }
    }
    public void addItemType(ItemType itemType){
        itemTypes.add(itemType);
    }

}

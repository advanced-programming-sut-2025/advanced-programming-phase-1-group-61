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

        Main.getApp().getCurrentGame().getCurrentCharacter().setMoney(
            Main.getApp().getCurrentGame().getCurrentCharacter().getMoney() + itemType.getPrice());
        itemTypes.remove(itemType);
    }
    public void changeDayActivity(){
        List<ItemType> itemsToRemove = new ArrayList<>();
        itemsToRemove.addAll(itemTypes);
        for (ItemType type : itemsToRemove) {
            removeItemType(type);
        }
    }
    public void addItemType(ItemType itemType){
        itemTypes.add(itemType);
    }

}

package models.resource;

import models.App;
import models.character.Character;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ShippingBin extends Resource{
    private List<ItemType> itemTypes ;
    private int owner;

    public int getOwner() {
        return owner;
    }

    public ShippingBin(int owner) {
        this.itemTypes = new ArrayList<>();
        this.owner = owner;
    }

    public List<ItemType> getItemTypes() {
        return itemTypes;
    }
    public void removeItemType(ItemType itemType){
        Character character =App.getCurrentGame().getCharacterByTurnNumber(owner);
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

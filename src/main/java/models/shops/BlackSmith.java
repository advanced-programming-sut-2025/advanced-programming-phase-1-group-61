package models.shops;

import models.building.Shop;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackSmith extends Shop {
    private final ArrayList<ShopItem> items;
    private final HashMap<String,Integer> upgrades;

    public BlackSmith(String type, String name, int X, int Y) {
        super(type, name, X, Y);
        this.owner="Clint";
        items=new ArrayList<>(List.of(
                new ShopItem(ItemType.CopperOre,Integer.MAX_VALUE,75,"A common ore that can be smelted into bars."),
                new ShopItem(ItemType.IronOre,Integer.MAX_VALUE,150,"A fairly common ore that can be smelted into bars."),
                new ShopItem(ItemType.Coal,Integer.MAX_VALUE,150,"A combustible rock that is useful for crafting and smelting."),
                new ShopItem(ItemType.GoldOre,Integer.MAX_VALUE,400,"A precious ore that can be smelted into bars.")
        ));
        upgrades=new HashMap<>(Map.of(
                "copper",5,
                "steel",5,
                "gold",5,
                "iridium",5
        ));
    }

    @Override
    public String showAllProducts() {
        StringBuilder builder=new StringBuilder();
        for(ShopItem item:items){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        for(Map.Entry<String,Integer> entry:upgrades.entrySet()){
            String key=getFirstCapitalLetter(entry.getKey())+" Tool";
            builder.append("Name: ")
                    .append(key)
                    .append(" | Price: ")
                    .append(entry.getValue())
                    .append("\n");
        }
        for(int i=0;i<upgrades.size();i++){
            Map.Entry<String,Integer> entry=upgrades.entrySet().iterator().next();
            String key=getFirstCapitalLetter(entry.getKey())+" Trash Can";
            builder.append("Name: ")
                    .append(key)
                    .append(" | Price: ")
                    .append(entry.getValue());
            if(i!=upgrades.size()-1) builder.append("\n");
        }
        return builder.toString();
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }
    public HashMap<String,Integer> getUpgrades() {
        return upgrades;
    }
    private String getFirstCapitalLetter(String s){
        char[] chars=s.toCharArray();
        chars[0]=Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}

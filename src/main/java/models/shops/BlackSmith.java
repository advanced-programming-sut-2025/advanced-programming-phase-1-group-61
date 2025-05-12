package models.shops;

import models.building.Shop;
import models.enums.ItemType;
import models.enums.TrashcanType;

import java.util.ArrayList;
import java.util.List;

public class BlackSmith extends Shop {
    private final ArrayList<ShopItem> items;
    private final ArrayList<ShopToolUpgrades> toolUpgrades;
    private final ArrayList<ShopTrashcanUpgrades> trashcanUpgrades;

    public BlackSmith(String type, String name, int X, int Y) {
        super(type, name, X, Y);
        this.owner="Clint";
        items=new ArrayList<>(List.of(
                new ShopItem(ItemType.CopperOre,Integer.MAX_VALUE,75,"A common ore that can be smelted into bars."),
                new ShopItem(ItemType.IronOre,Integer.MAX_VALUE,150,"A fairly common ore that can be smelted into bars."),
                new ShopItem(ItemType.Coal,Integer.MAX_VALUE,150,"A combustible rock that is useful for crafting and smelting."),
                new ShopItem(ItemType.GoldOre,Integer.MAX_VALUE,400,"A precious ore that can be smelted into bars.")
        ));
        toolUpgrades =new ArrayList<>(List.of(
                new ShopToolUpgrades("copper",1,2000,ItemType.CopperBar,5),
                new ShopToolUpgrades("iron",1,5000,ItemType.IronBar,5),
                new ShopToolUpgrades("gold",1,10000,ItemType.GoldBar,5),
                new ShopToolUpgrades("iridium",1,25000,ItemType.IridiumBar,5)
        ));
        trashcanUpgrades=new ArrayList<>(List.of(
                new ShopTrashcanUpgrades(TrashcanType.COPPER,1,1000,ItemType.CopperBar,5),
                new ShopTrashcanUpgrades(TrashcanType.IRON,1,2500,ItemType.IridiumBar,5),
                new ShopTrashcanUpgrades(TrashcanType.GOLDEN,1,5000,ItemType.GoldBar,5),
                new ShopTrashcanUpgrades(TrashcanType.IRIDIUM,1,12500,ItemType.IronBar,5)
        ));

    }

    @Override
    public String showAllProducts() {
        StringBuilder builder=new StringBuilder();
        builder.append("items:").append("\n");
        for(ShopItem item:items){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        for(ShopToolUpgrades tool:toolUpgrades){
            builder.append("Name: ")
                    .append(tool.getUpgradeName())
                    .append(" tool")
                    .append(" | Price: ")
                    .append(tool.getPrice())
                    .append("\n");
        }
        for(int i=0;i<trashcanUpgrades.size();i++){
            ShopTrashcanUpgrades trashcan=trashcanUpgrades.get(i);
            builder.append("Name: ")
                    .append(trashcan.getTrashcanType().getDisplayName())
                    .append(" Trashcan")
                    .append(" | Price: ")
                    .append(trashcan.getPrice());
            if(i!=trashcanUpgrades.size()-1) builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder=new StringBuilder();
        for(ShopItem item:items){
            if(item.getStock()>0){
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append(" | Stock: ")
                        .append(item.getStock())
                        .append("\n");
            }
        }
        for(ShopToolUpgrades tool:toolUpgrades){
            if(tool.getStock()>0){
                builder.append("Name: ")
                        .append(tool.getUpgradeName())
                        .append(" tool")
                        .append(" | Price: ")
                        .append(tool.getPrice())
                        .append(" | Stock: ")
                        .append(tool.getStock())
                        .append("\n");
            }
        }
        for(int i=0;i<trashcanUpgrades.size();i++){
            ShopTrashcanUpgrades trashcan=trashcanUpgrades.get(i);
            if(trashcan.getStock()>0){
                builder.append("Name: ")
                        .append(trashcan.getTrashcanType().getDisplayName())
                        .append(" Trashcan")
                        .append(" | Price: ")
                        .append(trashcan.getPrice())
                        .append(" | Stock: ")
                        .append(trashcan.getStock());
                if(i!=trashcanUpgrades.size()-1) builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product,int count) {
        for(ShopItem item:items){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopToolUpgrades tool:toolUpgrades){
            if(tool.getUpgradeName().equals(product)){
                if(count> tool.getStock()) return "not enough stock!";
                tool.setStock(tool.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopTrashcanUpgrades trashcan:trashcanUpgrades){
            if(trashcan.getTrashcanType().getDisplayName().equals(product)){
                if(count> trashcan.getStock()) return "not enough stock!";
                trashcan.setStock(trashcan.getStock()-count);
                return "Successfully purchased!";
            }
        }
        return "Please enter a valid product!";
    }

    @Override
    public void restoreStocks() {
        for(ShopItem item:items) item.restoreStock();
        for(ShopToolUpgrades tool:toolUpgrades) tool.restoreStock();
        for(ShopTrashcanUpgrades trashcan:trashcanUpgrades) trashcan.restoreStock();
    }

    public ArrayList<ShopItem> getItems() {
        return items;
    }
}

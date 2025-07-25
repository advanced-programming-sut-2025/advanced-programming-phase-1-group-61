package models.shops;

import models.App;
import models.building.Shop;
import models.character.Character;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ShopType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Carpenter extends Shop {
    private final ArrayList<ShopItem> permanentItems;
    private final ArrayList<ShopCages> farmBuildings;
    public Carpenter(String name, int X, int Y) {
        super(name, X, Y,ShopType.Carpenter);
        this.owner="Robin";
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Wood,Integer.MAX_VALUE,10,"A sturdy, yet flexible plant material with a wide variety of uses."),
                new ShopItem(ItemType.Stone,Integer.MAX_VALUE,20,"A common material with many uses in crafting and building.")
        ));
        farmBuildings = new ArrayList<>(List.of(
                new ShopCages(CageType.Barn,1,6000, Map.of(ItemType.Wood,350,ItemType.Stone,150)),
                new ShopCages(CageType.BigBarn,1,12000, Map.of(ItemType.Wood,450,ItemType.Stone,200)),
                new ShopCages(CageType.DeluxeBarn,1,25000, Map.of(ItemType.Wood,550,ItemType.Stone,300)),
                new ShopCages(CageType.Coop,1,4000, Map.of(ItemType.Wood,300,ItemType.Stone,100)),
                new ShopCages(CageType.BigCoop,1,10000, Map.of(ItemType.Wood,4000,ItemType.Stone,150)),
                new ShopCages(CageType.DeluxCoop,1,20000, Map.of(ItemType.Wood,500,ItemType.Stone,200)),
                new ShopCages(CageType.Well,1,1000, Map.of(ItemType.Stone,75)),
                new ShopCages(CageType.ShippingBin,1,250,Map.of(ItemType.Wood,150))
        ));
    }

    @Override
    public String showAllProducts() {
        StringBuilder builder = new StringBuilder();
        for (ShopItem item : permanentItems) {
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        for(int i=0;i<farmBuildings.size();i++) {
            ShopCages cage = farmBuildings.get(i);
            builder.append("Name: ")
                    .append(cage.getCageType().getDisplayName())
                    .append(" | Price: ").append(cage.getPrice());
            if(i!=farmBuildings.size()-1) builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder = new StringBuilder();
        for (ShopItem item : permanentItems) {
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append(" | Stock: ")
                        .append(item.getStock())
                        .append("\n");
            }
        }
        for(int i=0;i<farmBuildings.size();i++) {
            ShopCages cage = farmBuildings.get(i);
            if(cage.getStock()>0) {
                builder.append("Name: ")
                        .append(cage.getCageType().getDisplayName())
                        .append(" | Price: ")
                        .append(cage.getPrice())
                        .append(" | Stock: ")
                        .append(cage.getStock());
                if (i != farmBuildings.size() - 1) builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        Character character=App.getCurrentGame().getCurrentCharacter();
        for(ShopItem item : permanentItems) {
            if(item.getItem().getDisPlayName().equals(product)) {
                if(count>item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                character.getInventory().addItem(item.getItem(),count);
                return "successfully purchased!";
            }
        }
        for(ShopCages cage : farmBuildings) {
            if(cage.getCageType().getDisplayName().equals(product)) {
                if(count>cage.getStock()) return "not enough stock!";
                character.getInventory().addCage(cage.getCageType(),count);
                cage.setStock(cage.getStock()-count);
                return "successfully purchased!";
            }
        }
        return "please enter a valid product!";
    }

    @Override
    public void restoreStocks() {
        for(ShopCages cage : farmBuildings) cage.restoreStock();
        for(ShopItem item : permanentItems) item.restoreStock();
    }
    public int getOpenHour(){
        return 9;
    }
    public int getCloseHour(){
        return 19;
    }
    public ArrayList<ShopItem> getPermanentItems(){
        return permanentItems;
    }
    public ArrayList<ShopCages> getFarmBuildings() {
        return farmBuildings;
    }
}

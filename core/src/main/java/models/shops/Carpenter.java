package models.shops;

import io.github.camera.Main;
import models.App;
import models.building.Shop;
import models.character.Character;
import models.enums.CageType;
import models.enums.ItemType;
import models.enums.ShopType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carpenter extends Shop {
    private ArrayList<ShopItem> permanentItems;
    private ArrayList<ShopCages> farmBuildings;

    public Carpenter() {
    }

    public Carpenter(String name, int X, int Y) {
        super(name, X, Y,ShopType.Carpenter);
        this.owner="Robin";
        permanentItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.Wood,Integer.MAX_VALUE,10,"A sturdy, yet flexible plant material with a wide variety of uses."),
                new ShopItem(ItemType.Stone,Integer.MAX_VALUE,20,"A common material with many uses in crafting and building.")
        ));
        this.farmBuildings = new ArrayList<>();

        {
            HashMap<ItemType, Integer> barnMap = new HashMap<>();
            barnMap.put(ItemType.Wood, 350);
            barnMap.put(ItemType.Stone, 150);
            this.farmBuildings.add(new ShopCages(CageType.Barn, 1, 6000, barnMap));

            HashMap<ItemType, Integer> bigBarnMap = new HashMap<>();
            bigBarnMap.put(ItemType.Wood, 450);
            bigBarnMap.put(ItemType.Stone, 200);
            this.farmBuildings.add(new ShopCages(CageType.BigBarn, 1, 12000, bigBarnMap));

            HashMap<ItemType, Integer> deluxeBarnMap = new HashMap<>();
            deluxeBarnMap.put(ItemType.Wood, 550);
            deluxeBarnMap.put(ItemType.Stone, 300);
            this.farmBuildings.add(new ShopCages(CageType.DeluxeBarn, 1, 25000, deluxeBarnMap));

            HashMap<ItemType, Integer> coopMap = new HashMap<>();
            coopMap.put(ItemType.Wood, 300);
            coopMap.put(ItemType.Stone, 100);
            this.farmBuildings.add(new ShopCages(CageType.Coop, 1, 4000, coopMap));

            HashMap<ItemType, Integer> bigCoopMap = new HashMap<>();
            bigCoopMap.put(ItemType.Wood, 4000);
            bigCoopMap.put(ItemType.Stone, 150);
            this.farmBuildings.add(new ShopCages(CageType.BigCoop, 1, 10000, bigCoopMap));

            HashMap<ItemType, Integer> deluxeCoopMap = new HashMap<>();
            deluxeCoopMap.put(ItemType.Wood, 500);
            deluxeCoopMap.put(ItemType.Stone, 200);
            this.farmBuildings.add(new ShopCages(CageType.DeluxCoop, 1, 20000, deluxeCoopMap));

            HashMap<ItemType, Integer> wellMap = new HashMap<>();
            wellMap.put(ItemType.Stone, 75);
            this.farmBuildings.add(new ShopCages(CageType.Well, 1, 1000, wellMap));

            HashMap<ItemType, Integer> shippingBinMap = new HashMap<>();
            shippingBinMap.put(ItemType.Wood, 150);
            this.farmBuildings.add(new ShopCages(CageType.ShippingBin, 1, 250, shippingBinMap));
        }

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
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
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

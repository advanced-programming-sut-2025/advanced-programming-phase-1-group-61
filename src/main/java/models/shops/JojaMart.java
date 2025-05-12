package models.shops;

import models.building.Shop;
import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class JojaMart extends Shop {
    private final ArrayList<ShopItem> permanentShopItems;
    private final ArrayList<ShopItem> springShopItems;
    private final ArrayList<ShopItem> summerShopItems;
    private final ArrayList<ShopItem> fallShopItems;
    private final ArrayList<ShopItem> winterShopItems;

    public JojaMart(String type, String name, int X, int Y) {
        super(type, name, X, Y);
        owner="Morris";
        permanentShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.JojaCola,Integer.MAX_VALUE,75,"The flagship product of Joja corporation."),
                new ShopItem(ItemType.AncientSeed,1,500,"Could these still grow?"),
                new ShopItem(ItemType.GrassStarter,Integer.MAX_VALUE,125,"Place this on your farm to start a new patch of grass."),
                new ShopItem(ItemType.Sugar,Integer.MAX_VALUE,125,"Adds sweetness to pastries and candies. Too much can be unhealthy."),
                new ShopItem(ItemType.WheatFlour,Integer.MAX_VALUE,125,"A common cooking ingredient made from crushed wheat seeds."),
                new ShopItem(ItemType.Rice, Integer.MAX_VALUE,250,"A basic grain often served under vegetables.")
        ));
        springShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.ParsnipSeed, 5, 25,"Plant these in the spring. Takes 4 days to mature."),
                new ShopItem(ItemType.BeanStarter, 5, 75,"Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.CauliflowerSeed, 5, 100,"Plant these in the spring. Takes 12 days to produce a large cauliflower."),
                new ShopItem(ItemType.PotatoSeed, 5, 62,"Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest."),
                new ShopItem(ItemType.StrawberrySeed, 5, 100,"Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that."),
                new ShopItem(ItemType.TulipBulb, 5, 25,"Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors."),
                new ShopItem(ItemType.KaleSeed, 5, 87,"Plant these in the spring. Takes 6 days to mature. Harvest with the scythe."),
                new ShopItem(ItemType.CoffeeBean, 1, 200,"Plant in summer or spring. Takes 10 days to grow, then produces coffee Beans every other day."),
                new ShopItem(ItemType.CarrotSeed, 10, 5,"Plant in the spring. Takes 3 days to grow."),
                new ShopItem(ItemType.RhubarbSeed, 5, 100,"Plant these in the spring. Takes 13 days to mature."),
                new ShopItem(ItemType.JazzSeed, 5, 37,"Plant in spring. Takes 7 days to produce a blue puffball flower.")
        ));
        summerShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.TomatoSeed, 5, 62,"Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.PepperSeed, 5, 50,"Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.WheatSeed, 10, 12,"Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe."),
                new ShopItem(ItemType.SummerSquashSeed, 10, 10,"Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest."),
                new ShopItem(ItemType.RadishSeed, 5, 50,"Plant these in the summer. Takes 6 days to mature."),
                new ShopItem(ItemType.MelonSeed, 5, 100,"Plant these in the summer. Takes 12 days to mature."),
                new ShopItem(ItemType.HopsStarter, 5, 75,"Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.PoppySeed, 5, 125,"Plant in summer. Produces a bright red flower in 7 days."),
                new ShopItem(ItemType.SpangleSeed, 5, 62,"Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors."),
                new ShopItem(ItemType.StarfruitSeed, 5, 400,"Plant these in the summer. Takes 13 days to mature."),
                new ShopItem(ItemType.CoffeeBean, 1, 200,"Plant in summer or spring. Takes 10 days to grow. Then produces coffee Beans every other day."),
                new ShopItem(ItemType.SunflowerSeed, 5, 125,"Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.")
        ));
        fallShopItems = new ArrayList<>(List.of(
                new ShopItem(ItemType.CornSeed, 5, 18,"Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.EggplantSeed, 5, 25,"Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.PumpkinSeed, 5, 125,"Plant these in the fall. Takes 13 days to mature."),
                new ShopItem(ItemType.BroccoliSeed, 5, 15,"Plant in the fall. Takes 8 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.AmaranthSeed, 5, 87,"Plant these in the fall. Takes 7 days to grow. Harvest with the scythe."),
                new ShopItem(ItemType.GrapeStarter, 5, 75,"Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis."),
                new ShopItem(ItemType.BeetSeed, 5, 20,"Plant these in the fall. Takes 6 days to mature."),
                new ShopItem(ItemType.YamSeed, 5, 75,"Plant these in the fall. Takes 10 days to mature."),
                new ShopItem(ItemType.BokChoySeed, 5, 62,"Plant these in the fall. Takes 4 days to mature."),
                new ShopItem(ItemType.CranberrySeed, 5, 300,"Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest."),
                new ShopItem(ItemType.SunflowerSeed, 5, 125,"Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest."),
                new ShopItem(ItemType.FairySeed, 5, 250,"Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors."),
                new ShopItem(ItemType.RareSeed, 1, 1000,"Sow in fall. Takes all season to grow."),
                new ShopItem(ItemType.WheatSeed, 5, 12,"Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.")
        ));
        winterShopItems=new ArrayList<>(List.of(
                new ShopItem(ItemType.PowderMelonSeed,10,20,"This special melon grows in the winter. Takes 7 days to grow.")
        ));
    }

    @Override
    public String showAllProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("permanent items:").append("\n");
        for(ShopItem item : permanentShopItems){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("spring items:").append("\n");
        for(ShopItem item : springShopItems){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("summer items:").append("\n");
        for(ShopItem item : summerShopItems){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("fall items:").append("\n");
        for(ShopItem item : fallShopItems){
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice())
                    .append("\n");
        }
        builder.append("winter items:").append("\n");
        for(int i=0; i<winterShopItems.size(); i++){
            ShopItem item = winterShopItems.get(i);
            builder.append("Name: ")
                    .append(item.getItem().getDisPlayName())
                    .append(" | Price: ")
                    .append(item.getPrice());
            if(i!=winterShopItems.size()-1) builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String showAllAvailableProducts() {
        StringBuilder builder = new StringBuilder();
        builder.append("permanent items:").append("\n");
        for(ShopItem item : permanentShopItems){
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("spring items:").append("\n");
        for(ShopItem item : springShopItems){
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("summer items:").append("\n");
        for(ShopItem item : summerShopItems){
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("fall items:").append("\n");
        for(ShopItem item : fallShopItems){
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice())
                        .append("\n");
            }
        }
        builder.append("\n");
        builder.append("winter items:").append("\n");
        for(int i=0; i<winterShopItems.size(); i++){
            ShopItem item = winterShopItems.get(i);
            if(item.getStock()>0) {
                builder.append("Name: ")
                        .append(item.getItem().getDisPlayName())
                        .append(" | Price: ")
                        .append(item.getPrice());
                if (i != winterShopItems.size() - 1) builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public String purchaseProduct(String product, int count) {
        for(ShopItem item : permanentShopItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopItem item : summerShopItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopItem item : springShopItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopItem item : fallShopItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        for(ShopItem item : winterShopItems){
            if(item.getItem().getDisPlayName().equals(product)){
                if(count> item.getStock()) return "not enough stock!";
                item.setStock(item.getStock()-count);
                return "Successfully purchased!";
            }
        }
        return "successfully purchased!";
    }

    @Override
    public void restoreStocks() {
        for(ShopItem item : permanentShopItems) item.restoreStock();
        for(ShopItem item : summerShopItems) item.restoreStock();
        for(ShopItem item : fallShopItems) item.restoreStock();
        for(ShopItem item : winterShopItems) item.restoreStock();
        for(ShopItem item : springShopItems) item.restoreStock();
    }

    public ArrayList<ShopItem> getPermanentShopItems() {
        return permanentShopItems;
    }
    public ArrayList<ShopItem> getFallShopItems() {
        return fallShopItems;
    }
    public ArrayList<ShopItem> getWinterShopItems() {
        return winterShopItems;
    }
    public ArrayList<ShopItem> getSummerShopItems() {
        return summerShopItems;
    }
    public ArrayList<ShopItem> getSpringShopItems(){
        return springShopItems;
    }
}
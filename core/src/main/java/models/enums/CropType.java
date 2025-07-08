package models.enums;

import models.App;
import models.Item;
import models.RandomNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CropType {

    BlueJazz(new int[]{1, 2, 2, 2}, -1, false, ItemType.BlueJazz, ItemType.JazzSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Carrot(new int[]{1, 1, 1}, -1, false, ItemType.Carrot, ItemType.CarrotSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Cauliflower(new int[]{1, 2, 4, 4, 1}, -1, true, ItemType.CauliFlower, ItemType.CauliflowerSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    CoffeeBean(new int[]{1, 2, 2, 3, 2}, 2, false, ItemType.CoffeeBean, ItemType.CoffeeBean,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Garlic(new int[]{1, 1, 1, 1}, -1, false, ItemType.Garlic, ItemType.GarlicSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    GreenBean(new int[]{1, 1, 1, 3, 4}, 3, false, ItemType.GreenBean, ItemType.BeanStarter,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Kale(new int[]{1, 2, 2, 1}, -1, false, ItemType.Kale, ItemType.KaleSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Parsnip(new int[]{1, 1, 1, 1}, -1, false, ItemType.Parsnip, ItemType.ParsnipSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Potato(new int[]{1, 1, 1, 2, 1}, -1, false, ItemType.Potato, ItemType.PotatoSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Rhubarb(new int[]{2, 2, 2, 3, 4}, -1, false, ItemType.Rhubarb, ItemType.RhubarbSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Strawberry(new int[]{1, 1, 2, 2, 2}, 4, false, ItemType.Strawberry, ItemType.StrawberrySeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Tulip(new int[]{1, 1, 2, 2}, -1, false, ItemType.Tulip, ItemType.TulipBulb,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    UnmilledRice(new int[]{1, 2, 2, 3}, -1, false, ItemType.UnmilledRice, ItemType.RiceShoot,Season.Spring,"Crops/Blue_Jazz_Stage_5.png"),
    Blueberry(new int[]{1, 3, 3, 4, 2}, 4, false, ItemType.Blueberry, ItemType.BlueberrySeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Corn(new int[]{2, 3, 3, 3, 3}, 4, false, ItemType.Corn, ItemType.CornSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Hops(new int[]{1, 1, 2, 3, 4}, 1, false, ItemType.Hops, ItemType.HopsStarter,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    HotPepper(new int[]{1, 1, 1, 1, 1}, 3, false, ItemType.HotPepper, ItemType.PepperSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Melon(new int[]{1, 2, 3, 3, 3}, -1, true, ItemType.Melon, ItemType.MelonSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Poppy(new int[]{1, 2, 2, 2}, -1, false, ItemType.Poppy, ItemType.PoppySeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Radish(new int[]{2, 1, 2, 1}, -1, false, ItemType.Radish, ItemType.RadishSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    RedCabbage(new int[]{2, 1, 2, 2, 2}, -1, false, ItemType.RedCabbage, ItemType.RedCabbageSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Starfruit(new int[]{2, 3, 2, 3, 3}, -1, false, ItemType.Starfruit, ItemType.StarfruitSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    SummerSpangle(new int[]{1, 2, 3, 1}, -1, false, ItemType.SummerSpangle, ItemType.SpangleSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Sunflower(new int[]{1, 2, 3, 2}, -1, false, ItemType.Sunflower, ItemType.SunflowerSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Tomato(new int[]{2, 2, 2, 2, 3}, 4, false, ItemType.Tomato, ItemType.TomatoSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Wheat(new int[]{1, 1, 1, 1}, -1, false, ItemType.Wheat, ItemType.WheatSeed,Season.Summer,"Crops/Blue_Jazz_Stage_5.png"),
    Amaranth(new int[]{1, 2, 2, 2}, -1, false, ItemType.Amaranth, ItemType.AmaranthSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    Artichoke(new int[]{2, 2, 1, 2, 1}, -1, false, ItemType.Artichoke, ItemType.ArtichokeSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    Beet(new int[]{1, 1, 2, 2}, -1, false, ItemType.Beet, ItemType.BeetSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    BokChoy(new int[]{1, 1, 1, 1}, -1, false, ItemType.BokChoy, ItemType.BokChoySeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    Broccoli(new int[]{2, 2, 2, 2}, 4, false, ItemType.Broccoli, ItemType.BroccoliSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    Cranberries(new int[]{1, 2, 1, 1, 2}, 5, false, ItemType.Cranberries, ItemType.CranberrySeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    SweetGemBerry(new int[]{2, 4, 6, 6, 6}, -1, false, ItemType.SweetGemBerry, ItemType.RareSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    Powdermelon(new int[]{1, 2, 1, 2, 1}, -1, true, ItemType.Powdermelon, ItemType.PowdermelonSeed,Season.Fall,"Crops/Blue_Jazz_Stage_5.png"),
    AncientFruit(new int[]{2, 7, 7, 7, 5}, 7, false, ItemType.AncientFruit, ItemType.AncientSeed,Season.Spring,"Crops/Blue_Jazz_Stage_5.png");



    private int[] stages ;
    private int reGrowthTime ;
    private boolean canBecomeGiant;
    private ItemType product , source;
    private Season season;
    private String resourceTexturePath;

    CropType( int[] stages, int reGrowthTime, boolean canBecomeGiant,ItemType product,ItemType source,Season season,String resourceTexturePath) {
        this.stages = stages;
        this.reGrowthTime = reGrowthTime;
        this.canBecomeGiant = canBecomeGiant;
        this.source = source;
        this.product = product;
        this.resourceTexturePath = resourceTexturePath;
    }

    public int[] getStages() {
        return stages;
    }

    public int getStageByCropAge(int age){
        int stage = 0;
        int dayCounter =0;
        for (int i = 0; i < this.stages.length; i++) {
            dayCounter += stages[i];
            if(dayCounter < age){
                stage++;
            }else {
                return stage;
            }
        }
        return 1;
    }
    public int getTotalHarvestTime(){
        int counter =0;
        for (int stage : stages) {
            counter += stage;
        }
        return counter;
    }

    public boolean CanBecomeGiant() {
        return canBecomeGiant;
    }

    public ItemType getProduct() {
        return product;
    }

    public ItemType getSource() {
        return source;
    }

    public int getReGrowthTime() {
        return reGrowthTime;
    }

    public Season getSeason() {
        return season;
    }

    public boolean canBecomeGiant() {
        return canBecomeGiant;
    }

    public static CropType getCropType(String type) {
        try {
            return CropType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static CropType getCropTypeBySource(ItemType source) {
        if (source.equals(ItemType.MixedSeed)) {
            Season season = App.getCurrentGame().getDate().getSeason();
            List<CropType> possibleCrops = new ArrayList<>();

            switch (season) {
                case Spring -> Collections.addAll(possibleCrops, CropType.Cauliflower, CropType.Parsnip, CropType.Potato, CropType.BlueJazz, CropType.Tulip);
                case Summer -> Collections.addAll(possibleCrops, CropType.Corn, CropType.HotPepper, CropType.Radish, CropType.Wheat, CropType.Poppy, CropType.Sunflower, CropType.SummerSpangle);
                case Fall -> Collections.addAll(possibleCrops, CropType.Artichoke, CropType.Corn, CropType.Sunflower);
                case Winter -> Collections.addAll(possibleCrops, CropType.Powdermelon);
            }

            if (!possibleCrops.isEmpty()) {
                int random = RandomNumber.getRandomNumberWithBoundaries(0 ,possibleCrops.size());
                return possibleCrops.get(random);
            }
        }

        for (CropType cropType : CropType.values()) {
            if (cropType.getSource().equals(source)) {
                return cropType;
            }
        }

        return null;
    }

    public String getResourceTexturePath() {
        return resourceTexturePath;
    }
}

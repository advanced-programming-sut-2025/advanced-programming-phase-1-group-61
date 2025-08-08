package models.enums;

import models.App;
import models.Item;
import models.RandomNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CropType {

    BlueJazz(new int[]{1, 2, 2, 2}, -1, false, ItemType.BlueJazz, ItemType.JazzSeed,Season.Spring),
    Carrot(new int[]{1, 1, 1}, -1, false, ItemType.Carrot, ItemType.CarrotSeed,Season.Spring),
    Cauliflower(new int[]{1, 2, 4, 4, 1}, -1, true, ItemType.CauliFlower, ItemType.CauliflowerSeed,Season.Spring),
    CoffeeBean(new int[]{1, 2, 2, 3, 2}, 2, false, ItemType.CoffeeBean, ItemType.CoffeeBean,Season.Spring),
    Garlic(new int[]{1, 1, 1, 1}, -1, false, ItemType.Garlic, ItemType.GarlicSeed,Season.Spring),
    GreenBean(new int[]{1, 1, 1, 3, 4}, 3, false, ItemType.GreenBean, ItemType.BeanStarter,Season.Spring),
    Kale(new int[]{1, 2, 2, 1}, -1, false, ItemType.Kale, ItemType.KaleSeed,Season.Spring),
    Parsnip(new int[]{1, 1, 1, 1}, -1, false, ItemType.Parsnip, ItemType.ParsnipSeed,Season.Spring),
    Potato(new int[]{1, 1, 1, 2, 1}, -1, false, ItemType.Potato, ItemType.PotatoSeed,Season.Spring),
    Rhubarb(new int[]{2, 2, 2, 3, 4}, -1, false, ItemType.Rhubarb, ItemType.RhubarbSeed,Season.Spring),
    Strawberry(new int[]{1, 1, 2, 2, 2}, 4, false, ItemType.Strawberry, ItemType.StrawberrySeed,Season.Spring),
    Tulip(new int[]{1, 1, 2, 2}, -1, false, ItemType.Tulip, ItemType.TulipBulb,Season.Spring),
    UnmilledRice(new int[]{1, 2, 2, 3}, -1, false, ItemType.UnmilledRice, ItemType.RiceShoot,Season.Spring),
    Blueberry(new int[]{1, 3, 3, 4, 2}, 4, false, ItemType.Blueberry, ItemType.BlueberrySeed,Season.Summer),
    Corn(new int[]{2, 3, 3, 3, 3}, 4, false, ItemType.Corn, ItemType.CornSeed,Season.Summer),
    Hops(new int[]{1, 1, 2, 3, 4}, 1, false, ItemType.Hops, ItemType.HopsStarter,Season.Summer),
    HotPepper(new int[]{1, 1, 1, 1, 1}, 3, false, ItemType.HotPepper, ItemType.PepperSeed,Season.Summer),
    Melon(new int[]{1, 2, 3, 3, 3}, -1, true, ItemType.Melon, ItemType.MelonSeed,Season.Summer),
    Poppy(new int[]{1, 2, 2, 2}, -1, false, ItemType.Poppy, ItemType.PoppySeed,Season.Summer),
    Radish(new int[]{2, 1, 2, 1}, -1, false, ItemType.Radish, ItemType.RadishSeed,Season.Summer),
    RedCabbage(new int[]{2, 1, 2, 2, 2}, -1, false, ItemType.RedCabbage, ItemType.RedCabbageSeed,Season.Summer),
    Starfruit(new int[]{2, 3, 2, 3, 3}, -1, false, ItemType.Starfruit, ItemType.StarfruitSeed,Season.Summer),
    SummerSpangle(new int[]{1, 2, 3, 1}, -1, false, ItemType.SummerSpangle, ItemType.SpangleSeed,Season.Summer),
    Sunflower(new int[]{1, 2, 3, 2}, -1, false, ItemType.Sunflower, ItemType.SunflowerSeed,Season.Summer),
    Tomato(new int[]{2, 2, 2, 2, 3}, 4, false, ItemType.Tomato, ItemType.TomatoSeed,Season.Summer),
    Wheat(new int[]{1, 1, 1, 1}, -1, false, ItemType.Wheat, ItemType.WheatSeed,Season.Summer),
    Amaranth(new int[]{1, 2, 2, 2}, -1, false, ItemType.Amaranth, ItemType.AmaranthSeed,Season.Fall),
    Artichoke(new int[]{2, 2, 1, 2, 1}, -1, false, ItemType.Artichoke, ItemType.ArtichokeSeed,Season.Fall),
    Beet(new int[]{1, 1, 2, 2}, -1, false, ItemType.Beet, ItemType.BeetSeed,Season.Fall),
    BokChoy(new int[]{1, 1, 1, 1}, -1, false, ItemType.BokChoy, ItemType.BokChoySeed,Season.Fall),
    Broccoli(new int[]{2, 2, 2, 2}, 4, false, ItemType.Broccoli, ItemType.BroccoliSeed,Season.Fall),
    Cranberries(new int[]{1, 2, 1, 1, 2}, 5, false, ItemType.Cranberries, ItemType.CranberrySeed,Season.Fall),
    SweetGemBerry(new int[]{2, 4, 6, 6, 6}, -1, false, ItemType.SweetGemBerry, ItemType.RareSeed,Season.Fall),
    Powdermelon(new int[]{1, 2, 1, 2, 1}, -1, true, ItemType.Powdermelon, ItemType.PowdermelonSeed,Season.Fall),
    AncientFruit(new int[]{2, 7, 7, 7, 5}, 7, false, ItemType.AncientFruit, ItemType.AncientSeed,Season.Spring);



    private int[] stages ;
    private int reGrowthTime ;
    private boolean canBecomeGiant;
    private ItemType product , source;
    private Season season;


    CropType( int[] stages, int reGrowthTime, boolean canBecomeGiant,ItemType product,ItemType source,Season season) {
        this.stages = stages;
        this.reGrowthTime = reGrowthTime;
        this.canBecomeGiant = canBecomeGiant;
        this.source = source;
        this.product = product;
        this.season = season;

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


}

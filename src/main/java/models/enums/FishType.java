package models.enums;

import java.util.ArrayList;
import java.util.List;

public enum FishType {
    Salmon(Season.Fall,ItemType.Salmon , 1),
    Sardine(Season.Fall,ItemType.Sardine, 2),
    Shad(Season.Fall,ItemType.Shad,3),
    BlueDiscus(Season.Fall,ItemType.Discus,4),
    MidnightCarp(Season.Winter , ItemType.MidnightCarp,11),
    Squid(Season.Winter , ItemType.Squid,12),
    Tuna(Season.Winter , ItemType.Tuna,13),
    Perch(Season.Winter , ItemType.Perch,14),
    Flounder(Season.Spring , ItemType.Flounder,21),
    Lionfish(Season.Spring , ItemType.LionFish,22),
    Herring(Season.Spring , ItemType.Herring,23),
    GhostFish(Season.Spring , ItemType.GhostFish,24),
    Tilapia(Season.Summer , ItemType.Tilapia,31),
    Dorado(Season.Summer , ItemType.Dorado,32),
    Sunfish(Season.Summer,ItemType.Sunfish,33),
    RainbowTrout(Season.Summer , ItemType.RainbowTrout,34),
    Legend(Season.Spring , ItemType.Legend,25),
    Glacierfish(Season.Winter , ItemType.Glacierfish,15),
    Angler(Season.Fall , ItemType.Angler,5),
    Crimsonfish(Season.Summer , ItemType.Crimsonfish,35),
    ;
    private Season season;
    private ItemType fish;
    private int fishId;

    FishType(Season season, ItemType fish,int id) {
        this.season = season;
        this.fish = fish;
        this.fishId = id;
    }

    public Season getSeason() {
        return season;
    }

    public ItemType getFish() {
        return fish;
    }

    public int getFishId() {
        return fishId;
    }

    public static List<FishType> getFishTypeListBySeason(Season season){
        List<FishType> fishTypes = new ArrayList<>();
        for (FishType fishType : FishType.values()) {
            if(fishType.getSeason().equals(season)){
                fishTypes.add(fishType);
            }
        }
        return fishTypes;
    }

}

package models.enums;

public enum FishType {
    Salmon(Season.Fall,ItemType.Salmon),
    Sardine(Season.Fall,ItemType.Sardine),
    Shad(Season.Fall,ItemType.Shad),
    BlueDiscus(Season.Fall,ItemType.Discus),
    MidnightCarp(Season.Winter , ItemType.MidnightCarp),
    Squid(Season.Winter , ItemType.Squid),
    Tuna(Season.Winter , ItemType.Tuna),
    Perch(Season.Winter , ItemType.Perch),
    Flounder(Season.Spring , ItemType.Flounder),
    Lionfish(Season.Spring , ItemType.LionFish),
    Herring(Season.Spring , ItemType.Herring),
    GhostFish(Season.Spring , ItemType.GhostFish),
    Tilapia(Season.Summer , ItemType.Tilapia),
    Dorado(Season.Summer , ItemType.Dorado),
    Sunfish(Season.Summer,ItemType.Sunfish),
    RainbowTrout(Season.Summer , ItemType.RainbowTrout),
    Legend(Season.Spring , ItemType.Legend),
    Glacierfish(Season.Winter , ItemType.Glacierfish),
    Angler(Season.Fall , ItemType.Angler),
    Crimsonfish(Season.Summer , ItemType.Crimsonfish)
    ;
    private Season season;
    private ItemType fish;

    FishType(Season season, ItemType fish) {
        this.season = season;
        this.fish = fish;
    }

    public Season getSeason() {
        return season;
    }

    public ItemType getFish() {
        return fish;
    }
}

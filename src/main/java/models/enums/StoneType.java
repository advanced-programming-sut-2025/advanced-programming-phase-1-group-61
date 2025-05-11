package models.enums;

public enum StoneType {
    Gold(10,ItemType.Gold), Iron(30,ItemType.Iron), Quartz(20,ItemType.Quartz),
    EarthCrystal(20,ItemType.EarthCrystal),
    FrozenTear(7,ItemType.FrozenTear), FireQuartz(5,ItemType.FireQuartz)
    , Emerald(2,ItemType.Emerald), Aquamarine(3,ItemType.Aquamarine),
    Ruby(2,ItemType.Ruby), Amethyst(5,ItemType.Amethyst),
    Topaz(6,ItemType.Topaz), Jade(3,ItemType.Jade), Diamond(1,ItemType.Diamond),
    PrismaticShard(1,ItemType.PrismaticShard),
    Copper(40,ItemType.Copper), Iridium(5,ItemType.Iridium), Coal(20,ItemType.Coal);
    private int harvestAbleAmount;
    private ItemType oreType;

    StoneType(int harvestAbleAmount, ItemType oreType) {
        this.harvestAbleAmount = harvestAbleAmount;
        this.oreType = oreType;
    }

    public int getHarvestAbleAmount() {
        return harvestAbleAmount;
    }

    public ItemType getOreType() {
        return oreType;
    }
}

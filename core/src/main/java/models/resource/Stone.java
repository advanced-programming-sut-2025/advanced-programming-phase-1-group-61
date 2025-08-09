package models.resource;

import models.enums.ItemType;
import models.enums.StoneType;

public class Stone extends Resource{
    private StoneType type;
    private int harvestAbleAmount;
    private ItemType oreType;


    public Stone() {
    }

    public Stone(StoneType type) {
        this.type = type;
        this.harvestAbleAmount = type.getHarvestAbleAmount();
        this.oreType = type.getOreType();
    }

    public StoneType getType() {
        return type;
    }

    public int getHarvestAbleAmount() {
        return harvestAbleAmount;
    }

    public void setHarvestAbleAmount(int harvestAbleAmount) {
        this.harvestAbleAmount = harvestAbleAmount;
    }


}

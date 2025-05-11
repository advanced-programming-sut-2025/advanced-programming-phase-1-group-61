package models.character;

import models.App;

public class Buff {
    private final int energyIncrease;
    private final int hours;
    private final boolean farmingBuff;
    private final boolean miningBuff;
    private final boolean foragingBuff;
    private final boolean fishingBuff;
    private final int targetHour;
    public Buff(int energyIncrease,int hours, boolean farmingBuff, boolean miningBuff, boolean foragingBuff, boolean fishingBuff) {
        this.energyIncrease = energyIncrease;
        this.hours = hours;
        this.farmingBuff = farmingBuff;
        this.miningBuff = miningBuff;
        this.foragingBuff = foragingBuff;
        this.fishingBuff = fishingBuff;
        int currentHour = App.getCurrentGame().getDate().getHour();
        targetHour = (currentHour +hours)%22;
    }
    public int getEnergyIncrease() {
        return energyIncrease;
    }
    public boolean isFarmingBuff() {
        return farmingBuff;
    }
    public boolean isMiningBuff() {
        return miningBuff;
    }
    public boolean isForagingBuff() {
        return foragingBuff;
    }
    public boolean isFishingBuff() {
        return fishingBuff;
    }
    public int getHours() {
        return hours;
    }
    public int getTargetHour() {
        return targetHour;
    }
}

package models.character;

import io.github.camera.Main;
import models.App;

public class Buff {
    private  int energyIncrease;
    private  int hours;
    private  boolean farmingBuff;
    private  boolean miningBuff;
    private  boolean foragingBuff;
    private  boolean fishingBuff;
    private int targetHour;
    private boolean isOnUse=false;
    public Buff(int energyIncrease,int hours, boolean farmingBuff, boolean miningBuff, boolean foragingBuff, boolean fishingBuff) {
        this.energyIncrease = energyIncrease;
        this.hours = hours;
        this.farmingBuff = farmingBuff;
        this.miningBuff = miningBuff;
        this.foragingBuff = foragingBuff;
        this.fishingBuff = fishingBuff;
    }

    public Buff() {
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
    public void use(){
        int currentHour = Main.getApp().getCurrentGame().getDate().getHour();
        targetHour = (currentHour +hours)%22;
        isOnUse=true;
    }
    public boolean isOnUse() {
        return isOnUse;
    }
}

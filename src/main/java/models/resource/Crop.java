package models.resource;

import models.enums.CropType;
import models.enums.ItemType;
import models.enums.WeatherState;

import java.util.List;
import java.util.regex.Pattern;

public class Crop extends Resource{
    private CropType type;
    private int cropStage;
    private int cropAge;
    private int daysTillNextHarvest;
    private int daysWithOutWater ;
    private boolean isWatered;

    public Crop(CropType type) {
        this.type = type;
        this.cropStage = 1;
        this.cropAge = 0;
        this.daysTillNextHarvest = type.getTotalHarvestTime();
        this.isWatered = false;
        this.daysWithOutWater =0;
    }

    public void setCropStage(int cropStage) {
        this.cropStage = cropStage;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public CropType getType() {
        return type;
    }

    public int getCropStage() {
        return cropStage;
    }

    public int getCropAge() {
        return cropAge;
    }

    public int getDaysTillNextHarvest() {
        return daysTillNextHarvest;
    }

    public boolean isWatered() {
        return isWatered;
    }

    public void dayCycleForCrops(WeatherState state){
       if(isWatered){
           this.cropAge++;
           setCropStage(type.getStageByCropAge(this.cropAge));
           if(daysTillNextHarvest >0){
               daysTillNextHarvest--;
           }
           if(!state.equals(WeatherState.Rain)){
               this.isWatered = false;
           }
       }else {
           this.daysWithOutWater++;
       }
    }

    public void setDaysTillNextHarvest(int daysTillNextHarvest) {
        this.daysTillNextHarvest = daysTillNextHarvest;
    }
}

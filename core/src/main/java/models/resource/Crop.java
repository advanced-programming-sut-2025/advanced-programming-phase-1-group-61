package models.resource;

import models.enums.CropType;
import models.enums.TileType;
import models.enums.WeatherState;
import models.map.Tile;

public class Crop extends Resource{
    private CropType type;
    private int cropStage;
    private int cropAge;
    private int daysTillNextHarvest;
    private int daysWithOutWater ;
    private boolean isWatered;
    private boolean hasSpeedGro , hasDeuxRetailingSoil;

    public Crop(CropType type) {
        super(type.getResourceTexturePath());

        this.type = type;
        this.cropStage = 1;
        this.cropAge = 0;
        this.daysTillNextHarvest = type.getTotalHarvestTime();
        this.isWatered = false;
        this.daysWithOutWater =0;
        this.hasSpeedGro = false;
        this.hasDeuxRetailingSoil = false;
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


    public void dayCycleForCrops(WeatherState state , Tile tile){
        if(tile.getType().equals(TileType.GreenHouse)){
            isWatered = true;
        }
       if(isWatered ){
           int dTime = 1;
           if(hasSpeedGro){
               dTime = 2;
           }
           this.cropAge += dTime;
           setCropStage(type.getStageByCropAge(this.cropAge));
           if(daysTillNextHarvest >0){
               daysTillNextHarvest -= dTime;
               if(daysTillNextHarvest < 0){
                   daysTillNextHarvest = 0;
               }
           }
           if(!state.equals(WeatherState.Rain) && !hasDeuxRetailingSoil){
               this.isWatered = false;
           }
       }else {
           this.daysWithOutWater++;
       }
    }

    public void setDaysTillNextHarvest(int daysTillNextHarvest) {
        this.daysTillNextHarvest = daysTillNextHarvest;
    }
    public boolean isFertilized(){
        if(hasSpeedGro || hasDeuxRetailingSoil){
            return true;
        }
        return false;
    }

    public void setHasSpeedGro(boolean hasSpeedGro) {
        this.hasSpeedGro = hasSpeedGro;
    }

    public void setHasDeuxRetailingSoil(boolean hasDeuxRetailingSoil) {
        this.hasDeuxRetailingSoil = hasDeuxRetailingSoil;
    }
}

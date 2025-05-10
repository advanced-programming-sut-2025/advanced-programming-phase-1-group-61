package models.enums;

import models.tool.*;

import java.util.Map;

public enum ToolType {
    Axe(new Axe(),Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    FishingPole(new FishingPole(),Map.of("educational",8,"bamboo",8,"fiberGlass",6,"iridium",4)),
    Hoe(new Hoe(),Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    MilkPail(new MilkPail(),Map.of("default",4)),
    PickAxe(new Pickaxe(),Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    Scythe(new Scythe(),Map.of("default",2)),
    Shear(new Shear(),Map.of("default",4)),
    WateringCan(new WateringCan(),Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1));
    private final Tool tool;
    private final Map<String, Integer> energyPerLevel;
    ToolType(Tool tool, Map<String, Integer> energyPerLevel) {
        this.tool = tool;
        this.energyPerLevel = energyPerLevel;
    }
    public Tool getTool() {
        return tool;
    }
    public String toString(){
        return this.name();
    }
}

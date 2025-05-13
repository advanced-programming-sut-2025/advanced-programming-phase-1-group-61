package models.enums;

import models.tool.*;

import java.util.Map;

public enum ToolType {
    Axe("Axe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    FishingPole("FishingPole",Map.of("training",8,"bamboo",8,"fiberGlass",6,"iridium",4)),
    Hoe("Hoe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    MilkPail("MilkPail",Map.of("default",4)),
    PickAxe("PickAxe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1)),
    Scythe("Scythe",Map.of("default",2)),
    Shear("Shear",Map.of("default",4)),
    WateringCan("WateringCan",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1));
    private final String tool;
    private final Map<String, Integer> energyPerLevel;
    ToolType(String tool, Map<String, Integer> energyPerLevel) {
        this.tool = tool;
        this.energyPerLevel = energyPerLevel;
    }
    public String getTool() {
        return tool;
    }
    public String toString(){
        return this.name();
    }
    public int getEnergyConsumption(String level){
        return this.energyPerLevel.get(level);
    }
    public Map<String, Integer> getEnergyPerLevel(){
        return this.energyPerLevel;
    }
    public String getNextLevel(String currentLevel) {
        Object[] levels = energyPerLevel.keySet().toArray();
        for (int i = 0; i < levels.length - 1; i++) {
            if (levels[i].equals(currentLevel)) {
                return (String) levels[i + 1];
            }
        }
        return null;
    }

}

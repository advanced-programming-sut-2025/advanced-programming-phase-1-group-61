package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import models.tool.*;

import java.util.HashMap;
import java.util.Map;

public enum ToolType {
    Axe("Axe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","tools/Axe/Axe.png","copper","tools/Axe/Copper_Axe.png","iron","tools/Axe/Steel_Axe.png","gold","tools/Axe/Gold_Axe.png","iridium","Iridium_Axe.png")),
    FishingPole("FishingPole",Map.of("educational",8,"bamboo",8,"fiberGlass",6,"iridium",4),
        Map.of("educational","tools/Fishing_Pole/Training_Rod.png","bamboo","tools/Fishing_Pole/Bamboo_Pole","fiberGlass","tools/Fishing_Pole/Fiberglass_Rod","iridium","tools/Fishing_Pole/Iridium_Rod")),
    Hoe("Hoe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","","copper","","iron","","gold","","iridium","")),
    MilkPail("MilkPail",Map.of("default",4),
        Map.of("default","")),
    PickAxe("PickAxe",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","","copper","","iron","","gold","","iridium","")),
    Scythe("Scythe",Map.of("primary",2),
        Map.of("primary","","copper","","iron","","gold","","iridium","")),
    Shear("Shear",Map.of("default",4),
        Map.of("primary","","copper","","iron","","gold","","iridium","")),
    WateringCan("WateringCan",Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","","copper","","iron","","gold","","iridium","")),;
    private final String tool;
    private final Map<String, Integer> energyPerLevel;
    private final Map<String,String> internalPathForEachLevel;
    ToolType(String tool, Map<String, Integer> energyPerLevel, Map<String,String> internalPathForEachLevel) {
        this.tool = tool;
        this.energyPerLevel = energyPerLevel;
        this.internalPathForEachLevel = internalPathForEachLevel;
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
    public Texture getTextureForLevel(String level){
        for(Map.Entry<String,Integer> entry : energyPerLevel.entrySet()){
            if(entry.getKey().equals(level)){
                return new Texture(Gdx.files.internal(entry.getKey()));
            }
        }
        return new Texture("");
    }
}

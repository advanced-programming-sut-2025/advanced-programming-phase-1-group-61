package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import models.tool.*;

import java.util.HashMap;
import java.util.Map;

public enum ToolType {
    Axe("Axe",
        Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","tools/Axe/Axe.png","copper","tools/Axe/Copper_Axe.png","iron","tools/Axe/Steel_Axe.png","gold","tools/Axe/Gold_Axe.png","iridium","Iridium_Axe.png")),
    FishingPole("FishingPole",
        Map.of("educational",8,"bamboo",8,"fiberGlass",6,"iridium",4),
        Map.of("training","tools/Fishing_Pole/Training_Rod.png","bamboo","tools/Fishing_Pole/Bamboo_Pole.png","fiberGlass","tools/Fishing_Pole/Fiberglass_Rod.png","iridium","tools/Fishing_Pole/Iridium_Rod.png")),
    Hoe("Hoe",
        Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","tools/Hoe/Hoe.png","copper","tools/Hoe/Copper_Hoe.png","iron","tools/Hoe/Steel_Hoe.png","gold","tools/Hoe/Gold_Hoe.png","iridium","tools/Hoe/Iridium_Hoe.png")),
    MilkPail("MilkPail",
        Map.of("default",4),
        Map.of("default","tools/Milk_Pail.png")),
    PickAxe("PickAxe",
        Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","tools/Pickaxe/Pickaxe.png","copper","tools/Pickaxe/Copper_Pickaxe.png","iron","tools/Pickaxe/Steel_Pickaxe.png","gold","tools/Pickaxe/Gold_Pickaxe.png","iridium","tools/Pickaxe/Iridium_Pickaxe.png")),
    Scythe("Scythe",
        Map.of("primary",2),
        Map.of("primary","tools/Scythe/Scythe.png","copper","tools/Scythe/Scythe.png","iron","tools/Scythe/Scythe.png","gold","tools/Scythe/Golden_Scythe.png","iridium","tools/Scythe/Iridium_Scythe.png")),
    Shear("Shear",
        Map.of("default",4),
        Map.of("primary","tools/Shears.png","copper","tools/Shears.png","iron","tools/Shears.png","gold","tools/Shears.png","iridium","tools/Shears.png")),
    WateringCan("WateringCan",
        Map.of("primary",5,"copper",4,"iron",3,"gold",2,"iridium",1),
        Map.of("primary","tools/Watering_Can/Watering_Can.png","copper","tools/Watering_Can/Copper_Watering_Can.png","iron","tools/Watering_Can/Steel_Watering_Can.png","gold","tools/Watering_Can/Gold_Watering_Can.png","iridium","tools/Watering_Can/Iridium_Watering_Can.png")),;
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
        for(String key : internalPathForEachLevel.keySet()){
            if(key.equals(level)){
              try {
                  return new Texture(Gdx.files.internal(internalPathForEachLevel.get(key)));
              } catch (Exception e) {
                  e.printStackTrace();
                  return new Texture("error.png");
              }
            }
        }
        return new Texture("error.png");
    }
    public Texture getTexture(){
        try {
            return new Texture(Gdx.files.internal(internalPathForEachLevel.get(internalPathForEachLevel.entrySet().iterator().next().getKey())));
        } catch (Exception e) {
            e.printStackTrace();
            return new Texture("error.png");
        }
    }
}

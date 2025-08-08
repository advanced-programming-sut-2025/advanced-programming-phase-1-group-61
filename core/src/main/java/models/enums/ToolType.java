package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public enum ToolType {
    Axe("Axe",
        new HashMap<>() {{
            put("primary",5);
            put("copper",4);
            put("iron",3);
            put("gold",2);
            put("iridium",1);
        }},
        new HashMap<>() {{
            put("primary","tools/Axe/Axe.png");
            put("copper","tools/Axe/Copper_Axe.png");
            put("iron","tools/Axe/Steel_Axe.png");
            put("gold","tools/Axe/Gold_Axe.png");
            put("iridium","Iridium_Axe.png");
        }}),
    FishingPole("FishingPole",
        new HashMap<>() {{
            put("educational", 8);
            put("bamboo", 8);
            put("fiberGlass", 6);
            put("iridium", 4);
        }},
        new HashMap<>() {{
            put("training", "tools/Fishing_Pole/Training_Rod.png");
            put("bamboo", "tools/Fishing_Pole/Bamboo_Pole.png");
            put("fiberGlass", "tools/Fishing_Pole/Fiberglass_Rod.png");
            put("iridium", "tools/Fishing_Pole/Iridium_Rod.png");
        }}),
    Hoe("Hoe",
        new HashMap<>() {{
            put("primary", 5);
            put("copper", 4);
            put("iron", 3);
            put("gold", 2);
            put("iridium", 1);
        }},
        new HashMap<>() {{
            put("primary", "tools/Hoe/Hoe.png");
            put("copper", "tools/Hoe/Copper_Hoe.png");
            put("iron", "tools/Hoe/Steel_Hoe.png");
            put("gold", "tools/Hoe/Gold_Hoe.png");
            put("iridium", "tools/Hoe/Iridium_Hoe.png");
        }}),
    MilkPail("MilkPail",
        new HashMap<>() {{
            put("default", 4);
        }},
        new HashMap<>() {{
            put("default", "tools/Milk_Pail.png");
        }}),
    PickAxe("PickAxe",
        new HashMap<>() {{
            put("primary", 5);
            put("copper", 4);
            put("iron", 3);
            put("gold", 2);
            put("iridium", 1);
        }},
        new HashMap<>() {{
            put("primary", "tools/Pickaxe/Pickaxe.png");
            put("copper", "tools/Pickaxe/Copper_Pickaxe.png");
            put("iron", "tools/Pickaxe/Steel_Pickaxe.png");
            put("gold", "tools/Pickaxe/Gold_Pickaxe.png");
            put("iridium", "tools/Pickaxe/Iridium_Pickaxe.png");
        }}),
    Scythe("Scythe",
        new HashMap<>() {{
            put("primary", 2);
        }},
        new HashMap<>() {{
            put("primary", "tools/Scythe/Scythe.png");
            put("copper", "tools/Scythe/Scythe.png");
            put("iron", "tools/Scythe/Scythe.png");
            put("gold", "tools/Scythe/Golden_Scythe.png");
            put("iridium", "tools/Scythe/Iridium_Scythe.png");
        }}),
    Shear("Shear",
        new HashMap<>() {{
            put("default", 4);
        }},
        new HashMap<>() {{
            put("primary", "tools/Shears.png");
            put("copper", "tools/Shears.png");
            put("iron", "tools/Shears.png");
            put("gold", "tools/Shears.png");
            put("iridium", "tools/Shears.png");
        }}),
    WateringCan("WateringCan",
        new HashMap<>() {{
            put("primary", 5);
            put("copper", 4);
            put("iron", 3);
            put("gold", 2);
            put("iridium", 1);
        }},
        new HashMap<>() {{
            put("primary", "tools/Watering_Can/Watering_Can.png");
            put("copper", "tools/Watering_Can/Copper_Watering_Can.png");
            put("iron", "tools/Watering_Can/Steel_Watering_Can.png");
            put("gold", "tools/Watering_Can/Gold_Watering_Can.png");
            put("iridium", "tools/Watering_Can/Iridium_Watering_Can.png");
        }});
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
    public Texture getDefaultTexture(){
        try {
            return new Texture(Gdx.files.internal(internalPathForEachLevel.get(internalPathForEachLevel.entrySet().iterator().next().getKey())));
        } catch (Exception e) {
            e.printStackTrace();
            return new Texture("error.png");
        }
    }
}

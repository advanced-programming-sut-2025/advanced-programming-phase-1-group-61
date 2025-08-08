package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;
import models.map.Map;

public class Tool {
    protected int durability;
    protected ToolType type;
    protected String level="primary";

    public Tool(ToolType type) {
        this.type = type;
    }

    public String use(Direction direction) {
        Character character= App.getCurrentGame().getCurrentCharacter();
        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        character.setEnergy(newEnergy);
        return "salam";
    }
    public void decreaseDurability(int amount){
        durability -= amount;
    }
    public String getLevel() {
        return level;
    }
     public ToolType getType(){
        return type;
     }
    public static ToolType fromString(String name){
        for(ToolType t : ToolType.values()){
            if(t.getTool().equals(name)) return t;
        }
        return null;
    }
    public  void upgrade(){
    }
    public  int getConsumptionEnergy(){
        return 0;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

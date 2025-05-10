package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;

public class Tool {
    protected int durability;
    protected ToolType type;
    protected String level="primary";
    public String use(Direction direction) {
        //this method should be overwritten in child classes!
        Character character= App.getCurrentGame().getCurrentCharacter();
        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        character.setEnergy(newEnergy);
        return "";
    }
    public void decreaseDurability(int amount){
        durability -= amount;
    }
    public String getLevel() {
        return level;
    }
    public ToolType getType() {
        return type;
    }
    public static Tool fromString(String name){
        for(ToolType t : ToolType.values()){
            if(t.toString().equalsIgnoreCase(name)) return t.getTool();
        }
        return null;
    }
}

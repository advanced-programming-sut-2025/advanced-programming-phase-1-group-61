package models.tool;

import models.App;
import models.character.Character;
import models.enums.ToolType;

public abstract class Tool {
    protected int durability;
    protected ToolType type;
    protected String level="primary";
    public void use(){
        //this method should be overwritten in child classes!
        Character character= App.getCurrentGame().getCurrentCharacter();
        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        if(newEnergy<=0) {
            character.faint();
            return;
        }
        character.setEnergy(newEnergy);
    }
    public void decreaseDurability(int amount){
        durability -= amount;
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

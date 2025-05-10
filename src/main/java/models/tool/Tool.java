package models.tool;

import models.enums.ToolType;

public class Tool {
    protected int durability;
    protected int level;
    protected ToolType type;
    public void use(){
        //todo
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

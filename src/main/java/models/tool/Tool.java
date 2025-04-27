package models.tool;

import models.enums.ToolType;

public class Tool {
    protected int durability;
    protected int level;
    private ToolType type;
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
            if(t.toString().equals(name)) return t.getTool();
        }
        return null;
    }
}

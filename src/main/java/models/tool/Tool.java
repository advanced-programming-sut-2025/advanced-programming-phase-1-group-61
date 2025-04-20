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
}

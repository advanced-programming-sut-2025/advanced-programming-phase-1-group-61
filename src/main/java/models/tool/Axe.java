package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

import java.util.Map;


public class Axe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used Axe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Axe;
    }
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

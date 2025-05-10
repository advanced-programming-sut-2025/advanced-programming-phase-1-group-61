package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Pickaxe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "Used pick Axe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.PickAxe;
    }
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

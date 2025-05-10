package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class WateringCan extends Tool{

    public WateringCan() {
        super(ToolType.WateringCan);
    }

    public String use(Direction direction){
        super.use(direction);
        return "used watering can";
    }

    @Override
    public ToolType getType() {
        return ToolType.WateringCan;
    }
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

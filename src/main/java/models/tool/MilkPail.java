package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class MilkPail extends Tool{
    public MilkPail() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used milk pail!";
    }

    @Override
    public ToolType getType() {
        return ToolType.MilkPail;
    }
    public void upgrade() {}
}

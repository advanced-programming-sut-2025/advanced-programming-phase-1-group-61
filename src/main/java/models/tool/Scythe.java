package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Scythe extends Tool{
    public Scythe() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used Scythe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Scythe;
    }
}

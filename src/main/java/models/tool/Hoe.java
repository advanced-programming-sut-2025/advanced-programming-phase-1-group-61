package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Hoe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used hoe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Hoe;
    }
}

package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Axe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used Axe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Axe;
    }
}

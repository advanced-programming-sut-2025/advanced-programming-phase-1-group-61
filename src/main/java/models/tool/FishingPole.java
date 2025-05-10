package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class FishingPole extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "Used fishing pole!";
    }

    @Override
    public ToolType getType() {
        return ToolType.FishingPole;
    }
}

package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class WateringCan extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used watering can";
    }

    @Override
    public ToolType getType() {
        return ToolType.WateringCan;
    }
}

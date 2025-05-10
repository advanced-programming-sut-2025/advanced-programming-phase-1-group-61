package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Shear extends Tool{
    public Shear() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used shear!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Shear;
    }
}

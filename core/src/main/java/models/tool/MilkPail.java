package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class MilkPail extends Tool{


    public MilkPail() {
       super(ToolType.MilkPail);
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

    @Override
    public int getConsumptionEnergy() {
        return this.type.getEnergyConsumption(this.level);
    }
}

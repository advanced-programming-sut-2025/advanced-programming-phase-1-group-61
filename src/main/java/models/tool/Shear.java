package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class Shear extends Tool{


    public Shear() {
        super(ToolType.Shear);
    }
    public String use(Direction direction){
        super.use(direction);
        return "used shear!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Shear;
    }
    public void upgrade() {}

    @Override
    public int getConsumptionEnergy() {
        return this.type.getEnergyConsumption(this.level);
    }

}

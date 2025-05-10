package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;

public class Scythe extends Tool{


    public Scythe() {
        super(ToolType.Scythe);
    }
    public String use(Direction direction){
        super.use(direction);
        return "used Scythe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Scythe;
    }
    public void upgrade() {}

    @Override
    public int getConsumptionEnergy() {
        return this.type.getEnergyConsumption(this.level);
    }
}

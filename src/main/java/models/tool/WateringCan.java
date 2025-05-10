package models.tool;

import models.enums.Direction;

public class WateringCan extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used watering can";
    }
}

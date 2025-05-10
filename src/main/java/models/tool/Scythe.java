package models.tool;

import models.enums.Direction;

public class Scythe extends Tool{
    public Scythe() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used Scythe!";
    }
}

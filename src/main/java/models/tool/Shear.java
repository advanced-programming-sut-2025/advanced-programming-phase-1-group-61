package models.tool;

import models.enums.Direction;

public class Shear extends Tool{
    public Shear() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used shear!";
    }
}

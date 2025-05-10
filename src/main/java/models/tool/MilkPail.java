package models.tool;

import models.enums.Direction;

public class MilkPail extends Tool{
    public MilkPail() {
        level="default";
    }
    public String use(Direction direction){
        super.use(direction);
        return "used milk pail!";
    }

    @Override
    public void upgrade() {}
}

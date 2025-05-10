package models.tool;

import models.enums.Direction;

public class Hoe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used hoe!";
    }
}

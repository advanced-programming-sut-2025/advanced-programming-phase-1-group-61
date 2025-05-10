package models.tool;

import models.enums.Direction;

public class Axe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used Axe!";
    }
}

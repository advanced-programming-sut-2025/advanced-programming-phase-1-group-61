package models.tool;

import models.enums.Direction;

public class Pickaxe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "Used pick Axe!";
    }
}

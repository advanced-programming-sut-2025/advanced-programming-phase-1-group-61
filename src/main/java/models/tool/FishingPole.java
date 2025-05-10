package models.tool;

import models.enums.Direction;

public class FishingPole extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "Used fishing pole!";
    }
}

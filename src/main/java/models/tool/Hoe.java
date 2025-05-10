package models.tool;

import models.enums.Direction;

public class Hoe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "used hoe!";
    }

    @Override
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

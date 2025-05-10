package models.tool;

import models.enums.Direction;

public class Pickaxe extends Tool{
    public String use(Direction direction){
        super.use(direction);
        return "Used pick Axe!";
    }

    @Override
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

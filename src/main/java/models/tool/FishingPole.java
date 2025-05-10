package models.tool;

import models.enums.Direction;
import models.enums.ToolType;

public class FishingPole extends Tool{
    public FishingPole() {
        super(ToolType.FishingPole);
    }

    public String use(Direction direction){
        super.use(direction);
        return "Used fishing pole!";
    }

    @Override
    public ToolType getType() {
        return ToolType.FishingPole;
    }
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;
    }
}

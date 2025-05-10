package models.tool;

import models.App;
import models.character.Character;
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

    @Override
    public int getConsumptionEnergy() {
        int consume=this.type.getEnergyConsumption(this.level);
        Character character= App.getCurrentGame().getCurrentCharacter();
        if(character.getSkill().getFishingLVL()==4)
            consume=Math.max(0,consume-1);
        return consume;
    }
}

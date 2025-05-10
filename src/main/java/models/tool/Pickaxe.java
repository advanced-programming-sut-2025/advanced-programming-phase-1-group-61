package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;
import models.map.Map;

public class Pickaxe extends Tool{

    public Pickaxe() {
        super(ToolType.PickAxe);
    }

    public String use(Direction direction){
        super.use(direction);
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=App.getCurrentGame().getMap();
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "hit nothing!";
        return "Used pick Axe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.PickAxe;
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
        if(character.getSkill().getMiningLVL()==4)
            consume=Math.max(0,consume-1);
        return consume;
    }
}

package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.TileType;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;

public class FishingPole extends Tool{
    public FishingPole() {
        super(ToolType.FishingPole);
    }

    public String use(Direction direction){
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant fish void \n(pls stop trying to break our game)";

        if(!tile.getType().equals(TileType.Water)){
            return "what do you want to fish form here?! worms?";
        }
        if(character.getCurrentTool().getLevel().equals("educational")){

        }
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

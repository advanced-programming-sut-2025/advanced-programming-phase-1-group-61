package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.TileType;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;
import models.resource.Resource;
import models.resource.Stone;
import models.resource.Tree;


public class Axe extends Tool{

    public Axe() {
        super(ToolType.Axe);
    }

    @Override
    public String use(Direction direction){
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant chop down void \n(pls stop trying to break our game)";
        if(tile.getResource() != null){
            Resource resource = tile.getResource();
            if(resource instanceof Tree) {

                return "tree chopped down";
            }
        }
        int newEnergy=character.getEnergy()-1;
        character.setEnergy(newEnergy);
        return "There is no use for Axe here";
    }

    @Override
    public ToolType getType() {
        return ToolType.Axe;
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
        if(character.getSkill().getForagingLVL()==4)
            consume=Math.max(0,consume-1);
        return consume;
    }


}

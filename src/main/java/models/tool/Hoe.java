package models.tool;

import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.TileType;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;

public class Hoe extends Tool{
    public Hoe() {
        super(ToolType.Hoe);
    }

    public String use(Direction direction){
        Character character = App.getCurrentGame().getCurrentCharacter();
        Map map = App.getCurrentGame().getMap();
        int x = character.getX();
        int y = character.getY();
        x += direction.getDx();
        y += direction.getDy();
        if(x < 0 || x >= map.getWidthSize() || y <0 ||
                y >= map.getHeightSize()){
            return "you cant dig at x: "+x +" y: "+y;
        }
       Tile tile = map.getTileByCordinate(x, y);
        if(!tile.getType().equals(TileType.grass)){
            return "you cant dig here its not grass";
        }
        if(tile.getResource()!=null){
            return "there is a in this tile remove it first";
        }
        tile.setType(TileType.soil);

        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        character.setEnergy(newEnergy);
        return "used hoe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Hoe;
    }
    public void upgrade() {
        if(this.level.equals("iridium")) return;
        String nextLevel = type.getNextLevel(level);
        if(nextLevel==null) return;
        this.level = nextLevel;

    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }
}
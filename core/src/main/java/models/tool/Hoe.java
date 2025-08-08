package models.tool;

import io.github.camera.Main;
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
        Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
        Map map = Main.getApp().getCurrentGame().getMap();
        int x = character.getX() + direction.getDx();
        int y = character.getY() + direction.getDy();

        if(x < 0 || x >= map.getWidthSize() || y <0 ||
                y >= map.getHeightSize()){
            return "you cant dig at x: "+x +" y: "+y;
        }
       Tile tile = map.getTileByCordinate(x, y);
        if(!tile.getType().equals(TileType.Grass)){
            return "you cant dig here its not grass";
        }
        if(tile.getResource()!=null){
            return "there is a resource in this tile remove it first";
        }
        if(tile.getItem()!= null){
            tile.setItem(null);
        }
        tile.setType(TileType.Soil);

        int newEnergy=character.getEnergy()-type.getEnergyConsumption(level);
        character.setEnergy(newEnergy);
        character.getSkill().addFarmingSkillXP(5);
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

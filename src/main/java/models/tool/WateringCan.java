package models.tool;

import models.App;
import models.RandomNumber;
import models.character.Character;
import models.enums.Direction;
import models.enums.TileType;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;
import models.resource.Crop;
import models.resource.Resource;
import models.resource.Tree;

public class WateringCan extends Tool{

    public WateringCan() {
        super(ToolType.WateringCan);
        this.durability = 40;
        this.level="primary";
    }

    public String use(Direction direction){
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant water void \n(pls stop trying to break our game)";
        if(tile.getType().equals(TileType.Water)){
            int water=0;
           switch (this.level){
               case "primary":{
                   water=40;
                   break;
               }
               case "copper":{
                   water=55;
                   break;
               } case "iron":{
                   water = 70;
                   break;
               } case "gold":{
                   water = 85;
                   break;
               } case "iridium": {
                   water = 100;
                   break;
               }
           }
           this.durability = water;
        }
        else if (tile.getType().equals(TileType.Soil)) {
            Crop crop =(Crop) tile.getResource();
            int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getForagingLVL();
            if(dEnergy <0){
                dEnergy = 0;
            }
            int newEnergy=character.getEnergy()-dEnergy;
            character.setEnergy(newEnergy);
        }
        int newEnergy=character.getEnergy()-1;
        character.setEnergy(newEnergy);
        return "There is no use for Watering Can here";
    }

    @Override
    public ToolType getType() {
        return ToolType.WateringCan;
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
        if(character.getSkill().getFarmingLVL()==4)
            consume=Math.max(0,consume-1);
        return consume;
    }
}

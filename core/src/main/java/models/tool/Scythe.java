package models.tool;

import io.github.camera.Main;
import models.App;
import models.character.Character;
import models.enums.Direction;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;
import models.resource.Crop;
import models.resource.Resource;
import models.resource.Tree;

public class Scythe extends Tool{


    public Scythe() {
        super(ToolType.Scythe);
    }
    public String use(Direction direction){
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=Main.getApp().getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant chop down void \n(pls stop trying to break our game)";
        Resource resource = tile.getResource();
        if(resource instanceof Crop){
            Crop crop =(Crop) resource;
            if(crop.getDaysTillNextHarvest() ==0){
               character.getInventory().addItem(crop.getType().getProduct(),1);
               character.getInventory().addItem(crop.getType().getSource(), 1);
                if(crop.getType().getReGrowthTime() <=0){
                    tile.setResource(null);
                }else {
                    crop.setDaysTillNextHarvest(crop.getType().getReGrowthTime());
                }
                return "crop harvested";
            }else {
                return "crop not ready yet use pickAxe if you want to remove it";
            }
        } else if (resource instanceof Tree) {
            Tree tree = (Tree) resource;
            if(tree.getDaysUntilNextCycle() == 0){
                character.getInventory().addItem(tree.getFruit() , 7);
                tree.setDaysUntilNextCycle(tree.getType().getHarvestCycle());
                return "tree harvested";
            }
            return "tree not ready yet";
        }
        int energy =   character.getEnergy();
        character.setEnergy(energy - 2);
        return "used Scythe!";
    }

    @Override
    public ToolType getType() {
        return ToolType.Scythe;
    }
    public void upgrade() {}

    @Override
    public int getConsumptionEnergy() {
        return this.type.getEnergyConsumption(this.level);
    }
}

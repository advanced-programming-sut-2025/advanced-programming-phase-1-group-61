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

public class Pickaxe extends Tool{

    public Pickaxe() {
        super(ToolType.PickAxe);
    }

    public String use(Direction direction){
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant mine void \n(pls stop trying to break our game)";
        if(tile.getResource() != null){
            Resource resource = tile.getResource();
            if(resource instanceof Stone){
                Stone stone = (Stone) resource;
                int amount = stone.getHarvestAbleAmount();
                if(character.getSkill().getMiningLVL() >2){
                    amount++;
                }
                character.getInventory().addItem(stone.getType().getOreType(), amount);
                tile.setResource(null);
                character.getSkill().addMiningSkillXP(10);
                int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getMiningLVL();
                if(dEnergy <0){
                    dEnergy = 0;
                }
                int newEnergy=character.getEnergy()-dEnergy;
                character.setEnergy(newEnergy);
                return "Used pickaxe to mine";
            }
            else if(!(resource instanceof Tree)) {
                tile.setResource(null);
                int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getMiningLVL();
                if(dEnergy <0){
                    dEnergy = 0;
                }
                int newEnergy=character.getEnergy()-dEnergy;
                character.setEnergy(newEnergy);
                return "Removed the item placed in this tile";
            }
        }
        if(tile.getItem()!= null){
            character.getInventory().addItem(tile.getItem().getItemType() , 1);
            int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getMiningLVL();
            if(dEnergy <0){
                dEnergy = 0;
            }
            int newEnergy=character.getEnergy()-dEnergy;
            character.setEnergy(newEnergy);
            tile.setItem(null);
        }
        if(tile.getType().equals(TileType.Soil)){
            tile.setResource(null);
            tile.setType(TileType.Grass);
            int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getMiningLVL();
            if(dEnergy <0){
                dEnergy = 0;
            }
            int newEnergy=character.getEnergy()-dEnergy;
            character.setEnergy(newEnergy);
            return "changed soil to grass you cant farm on this land any more.";
        }
        int newEnergy=character.getEnergy()-1;
        character.setEnergy(newEnergy);
        return "There is no use for pickAxe here";
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

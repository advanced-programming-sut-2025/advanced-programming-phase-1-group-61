package models.tool;

import models.App;
import models.RandomNumber;
import models.character.Character;
import models.enums.Direction;
import models.enums.ItemType;
import models.enums.ToolType;
import models.map.Map;
import models.map.Tile;
import models.resource.Resource;
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
        Map<S, S1> map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant chop down void \n(pls stop trying to break our game)";
        if(tile.getResource() != null){
            Resource resource = tile.getResource();
            if(resource instanceof Tree) {
                Tree tree = (Tree) resource;
                int randomNumber = RandomNumber.getRandomNumberWithBoundaries(1,3);
                character.getInventory().addItem(tree.getSource() , randomNumber);
                int randomAmount = RandomNumber.getRandomNumberWithBoundaries(3 , 7);
                character.getInventory().addItem(tree.getFruit(),randomAmount);
                character.getInventory().addItem(ItemType.Wood,10);
                tile.setResource(null);
                character.getSkill().addForgingSkillXP(10);
                int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getForagingLVL();
                if(dEnergy <0){
                    dEnergy = 0;
                }
                int newEnergy=character.getEnergy()-dEnergy;
                character.setEnergy(newEnergy);
                return "tree chopped down";
            }
        }
        int newEnergy=character.getEnergy()-1;
        character.setEnergy(newEnergy);
        return "There is no use for Axe here";
    }

    public String useForSyrup(Direction direction){
        Character character= App.getCurrentGame().getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map<S, S1> map=App.getCurrentGame().getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant get syrup of void \n(pls stop trying to break our game)";
        if(tile.getResource() != null){
            Resource resource = tile.getResource();
            if(resource instanceof Tree) {
                Tree tree = (Tree) resource;
                int randomAmount = RandomNumber.getRandomNumberWithBoundaries(3 , 7);
                character.getInventory().addItem(tree.getFruit(),randomAmount);
                character.getSkill().addForgingSkillXP(10);
                int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getForagingLVL();
                if(dEnergy <0){
                    dEnergy = 0;
                }
                int newEnergy=character.getEnergy()-dEnergy;
                character.setEnergy(newEnergy);
                return "you now have the syrup";
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

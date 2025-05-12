package models.tool;

import models.App;
import models.Game;
import models.RandomNumber;
import models.character.Character;
import models.enums.*;
import models.map.Map;
import models.map.Tile;

import java.util.Comparator;
import java.util.List;

public class FishingPole extends Tool{
    public FishingPole() {
        super(ToolType.FishingPole);
    }

    public String use(Direction direction){
        Game game =  App.getCurrentGame();
        Character character= game.getCurrentCharacter();
        int targetX=character.getX()+direction.getDx();
        int targetY=character.getY()+direction.getDy();
        Map map=game.getMap();
        Tile tile = map.getTileByCordinate(targetX , targetY);
        if(targetY<0 || targetX<0 || targetY>=map.getHeightSize() || targetX>=map.getWidthSize())
            return "you cant fish void \n(pls stop trying to break our game)";

        if(!tile.getType().equals(TileType.Water)){
            return "what do you want to fish form here?! worms?";
        }else {
            Season season =game.getDate().getSeason();
            ItemType fish = null;
            if(character.getCurrentTool().getLevel().equals("educational")){
                switch (season){
                    case Spring:{
                        fish = FishType.Herring.getFish();
                        break;
                    }case Summer:{
                        fish = FishType.Sunfish.getFish();
                        break;
                    }case Fall:{
                        fish = FishType.Sardine.getFish();
                        break;
                    }case Winter:{
                        fish = FishType.Perch.getFish();
                        break;
                    }
                }
                character.getInventory().addItem(fish , 1);
            }else {
               List<FishType> fishTypes = FishType.getFishTypeListBySeason(season);
               fishTypes.sort(Comparator.comparing(fishType -> fishType.getFish().getPrice()));
               int randNumber = RandomNumber.getRandomNumber() % 100;
                if(randNumber < 30){
                    fish = fishTypes.get(0).getFish();
                } else if (randNumber < 40) {
                    fish = fishTypes.get(1).getFish();
                } else if (randNumber < 50) {
                    fish = fishTypes.get(2).getFish();
                } else if (randNumber < 60) {
                    fish = fishTypes.get(3).getFish();
                } else if (randNumber <65 && character.getSkill().getFishingLVL() >= 4) {
                    fish = fishTypes.get(4).getFish();
                }
                if(fish != null){
                    character.getInventory().addItem(fish , 1);
                    character.getSkill().addFishingSkillXP(10);
                    int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getFishingLVL();
                    if(dEnergy <0){
                        dEnergy = 0;
                    }
                    int newEnergy=character.getEnergy()-dEnergy;
                    character.setEnergy(newEnergy);
                }else {
                    int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getFishingLVL();
                    if(dEnergy <0){
                        dEnergy = 0;
                    }
                    int newEnergy=character.getEnergy()-dEnergy;
                    character.setEnergy(newEnergy);
                    return "no fish better luck next time";
                }
            }

        }
        return "Used fishing pole but something went wrong!";
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

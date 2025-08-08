package models.tool;

import io.github.camera.Main;
import models.App;
import models.Game;
import models.RandomNumber;
import models.character.Character;
import models.character.Inventory;
import models.character.InventorySlot;
import models.enums.*;
import models.map.Map;
import models.map.Tile;

import java.util.Comparator;
import java.util.List;

public class FishingPole extends Tool{
    public FishingPole() {
        super(ToolType.FishingPole);
        this.level = "educational";
    }

    public String use(Direction direction){
        Game game =  Main.getApp().getCurrentGame();
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
            double pole = 1;
            if(character.getCurrentTool().getLevel().equals("educational")){
                pole = 0.1;
                double m = 1;
                if(map.getWeather().getState().equals(WeatherState.Rain)){
                    m = 1.2;
                } else if (map.getWeather().equals(WeatherState.Storm)) {
                    m = 0.5;
                }else {
                    m=1.5;
                }
                int r = RandomNumber.getRandomNumberWithBoundaries(1,11);
                double R = (double) r / 10;
                int skill = character.getSkill().getFishingLVL();
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
                int count = (int) (m * R *(skill+2));
                character.getInventory().addItem(fish , count);
                return "you caught a fish";
            }else {
                if(character.getCurrentTool().getLevel().equals("bamboo")){
                    pole = 0.5;
                } else if (character.getCurrentTool().getLevel().equals("fiberGlass")) {
                    pole = 1;
                } else if (character.getCurrentTool().getLevel().equals("iridium")) {
                    pole = 1.5;
                }

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
                double m = 1;
                if(map.getWeather().getState().equals(WeatherState.Rain)){
                    m = 1.2;
                } else if (map.getWeather().equals(WeatherState.Storm)) {
                    m = 0.5;
                }else {
                    m=1.5;
                }
                int r = RandomNumber.getRandomNumberWithBoundaries(1,11);
                double R = (double) r / 10;
                int skill = character.getSkill().getFishingLVL();
                if(fish != null){
                    character.getInventory().addItem(fish , (int) (m * R *(skill+2)));

                    character.getSkill().addFishingSkillXP(10);
                    int dEnergy  = type.getEnergyConsumption(level)-character.getSkill().getFishingLVL();
                    if(dEnergy <0){
                        dEnergy = 0;
                    }
                    int newEnergy=character.getEnergy()-dEnergy;
                    character.setEnergy(newEnergy);
                    return "you caught a fish";
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
        Character character= Main.getApp().getCurrentGame().getCurrentCharacter();
        if(character.getSkill().getFishingLVL()==4)
            consume=Math.max(0,consume-1);
        return consume;
    }
}

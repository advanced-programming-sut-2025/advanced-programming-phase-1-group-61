package models;

import models.character.Character;
import models.date.Date;
import models.enums.*;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;
import models.resource.Stone;
import models.resource.Tree;

import java.util.List;

public class Game implements Runnable {


    private int id;
    private Map map;
    private List<Character> allCharacters;
    private int currentCharacter;
    private transient Thread gameThread;
    private final int FPS = 60;
    private Date date;
    private volatile boolean running = false;

    public Game(Map map, List<Character> characters) {
        id = App.getAllGames().size()+1;
        this.map = map;
        this.allCharacters = characters;
        this.date = new Date();
        spawnRandomResourceOnMap();
        spawnRandomItemsOnMap();
    }


    public void changeDayActivities() {
        handleWeatherBeforeDayChange();
        spawnRandomItemsOnMap();
        date.setHour(9);
    }


    public void startGameThread() {
        if (gameThread == null || !running) {
            running = true;
            gameThread = new Thread(this, "GameThread-" + id);
            gameThread.start();
        }
    }

    public void stopGameThread() {
        running = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (running) {
            update();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

    }

    public int getId() {
        return id;
    }

    public Character getCurrentCharacter() {
        for (int i = 0; i < allCharacters.size(); i++) {
            if(i==currentCharacter){
                return allCharacters.get(i);
            }
        }
        return null;
    }

    public Character getCharacterByTurnNumber(int turn) {
        Character character = allCharacters.get(turn);
        return character;
    }

    public String changeTurn() {
        int turn = currentCharacter+1;
        this.currentCharacter = turn % allCharacters.size();
        StringBuilder message =  new StringBuilder(User.getUSerById(allCharacters.get(currentCharacter).getUserId()).getNickName());

        if(turn == allCharacters.size()){
            message.append("\n");
            message.append("time increased");
            date.increaseTime(1);
        }
        return message.toString();
    }

    public Date getDate() {
        return date;
    }

    public Map getMap() {
        return map;
    }

    private void handleWeatherBeforeDayChange() {
        Weather weather = map.getWeather();
        weather.changeTomorrowWeatherState();
        weather.setState(weather.getTomorrowWeatherState());

        if (weather.getState().equals(WeatherState.Storm)) {
            for (int i = 0; i < 3; i++) {
                int x = RandomNumber.getRandomNumberWithBoundaries(0, 79);
                int y = RandomNumber.getRandomNumberWithBoundaries(0, 79);
                weather.lightning(x, y);
            }
        }
    }

    private void spawnRandomItemsOnMap(){
        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                if(tile.getType().equals(TileType.Grass)){
                    if(tile.getResource() == null){
                        int rand = RandomNumber.getRandomNumber();
                        if(date.getSeason().equals(Season.Spring)){
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.CommonMushroom));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.Daffodil));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.Dandelion));
                                    break;
                                } case 3:{
                                    tile.setItem(new Item(ItemType.Leek));
                                    break;
                                } case 4:{
                                    tile.setItem(new Item(ItemType.moral));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.SalmonBerry));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.springOnion));
                                    break;
                                } case 7:{
                                    tile.setItem(new Item(ItemType.WildHorseradish));
                                    break;
                                }
                                case 8:{
                                    tile.setItem(new Item(ItemType.JazzSeed));
                                    break;
                                }
                                case 9:{
                                    tile.setItem(new Item(ItemType.CarrotSeed));
                                    break;
                                }
                                case 10:{
                                    tile.setItem(new Item(ItemType.CauliflowerSeed));
                                    break;
                                }
                                case 11:{
                                    tile.setItem(new Item(ItemType.CoffeeBeen));
                                    break;
                                }
                                case 12:{
                                    tile.setItem(new Item(ItemType.GarlicSeed));
                                    break;
                                }
                                case 13:{
                                    tile.setItem(new Item(ItemType.BeanStarter));
                                    break;
                                }
                                case 14:{
                                    tile.setItem(new Item(ItemType.KaleSeed));
                                    break;
                                } case 15:{
                                    tile.setItem(new Item(ItemType.ParsnipSeed));
                                    break;
                                } case 16:{
                                    tile.setItem(new Item(ItemType.PotatoSeed));
                                    break;
                                } case 17:{
                                    tile.setItem(new Item(ItemType.RhubarbSeed));
                                    break;
                                } case 18:{
                                    tile.setItem(new Item(ItemType.StrawberrySeed));
                                    break;
                                } case 19:{
                                    tile.setItem(new Item(ItemType.TulipBulb));
                                    break;
                                } case 20:{
                                    tile.setItem(new Item(ItemType.RiceShoot));
                                    break;
                                } case 21:{
                                    tile.setItem(new Item(ItemType.AncientSeed));
                                    break;
                                } case 22:{
                                    tile.setItem(new Item(ItemType.MixedSeed));
                                    break;
                                } case 23:{
                                    tile.setItem(new Item(ItemType.Acorns));
                                    break;
                                } case 24:{
                                    tile.setItem(new Item(ItemType.MapleSeed));
                                    break;
                                } case 25:{
                                    tile.setItem(new Item(ItemType.PineCones));
                                    break;
                                } case 26:{
                                    tile.setItem(new Item(ItemType.MahoganySeed));
                                    break;
                                } case 27:{
                                    tile.setItem(new Item(ItemType.MushroomTreeSeed));
                                    break;
                                }
                            }
                        } else if (date.getSeason().equals(Season.Summer)) {
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.FiddleHead));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.Grape));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.RedMushroom));
                                    break;
                                } case 3:{
                                    tile.setItem(new Item(ItemType.SpiceBerry));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.SweetPea));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.BlueberrySeed));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.CornSeed));
                                    break;
                                } case 7:{
                                    tile.setItem(new Item(ItemType.HopsStarter));
                                    break;
                                } case 8:{
                                    tile.setItem(new Item(ItemType.PepperSeed));
                                    break;
                                } case 9:{
                                    tile.setItem(new Item(ItemType.MelonSeed));
                                    break;
                                } case 10:{
                                    tile.setItem(new Item(ItemType.PoppySeed));
                                    break;
                                } case 11:{
                                    tile.setItem(new Item(ItemType.RadishSeed));
                                    break;
                                } case 12:{
                                    tile.setItem(new Item(ItemType.RedCabbageSeed));
                                    break;
                                } case 13:{
                                    tile.setItem(new Item(ItemType.StarfruitSeed));
                                    break;
                                } case 14:{
                                    tile.setItem(new Item(ItemType.SpangleSeed));
                                    break;
                                } case 15:{
                                    tile.setItem(new Item(ItemType.SummerSquashSeed));
                                    break;
                                } case 16:{
                                    tile.setItem(new Item(ItemType.SunflowerSeed));
                                    break;
                                } case 17:{
                                    tile.setItem(new Item(ItemType.TomatoSeed));
                                    break;
                                } case 18:{
                                    tile.setItem(new Item(ItemType.WheatSeed));
                                    break;
                                } case 19:{
                                    tile.setItem(new Item(ItemType.AncientSeed));
                                    break;
                                } case 20:{
                                    tile.setItem(new Item(ItemType.MixedSeed));
                                    break;
                                } case 21:{
                                    tile.setItem(new Item(ItemType.Acorns));
                                    break;
                                } case 22:{
                                    tile.setItem(new Item(ItemType.MapleSeed));
                                    break;
                                } case 23:{
                                    tile.setItem(new Item(ItemType.PineCones));
                                    break;
                                } case 24:{
                                    tile.setItem(new Item(ItemType.MahoganySeed));
                                    break;
                                } case 25:{
                                    tile.setItem(new Item(ItemType.MushroomTreeSeed));
                                    break;
                                }
                            }
                        } else if (date.getSeason().equals(Season.Fall))  {
                            switch (rand%100) {
                                case 0: {
                                    tile.setItem(new Item(ItemType.Blackberry));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.Chanterelle));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.Hazelnut));
                                    break;
                                }
                                case 3:{
                                    tile.setItem(new Item(ItemType.PurpleMushroom));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.WildPlum));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.AmaranthSeed));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.ArtichokeSeed));
                                    break;
                                } case 7:{
                                    tile.setItem(new Item(ItemType.BeetSeed));
                                    break;
                                } case 8:{
                                    tile.setItem(new Item(ItemType.BokChoySeed));
                                    break;
                                } case 9:{
                                    tile.setItem(new Item(ItemType.BroccoliSeed));
                                    break;
                                } case 10:{
                                    tile.setItem(new Item(ItemType.CranberrySeed));
                                    break;
                                } case 11:{
                                    tile.setItem(new Item(ItemType.EggplantSeed));
                                    break;
                                } case 12:{
                                    tile.setItem(new Item(ItemType.FairySeed));
                                    break;
                                } case 13:{
                                    tile.setItem(new Item(ItemType.GrapeStarter));
                                    break;
                                } case 14:{
                                    tile.setItem(new Item(ItemType.PumpkinSeed));
                                    break;
                                } case 15:{
                                    tile.setItem(new Item(ItemType.YamSeed));
                                    break;
                                } case 16:{
                                    tile.setItem(new Item(ItemType.RareSeed));
                                } case 17:{
                                    tile.setItem(new Item(ItemType.AncientSeed));
                                    break;
                                } case 18:{
                                    tile.setItem(new Item(ItemType.MixedSeed));
                                    break;
                                } case 19:{
                                    tile.setItem(new Item(ItemType.Acorns));
                                    break;
                                } case 20:{
                                    tile.setItem(new Item(ItemType.MapleSeed));
                                    break;
                                } case 21:{
                                    tile.setItem(new Item(ItemType.PineCones));
                                    break;
                                } case 22:{
                                    tile.setItem(new Item(ItemType.MahoganySeed));
                                    break;
                                } case 23:{
                                    tile.setItem(new Item(ItemType.MushroomTreeSeed));
                                    break;
                                }
                            }
                        } else if (date.getSeason().equals(Season.Winter)) {
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.Crocus));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.CrystalFruit));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.Holly));
                                    break;
                                }
                                case 3:{
                                    tile.setItem(new Item(ItemType.SnowYam));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.WinterRoot));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.PowderMelonSeed));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.AncientSeed));
                                    break;
                                } case 7:{
                                    tile.setItem(new Item(ItemType.MixedSeed));
                                    break;
                                } case 8:{
                                    tile.setItem(new Item(ItemType.Acorns));
                                    break;
                                } case 9:{
                                    tile.setItem(new Item(ItemType.MapleSeed));
                                    break;
                                } case 10:{
                                    tile.setItem(new Item(ItemType.PineCones));
                                    break;
                                } case 11:{
                                    tile.setItem(new Item(ItemType.MahoganySeed));
                                    break;
                                } case 12:{
                                    tile.setItem(new Item(ItemType.MushroomTreeSeed));
                                    break;
                                }
                            }
                        }
                    }
                } else if (tile.getType().equals(TileType.Stone)) {
                    if(tile.getResource() == null){
                    int rand = RandomNumber.getRandomNumber();
                        switch (rand%100){
                            case 0:{
                                tile.setItem(new Item(ItemType.Quartz));
                                break;
                            }
                            case 1:{
                                tile.setItem(new Item(ItemType.EarthCrystal));
                                break;
                            }
                            case 2:{
                                tile.setItem(new Item(ItemType.FrozenTear));
                                break;
                            }
                            case 3:{
                                tile.setItem(new Item(ItemType.FireQuartz));
                                break;
                            }
                            case 4:{
                                tile.setItem(new Item(ItemType.Emerald));
                                break;
                            }
                            case 5:{
                                tile.setItem(new Item(ItemType.Aquamarine));
                                break;
                            }
                            case 6:{
                                tile.setItem(new Item(ItemType.Ruby));
                                break;
                            }
                            case 7:{
                                tile.setItem(new Item(ItemType.Amethyst));
                                break;
                            }
                            case 8:{
                                tile.setItem(new Item(ItemType.Topaz));
                                break;
                            }
                            case 9:{
                                tile.setItem(new Item(ItemType.Jade));
                                break;
                            }
                            case 10:{
                                tile.setItem(new Item(ItemType.Diamond));
                                break;
                            }
                            case 11:{
                                tile.setItem(new Item(ItemType.PrismaticShard));
                                break;
                            }
                            case 12:{
                                tile.setItem(new Item(ItemType.Copper));
                                break;
                            }
                            case 13:{
                                tile.setItem(new Item(ItemType.Iron));
                                break;
                            }
                            case 14:{
                                tile.setItem(new Item(ItemType.Gold));
                                break;
                            }
                            case 15:{
                                tile.setItem(new Item(ItemType.Iridium));
                                break;
                            }
                            case 16:{
                                tile.setItem(new Item(ItemType.Coal));
                            }

                        }
                    }
                }
            }
        }
    }

    private void spawnRandomResourceOnMap(){
        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                if(tile.getType().equals(TileType.Grass)){
                    int rand = RandomNumber.getRandomNumber();
                    switch (rand%20){
                        case 0:{
                            tile.setResource(new Tree(TreeType.Oak));
                            break;
                        } case 1:{
                            tile.setResource(new Tree(TreeType.ApricotTree));
                            break;
                        } case 2:{
                            tile.setResource(new Tree(TreeType.CherryTree));
                            break;
                        }
                    }

                } else if (tile.getType().equals(TileType.Stone)) {
                    int rand = RandomNumber.getRandomNumber();
                    if(rand % 3 == 0) {
                        tile.setResource(new Stone(StoneType.Coal));
                        int chance = RandomNumber.getRandomNumber() % 100;

                        if(chance < 15) {
                            tile.setResource(new Stone(StoneType.Quartz));
                        } else if (chance < 20) {
                            tile.setResource(new Stone(StoneType.Copper));
                        } else if (chance < 35) {
                            tile.setResource(new Stone(StoneType.Iron));
                        } else if(chance < 40) {
                            tile.setResource(new Stone(StoneType.EarthCrystal));
                        } else if (chance < 50) {
                            tile.setResource(new Stone(StoneType.Gold));
                        } else if(chance < 55) {
                            tile.setResource(new Stone(StoneType.FrozenTear));
                        } else if(chance < 60) {
                            tile.setResource(new Stone(StoneType.FireQuartz));
                        } else if(chance < 70) {
                            tile.setResource(new Stone(StoneType.Amethyst));
                        } else if(chance < 75) {
                            tile.setResource(new Stone(StoneType.Topaz));
                        } else if(chance < 80) {
                            tile.setResource(new Stone(StoneType.Jade));
                        } else if(chance < 85) {
                            tile.setResource(new Stone(StoneType.Aquamarine));
                        } else if(chance < 90) {
                            tile.setResource(new Stone(StoneType.Emerald));
                        } else if(chance < 93) {
                            tile.setResource(new Stone(StoneType.Ruby));
                        } else if(chance < 97) {
                            tile.setResource(new Stone(StoneType.Diamond));
                        } else if(chance < 99) {
                            tile.setResource(new Stone(StoneType.Iridium));
                        } else {
                            tile.setResource(new Stone(StoneType.PrismaticShard));
                        }
                    }
                }

            }
        }
    }

    public List<Character> getAllCharacters() {
        return allCharacters;
    }
}

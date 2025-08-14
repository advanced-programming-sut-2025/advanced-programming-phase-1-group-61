package models;

import io.github.camera.Main;
import models.NPC.NPC;
import models.animal.Animal;
import models.building.Building;
import models.building.Shop;
import models.character.Character;
import models.date.Date;
import models.enums.*;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;
import models.resource.*;
import models.shops.*;
import models.workBench.WorkBench;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {


    private int id;
    private Map map;
    private List<Character> allCharacters;
    private Date date;
    private ArrayList<Shop> shops = new ArrayList<>();
    private List<NPC> npcList = new ArrayList<>();
    private List<ShippingBin> shippingBins = new ArrayList<>();
    private ArrayList<WorkBench> workBenches =new ArrayList<>();

    public Game() {
    }

    public Game(Map map, List<Character> characters) {
        id = Main.getApp().getNewGameId();
        this.map = map;
        for (int i = 0; i < map.getTiles().length; i++) {
            for (int j = 0; j < map.getTiles()[0].length; j++) {
                if(map.getTiles()[i][j].getResource() instanceof ShippingBin shippingBin){
                    shippingBins.add(shippingBin);
                }
                if(map.getTiles()[i][j].getType().equals(TileType.RobinSpawnPoint)){
                    npcList.add(new NPC(NpcInfo.Robin , NpcDialog.Robin ,characters , j,i ));
                    System.out.println("robin");
                } else if (map.getTiles()[i][j].getType().equals(TileType.LiaSpawnPoint)) {
                    npcList.add(new NPC(NpcInfo.Lia , NpcDialog.Lia , characters , j , i));
                    System.out.println("lia");
                } else if (map.getTiles()[i][j].getType().equals(TileType.Abigail)) {
                    npcList.add(new NPC(NpcInfo.Abigail, NpcDialog.Abigail, characters, j, i));
                    System.out.println("Abigail");
                } else if (map.getTiles()[i][j].getType().equals(TileType.Harvi)) {
                    System.out.println("Harvi");
                    npcList.add(new NPC(NpcInfo.Harvi , NpcDialog.Harvi , characters , j ,i ));
                } else if (map.getTiles()[i][j].getType().equals(TileType.Sebastian)) {
                    System.out.println("sebastian");
                    npcList.add(new NPC(NpcInfo.Sebastian , NpcDialog.Sebastian , characters , j,i));
                }
            }
        }
        this.allCharacters = characters;
        this.date = new Date();
        spawnRandomResourceOnMap();
        spawnRandomItemsOnMap();
        doAfterMapBuilt();
    }

    public ArrayList<WorkBench> getWorkBenches() {
        return workBenches;
    }

    public void changeDayActivities() {
        handleWeatherBeforeDayChange();
        int count = 0;
        spawnRandomItemsOnMap();
        date.setHour(9);
        ArrayList<Shop> shops=this.getAllShops();
        for(Shop shop:shops) shop.restoreStocks();
        WeatherState weatherState = map.getWeather().getState();
        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
               Resource resource = tile.getResource();
               if(resource == null){
                   continue;
               }
               if(resource instanceof Crop crop){
                   crop.dayCycleForCrops(weatherState , tile);
                   count++;
               } else if (resource instanceof Tree tree) {
                   tree.dayCycleForTrees();
               } else if (resource instanceof Building b) {
                   for (Animal animal : b.getAnimalList()) {
                       animal.changeDayActivity();
                   }
               }
            }
        }
        List<Character> characters=this.getAllCharacters();
        for(Character character:characters) character.setBuff(null);
        NPC.changeDayActivities();
        if(weatherState.equals(WeatherState.Storm)){
            map.getWeather().lightning(RandomNumber.getRandomNumberWithBoundaries(0,70 ),
                    RandomNumber.getRandomNumberWithBoundaries(0 , 40));
        }
        for (Character character : allCharacters) {
            character.setFainted(false);
            character.setEnergy(150);
            character.getShippingBin().changeDayActivity();
        }
        if(count >= 16){
            map.getWeather().crowAttack(RandomNumber.getRandomNumberWithBoundaries(0,70 ),
                    RandomNumber.getRandomNumberWithBoundaries(0 , 40));
        }
        for (ShippingBin shippingBin : shippingBins) {
            shippingBin.changeDayActivity();
        }
    }


    public Character getCharacterByID(int id) {
        for(Character character:allCharacters) {
            if(character.getUserId()==id) return character;
        }
        return null;
    }



    public int getId() {
        return id;
    }

    public Character getCurrentCharacter() {
        for (Character character : allCharacters) {
            if(character.getUserId() == Main.getApp().getLoggedInUser().getId()){
                return character;
            }
        }
        return null;
    }

    public Character getCharacterByTurnNumber(int turn) {
        try {
            return allCharacters.get(turn);
        } catch (Exception e) {
            return null;
        }
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

    public ArrayList<Shop> getAllShops() {
        return shops;
    }

    public Character getCharachterByUserId(int id){
        for (Character character : allCharacters) {
            if(character.getUserId() == id){
                return character;
            }
        }
        return null;
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
                                    tile.setItem(new Item(ItemType.Moral));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.SalmonBerry));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.SpringOnion));
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
                                    tile.setItem(new Item(ItemType.CoffeeBean));
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


    public Shop getShopByShopType(ShopType type){
        for (Shop shop : shops) {
            if(shop.getType().equals(type)){
                return shop;
            }
        }
        return null;
    }

    private void spawnRandomResourceOnMap(){
        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                if(tile.getType().equals(TileType.Grass)){
                    int rand = RandomNumber.getRandomNumber();
                    switch (rand%20){
                        case 0:{
                            Tree tree = new Tree(TreeType.Oak , tile.getX() , tile.getY());
                            tree.setTreeStage(4);
                            tree.setTreeAge(28);
                            tile.setResource(tree);
                            break;
                        } case 1:{
                            Tree tree = new Tree(TreeType.ApricotTree, tile.getX() , tile.getY());
                            tree.setTreeStage(4);
                            tree.setTreeAge(28);
                            tile.setResource(tree);
                            break;
                        } case 2:{
                            Tree tree = new Tree(TreeType.CherryTree, tile.getX() , tile.getY());
                            tree.setTreeStage(4);
                            tree.setTreeAge(28);
                            tile.setResource(tree);
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

    public ArrayList<Shop> getShops() {
        return shops;
    }
    private void doAfterMapBuilt() {
        Set<ShopType> createdShopTypes = new HashSet<>();

        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                TileType type = tile.getType();
                if (type.equals(TileType.Carpenter) && !createdShopTypes.contains(ShopType.Carpenter)) {
                    shops.add(new Carpenter("Carpenter", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.Carpenter);
                } else if (type.equals(TileType.BlackSmith) && !createdShopTypes.contains(ShopType.BlackSmith)) {
                    BlackSmith blackSmith=new BlackSmith("BlackSmith", tile.getX(), tile.getY());
                    shops.add(blackSmith);
                    createdShopTypes.add(ShopType.BlackSmith);
                } else if (type.equals(TileType.FishShop) && !createdShopTypes.contains(ShopType.FishShop)) {
                    shops.add(new FishShop("FishShop", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.FishShop);
                } else if (type.equals(TileType.JojaMart) && !createdShopTypes.contains(ShopType.JojaMart)) {
                    shops.add(new JojaMart("JojaMart", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.JojaMart);
                } else if (type.equals(TileType.Marnie) && !createdShopTypes.contains(ShopType.Marnie)) {
                    shops.add(new Marnie("Marnie", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.Marnie);
                } else if (type.equals(TileType.Pierre) && !createdShopTypes.contains(ShopType.Pierre)) {
                    shops.add(new Pierre("Pierre", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.Pierre);
                } else if (type.equals(TileType.StarDrop) && !createdShopTypes.contains(ShopType.StarDrop)) {
                    shops.add(new StarDrop("StarDrop", tile.getX(), tile.getY()));
                    createdShopTypes.add(ShopType.StarDrop);
                }
            }
        }
    }

    public void updateCharacter(int id, Character newCharacter) {
        for (int i = 0; i < allCharacters.size(); i++) {
            Character character = allCharacters.get(i);
            if (character.getUserId() == id) {
                allCharacters.set(i, newCharacter);
                return;
            }
        }
    }



    public List<NPC> getNpcList() {
        return npcList;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }

    public void setNpcList(List<NPC> npcList) {
        this.npcList = npcList;
    }

    public void setShippingBins(List<ShippingBin> shippingBins) {
        this.shippingBins = shippingBins;
    }

    public void setWorkBenches(ArrayList<WorkBench> workBenches) {
        this.workBenches = workBenches;
    }

}

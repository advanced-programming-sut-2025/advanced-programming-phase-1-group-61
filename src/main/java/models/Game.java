package models;

import models.character.Character;
import models.date.Date;
import models.enums.ItemType;
import models.enums.Season;
import models.enums.TileType;
import models.enums.WeatherState;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;
import models.resource.Resource;

import java.util.List;
import java.util.Random;

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
    }


    public void changeDayActivities() {
        handleWeatherBeforeDayChange();
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

    private void spawnRandomObjectsOnMap(){
        for (Tile[] tiles : map.getTiles()) {
            for (Tile tile : tiles) {
                if(tile.getType().equals(TileType.grass)){
                    if(tile.getResource() == null){
                        int rand = RandomNumber.getRandomNumber();
                        if(date.getSeason().equals(Season.Spring)){
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.commonMushroom));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.daffodil));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.dandelion));
                                    break;
                                } case 3:{
                                    tile.setItem(new Item(ItemType.leek));
                                    break;
                                } case 4:{
                                    tile.setItem(new Item(ItemType.moral));
                                    break;
                                } case 5:{
                                    tile.setItem(new Item(ItemType.salmonBerry));
                                    break;
                                } case 6:{
                                    tile.setItem(new Item(ItemType.springOnion));
                                    break;
                                } case 7:{
                                    tile.setItem(new Item(ItemType.wildHorseradish));
                                    break;
                                }
                            }
                        } else if (date.getSeason().equals(Season.Summer)) {
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.fiddleHead));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.grape));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.redMushroom));
                                    break;
                                } case 3:{
                                    tile.setItem(new Item(ItemType.spiceBerry));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.sweetPea));
                                }
                            }
                        } else if (date.getSeason().equals(Season.Fall)) {
                            switch (rand%100) {
                                case 0: {
                                    tile.setItem(new Item(ItemType.blackberry));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.chanterelle));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.hazelnut));
                                    break;
                                }
                                case 3:{
                                    tile.setItem(new Item(ItemType.purpleMushroom));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.wildPlum));
                                    break;
                                }
                            }
                        } else if (date.getSeason().equals(Season.Winter)) {
                            switch (rand%100){
                                case 0:{
                                    tile.setItem(new Item(ItemType.crocus));
                                    break;
                                }
                                case 1:{
                                    tile.setItem(new Item(ItemType.crystalFruit));
                                    break;
                                }
                                case 2:{
                                    tile.setItem(new Item(ItemType.holly));
                                    break;
                                }
                                case 3:{
                                    tile.setItem(new Item(ItemType.snowYam));
                                    break;
                                }
                                case 4:{
                                    tile.setItem(new Item(ItemType.winterRoot));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

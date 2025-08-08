package models.map;

import io.github.camera.Main;
import models.App;
import models.Game;
import models.RandomNumber;
import models.enums.ItemType;
import models.enums.Season;
import models.enums.TileType;
import models.enums.WeatherState;
import models.resource.Crop;

public class Weather {
   private WeatherState state;
   private WeatherState tomorrowWeatherState ;
   private WeatherState cheatedWeatherState ;

    public Weather(WeatherState state ) {
        this.state = state;
        this.tomorrowWeatherState = state;
        this.cheatedWeatherState = null;
    }

    public WeatherState getState() {
        return state;
    }

    public WeatherState getTomorrowWeatherState() {
        return tomorrowWeatherState;
    }

    public void setState(WeatherState state) {
        this.state = state;
    }
    public void changeTomorrowWeatherState(){
        Game game = Main.getApp().getCurrentGame();
        Season season = game.getDate().getSeason();
        if(cheatedWeatherState ==null){
            if(season.equals(Season.Winter)){
                int randomNumber = RandomNumber.getRandomNumberWithBoundaries(0,2);
                switch (randomNumber%2){
                    case 0: {
                        tomorrowWeatherState = WeatherState.Sunny;
                        break;
                    }
                    case 1:{
                        tomorrowWeatherState = WeatherState.Snow;
                        break;
                    }
                }
            } else{
                int randomNumber = RandomNumber.getRandomNumberWithBoundaries(0,3);
                switch (randomNumber%3){
                    case 0:{
                        tomorrowWeatherState = WeatherState.Sunny;
                        break;
                    }
                    case 1:{
                        tomorrowWeatherState = WeatherState.Rain;
                        break;
                    }
                    case 2: {
                        tomorrowWeatherState = WeatherState.Storm;
                        break;
                    }
                }
            }
        }else {
            tomorrowWeatherState = cheatedWeatherState;
            cheatedWeatherState = null;
        }
    }
    public void crowAttack(int x, int y) {
        Tile tile = Main.getApp().getCurrentGame().getMap().getTileByCordinate(x, y);
        if (tile == null) {
            return;
        }

        if (tile.getResource() != null && !tile.getType().equals(TileType.GreenHouse) && !tile.getType().equals(TileType.CabinFloor)) {
            Map map = Main.getApp().getCurrentGame().getMap();


            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            for (int[] dir : directions) {
                Tile adjacentTile = map.getTileByCordinate(x + dir[0], y + dir[1]);
                if (adjacentTile != null && adjacentTile.getItem() != null && adjacentTile.getItem().equals(ItemType.Scarecrow)) {
                    return;
                }
            }

            if (tile.getResource() instanceof Crop && !tile.getType().equals(TileType.GreenHouse)) {
                tile.setResource(null);
            }
        }
    }

    public void lightning(int x, int y){
       Tile tile = Main.getApp().getCurrentGame().getMap().getTileByCordinate(x,y);
       if(tile == null){
           return;
       }
       if(tile.getResource() != null && !tile.getType().equals(TileType.GreenHouse) &&!tile.getType().equals(TileType.CabinFloor)){
           tile.setResource(null);
       }
    }

    public void setCheatedWeatherState(WeatherState cheatedWeatherState) {
        this.cheatedWeatherState = cheatedWeatherState;
    }
}

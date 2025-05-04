package models.map;

import models.App;
import models.Game;
import models.RandomNumber;
import models.enums.Season;
import models.enums.WeatherState;

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
        Game game = App.getCurrentGame();
        Season season = game.getDate().getSeason();
        if(cheatedWeatherState ==null){
            if(season.equals(Season.Winter)){
                int randomNumber = RandomNumber.getRandomNumberWithBoundaries(0,2);
                switch (randomNumber%2){
                    case 0: tomorrowWeatherState = WeatherState.Sunny;
                    case 1: tomorrowWeatherState = WeatherState.Snow;
                }
            } else{
                int randomNumber = RandomNumber.getRandomNumberWithBoundaries(0,3);
                switch (randomNumber%3){
                    case 0: tomorrowWeatherState = WeatherState.Sunny;
                    case 1: tomorrowWeatherState = WeatherState.Rain;
                    case 2: tomorrowWeatherState = WeatherState.Storm;
                }
            }
        }else {
            tomorrowWeatherState = cheatedWeatherState;
            cheatedWeatherState = null;
        }
    }

    public void lightning(int x, int y){
        //todo
    }

    public void setCheatedWeatherState(WeatherState cheatedWeatherState) {
        this.cheatedWeatherState = cheatedWeatherState;
    }
}

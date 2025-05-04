package models.map;

import models.date.Date;
import models.enums.WeatherState;

public class Weather {
   private WeatherState state;
   private WeatherState tomarowWeatherState ;

    public Weather(WeatherState state ) {
        this.state = state;
        tomarowWeatherState = state;
    }

    public WeatherState getState() {
        return state;
    }

    public void setState(WeatherState state) {
        this.state = state;
    }

    public void lightning(int x, int y){
        //todo
    }
}

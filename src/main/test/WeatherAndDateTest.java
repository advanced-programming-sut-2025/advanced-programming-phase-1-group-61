
import static org.junit.jupiter.api.Assertions.*;
import models.App;
import models.Game;
import models.character.Character;
import models.enums.Season;
import models.enums.WeatherState;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import models.map.Weather;
import models.date.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class WeatherAndDateTest {

    private Weather weather;
    private Date date;

    @BeforeEach
    void setUp() {
        List<Character> characterList = java.util.List.of(new Character(1) ,new Character(2));
        Map map = MapBuilder.buildFullMap(1,1,1,1 ,characterList);
        Game game =new Game(map ,characterList );
        App.setCurrentGame(game.getId());
        App.addGame(game);
        weather = new Weather(WeatherState.Sunny);
        date = new Date();
    }

    @Test
    void testInitialWeatherState() {
        assertEquals(WeatherState.Sunny, weather.getState());
        assertEquals(WeatherState.Sunny, weather.getTomorrowWeatherState());
    }

    @Test
    void testChangeTomorrowWeatherState() {
        date.changeSeason(Season.Winter);
        weather.changeTomorrowWeatherState();
        assertTrue(weather.getTomorrowWeatherState().equals(WeatherState.Sunny) || weather.getTomorrowWeatherState().equals(WeatherState.Snow));

        date.changeSeason(Season.Spring);
        weather.changeTomorrowWeatherState();
        assertTrue(weather.getTomorrowWeatherState() == WeatherState.Sunny || weather.getTomorrowWeatherState() == WeatherState.Rain || weather.getTomorrowWeatherState() == WeatherState.Storm);
    }

    @Test
    void testCheatedWeatherState() {
        weather.setCheatedWeatherState(WeatherState.Storm);
        weather.changeTomorrowWeatherState();
        assertEquals(WeatherState.Storm, weather.getTomorrowWeatherState());
    }

    @Test
    void testInitialDateState() {
        assertEquals(9, date.getHour());
        assertEquals(Season.Spring, date.getSeason());
    }

    @Test
    void testChangeDayAndSeason() {
        date.changeDay(30);
        assertEquals(Season.Summer, date.getSeason());

        date.changeDay(60);
        assertEquals(Season.Winter, date.getSeason());
    }

    @Test
    void testIncreaseTime() {
        date.increaseTime(15);
        assertEquals(24, date.getHour());
    }
}

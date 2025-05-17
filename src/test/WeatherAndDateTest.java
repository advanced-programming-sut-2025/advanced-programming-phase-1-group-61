import static org.junit.jupiter.api.Assertions.*;
import models.App;
import models.Game;
import models.enums.Season;
import models.enums.WeatherState;
import models.map.Weather;
import models.date.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherAndDateTest {

    private Weather weather;
    private Date date;

    @BeforeEach
    void setUp() {
        App.setCurrentGame(App.getCurrentGame().getId());
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
        assertTrue(weather.getTomorrowWeatherState() == WeatherState.Sunny || weather.getTomorrowWeatherState() == WeatherState.Snow);

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

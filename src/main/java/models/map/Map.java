package models.map;

import models.workBench.WorkBench;

import java.util.ArrayList;

public class Map {
    private Tile[][] tiles;
   private Weather weather;

    public Map(Tile[][] tiles, Weather weather) {
        this.tiles = tiles;
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}

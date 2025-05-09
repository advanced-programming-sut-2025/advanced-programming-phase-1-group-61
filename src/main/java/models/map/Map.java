package models.map;

import models.workBench.WorkBench;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Map {
    private Tile[][] tiles;
   private Weather weather;
   private List<Integer> xSpawnPoints;
   private List<Integer> ySpawnPoints;

    public List<Integer> getXSpawnPoints() {
        return xSpawnPoints;
    }

    public List<Integer> getYSpawnPoints() {
        return ySpawnPoints;
    }

    public void setXSpawnPoints(List<Integer> xSpawnPoints) {
        this.xSpawnPoints = xSpawnPoints;
    }

    public void setYSpawnPoints(List<Integer> ySpawnPoints) {
        this.ySpawnPoints = ySpawnPoints;
    }

    public Map(Tile[][] tiles, Weather weather) {
        this.tiles = tiles;
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getHeightSize(){
        return tiles.length;
    }

    public int getWidthSize(){
        return tiles[0].length;
    }

}

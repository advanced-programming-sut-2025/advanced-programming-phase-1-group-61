package models.map;

import models.App;
import models.NPC.NPC;
import models.enums.NpcDialog;
import models.enums.NpcInfo;

import java.util.List;

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

    public Map() {
    }

    public Weather getWeather() {
        return weather;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTileByCordinate(int x , int y){
        if (x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length){
            return null;
        }
        return tiles[y][x];
    }

    public int getHeightSize(){
        return tiles.length;
    }

    public int getWidthSize(){
        return tiles[0].length;
    }

}

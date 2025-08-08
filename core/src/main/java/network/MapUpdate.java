package network;

import models.map.Map;

public class MapUpdate {
    private Map map;
    private int gameId;

    public MapUpdate(Map map, int gameId) {
        this.map = map;
        this.gameId = gameId;
    }

    public MapUpdate() {
    }

    public Map getMap() {
        return map;
    }

    public int getGameId() {
        return gameId;
    }
}

package models.map;

import models.enums.TileType;
import models.resource.Resource;

public class Tile {
    private int x;
    private int y;
    private TileType type;
    private Resource resource;

    public Tile(int x, int y, TileType type, Resource resource) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.resource = resource;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}

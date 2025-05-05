package models.map;

import models.Item;
import models.enums.TileType;
import models.resource.Resource;

public class Tile {
    private int x;
    private int y;
    private TileType type;
    private Resource resource;
    private boolean collisionOn;
    private int ownerId ;
    private Item item;

    public Tile(int x, int y, TileType type, Resource resource, int ownerId) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.resource = resource;
        this.collisionOn = type.isCollisionOn();
        this.ownerId = ownerId;
        this.item = null;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

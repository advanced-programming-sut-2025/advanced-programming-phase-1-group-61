package models.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import models.AssetManager;
import models.CollisionRect;
import models.Item;
import models.enums.TileType;
import models.resource.Resource;

import java.util.HashSet;

public class Tile {
    private int x;
    private int y;
    private TileType type;
    private Resource resource;
    private boolean collisionOn;
    private int ownerId ;
    private Item item;
    private transient Sprite sprite;
    private CollisionRect collisionRect;

    public Tile(int x, int y, TileType type, Resource resource, int ownerId) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.resource = resource;
        this.collisionOn = type.isCollisionOn();
        this.ownerId = ownerId;
        this.item = null;
        sprite = new Sprite(type.getTexture());
        sprite.setX(x);
        sprite.setY(y);
        sprite.setSize(AssetManager.getTileSize(),AssetManager.getTileSize());
        int tileSize = AssetManager.getTileSize();
        collisionRect = new CollisionRect(x*tileSize,y*tileSize,tileSize , tileSize);
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
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

    public int getOwnerId() {
        return ownerId;
    }

    public Sprite getSprite(){
        if(sprite == null){
            sprite = new Sprite(type.getTexture());
            sprite.setX(x);
            sprite.setY(y);
            int tileSize = AssetManager.getTileSize();
            sprite.setSize(tileSize,tileSize);
        }
        return sprite;
    }

    public int getX() {
        return x*AssetManager.getTileSize();
    }

    public int getY() {
        return y*AssetManager.getTileSize();
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
}

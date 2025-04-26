package models.enums;

public enum TileType {
    soil(false),
    water(true),
    grass(false);
    private boolean collisionOn;

    TileType(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }
}

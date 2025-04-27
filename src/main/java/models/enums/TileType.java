package models.enums;

public enum TileType {
    soil(false,1),
    water(true,2),
    grass(false,0),
    cabinFloor(false , 4),
    cabinWall(true , 6),
    stone(false , 5);
    private boolean collisionOn;
    private int typeNum;

    TileType(boolean collisionOn,int typeNum) {
        this.typeNum = typeNum;
        this.collisionOn = collisionOn;
    }

    public static TileType getTypeByNumber(int typeNum) {
        for (TileType type : TileType.values()) {
            if (type.getTypeNum() == typeNum) {
                return type;
            }
        }
        return null;
    }


    public int getTypeNum() {
        return typeNum;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }
}

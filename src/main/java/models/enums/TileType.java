package models.enums;

public enum TileType {
    grass(false,"0"),
    soil(false,"1"),
    water(true,"2"),
    brokenGreenHouse(true , "3"),
    brokenGreenHouseWall(true , "7"),
    cabinFloor(false , "4"),
    cabinWall(true , "6"),
    stone(false , "5"),
    door(false , "8"),
    building(true , "B");
    private boolean collisionOn;
    private String  typeNum;

    TileType(boolean collisionOn,String typeNum) {
        this.typeNum = typeNum;
        this.collisionOn = collisionOn;
    }

    public static TileType getTypeByNumber(String typeNum) {
        for (TileType type : TileType.values()) {
            if(type.getTypeNum().equals(typeNum)){
                return type;
            }
        }
        return null;
    }


    public String getTypeNum() {
        return typeNum;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }
}

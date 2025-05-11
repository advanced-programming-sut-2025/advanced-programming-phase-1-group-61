package models.enums;

public enum TileType {
    Grass(false,"0"),
    Soil(false,"1"),
    Water(true,"2"),
    BrokenGreenHouse(true , "3"),
    BrokenGreenHouseWall(true , "7"),
    CabinFloor(false , "4"),
    CabinWall(true , "6"),
    Stone(false , "5"),
    Door(false , "8"),
    Shop(true , "B"),
    GreenHouse(true , "C"),
    Barn(true , "D");
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

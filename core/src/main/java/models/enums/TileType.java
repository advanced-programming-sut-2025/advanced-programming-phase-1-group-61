package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
    Grass(false,"0",new Texture("grass.png")),
    Soil(false,"1" , new Texture("soil.png")),
    Water(true,"2",new Texture("water.png")),
    BrokenGreenHouse(true , "3",new Texture("dirt.png")),
    BrokenGreenHouseWall(true , "7",new Texture("dirt.png")),
    CabinFloor(false , "4",new Texture("dirt.png")),
    CabinWall(true , "6",new Texture("dirt.png")),
    Stone(false , "5",new Texture("dirt.png")),
    Door(false , "8",new Texture("dirt.png")),
    Shop(true , "B",new Texture("dirt.png")),
    GreenHouse(true , "C",new Texture("dirt.png")),
    Barn(true , "D",new Texture("dirt.png")),
    Carpenter(false , "E",new Texture("dirt.png")),
    BlackSmith(false , "F",new Texture("dirt.png")),
    FishShop(false , "G",new Texture("dirt.png")),
    JojaMart(false,"H",new Texture("dirt.png")),
    Marnie(false , "I",new Texture("dirt.png")),
    Pierre(false , "J",new Texture("dirt.png")),
    StarDrop(false , "K",new Texture("dirt.png")),
    ShopWall(true , "L",new Texture("dirt.png")),
    Path(false , "M",new Texture("dirt.png")),
    RobinSpawnPoint(false , "N",new Texture("dirt.png")),
    LiaSpawnPoint(false , "O",new Texture("dirt.png")),
    Abigail(false , "P",new Texture("dirt.png")),
    Harvi(false , "Q",new Texture("dirt.png")),
    Sebastian(false , "R",new Texture("dirt.png"));

    private boolean collisionOn;
    private String  typeNum;
    private Texture texture;

    TileType(boolean collisionOn, String typeNum, Texture texture) {
        this.collisionOn = collisionOn;
        this.typeNum = typeNum;
        this.texture = texture;
    }



    public static TileType getTypeByNumber(String typeNum) {
        for (TileType type : TileType.values()) {
            if(type.getTypeNum().equalsIgnoreCase(typeNum)){
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

    public Texture getTexture() {
        return texture;
    }
}

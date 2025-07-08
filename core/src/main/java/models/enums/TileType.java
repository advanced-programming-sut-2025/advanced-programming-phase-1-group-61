package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum TileType {
    Grass(false,"0",new Texture("Tiles/grass.png")),
    Soil(false,"1" , new Texture("Tiles/soil.png")),
    Water(true,"2",new Texture("Tiles/water.png")),
    BrokenGreenHouse(true , "3",new Texture("Tiles/brokenGreenHouseFloor.png")),
    BrokenGreenHouseWall(true , "7",new Texture("Tiles/brokenGreenHouseWall.png")),
    CabinFloor(false , "4",new Texture("Tiles/CabinFloor.png")),
    CabinWall(true , "6",new Texture("Tiles/cabinwall.png")),
    Stone(false , "5",new Texture("Tiles/stone.png")),
    Door(false , "8",new Texture("Tiles/CabinFloor.png")),
    Shop(true , "B",new Texture("Tiles/dirt.png")),
    GreenHouse(true , "C",new Texture("Tiles/greenHouseCabinFloor.png")),
    Barn(true , "D",new Texture("Tiles/dirt.png")),
    Carpenter(false , "E",new Texture("Tiles/Carpenter.png")),
    BlackSmith(false , "F",new Texture("Tiles/BlackSmith.png")),
    FishShop(false , "G",new Texture("Tiles/FishShop.png")),
    JojaMart(false,"H",new Texture("Tiles/JojaMart.png")),
    Marnie(false , "I",new Texture("Tiles/Marnie.png")),
    Pierre(false , "J",new Texture("Tiles/Pierre.png")),
    StarDrop(false , "K",new Texture("Tiles/StarDrop.png")),
    ShopWall(true , "L",new Texture("Tiles/grass.png")),
    Path(false , "M",new Texture("Tiles/dirt.png")),
    RobinSpawnPoint(false , "N",new Texture("Tiles/dirt.png")),
    LiaSpawnPoint(false , "O",new Texture("Tiles/dirt.png")),
    Abigail(false , "P",new Texture("Tiles/dirt.png")),
    Harvi(false , "Q",new Texture("Tiles/dirt.png")),
    Sebastian(false , "R",new Texture("Tiles/dirt.png"));

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

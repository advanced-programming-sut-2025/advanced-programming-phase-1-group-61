package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import models.App;

public enum TileType {
    Grass(false,"0","Tiles/grass.png"),
    Soil(false,"1" , "Tiles/soil.png"),
    Water(true,"2","Tiles/water.png"),
    BrokenGreenHouse(false , "3","Tiles/grass.png"),
    BrokenGreenHouseWall(false , "7","Tiles/grass.png"),
    CabinFloor(false , "4","Tiles/CabinFloor.png"),
    CabinWall(true , "6","Tiles/cabinwall.png"),
    Stone(false , "5","Tiles/stone.png"),
    Door(false , "8","Tiles/CabinFloor.png"),
    Shop(true , "B","Tiles/dirt.png"),
    GreenHouse(false , "C","Tiles/greenHouseCabinFloor.png"),
    Barn(true , "D","Tiles/dirt.png"),
    Carpenter(false , "E","Tiles/Carpenter.png"),
    BlackSmith(false , "F","Tiles/BlackSmith.png"),
    FishShop(false , "G","Tiles/FishShop.png"),
    JojaMart(false,"H","Tiles/JojaMart.png"),
    Marnie(false , "I","Tiles/Marnie.png"),
    Pierre(false , "J","Tiles/Pierre.png"),
    StarDrop(false , "K","Tiles/StarDrop.png"),
    ShopWall(true , "L","Tiles/grass.png"),
    Path(false , "M","Tiles/dirt.png"),
    RobinSpawnPoint(false , "N","Tiles/dirt.png"),
    LiaSpawnPoint(false , "O","Tiles/dirt.png"),
    Abigail(false , "P","Tiles/dirt.png"),
    Harvi(false , "Q","Tiles/dirt.png"),
    Sebastian(false , "R","Tiles/dirt.png"),
    fallGrass(false , "AA","Tiles/fallGrass.png"),
    snowyGrass(false,  "AB","Tiles/snowyGrass.png"),
    summerGrass(false , "AC" , "Tiles/summerGrass.png");

    private boolean collisionOn;
    private String  typeNum;
    private Texture texture;
    private String texturePath;

    TileType(boolean collisionOn, String typeNum , String texturePath) {
        this.collisionOn = collisionOn;
        this.typeNum = typeNum;
        this.texturePath = texturePath;
    }

    public static TileType getTypeByNumber(String typeNum) {
        for (TileType type : TileType.values()) {
            if(type.getTypeNum().equalsIgnoreCase(typeNum)){
                return type;
            }
        }
        return null;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public Texture getTexture() {
        if (texture == null) {
            try {
                texture = new Texture(texturePath);
            } catch (Exception e) {
                e.printStackTrace();
                texture = new Texture(Gdx.files.internal("Tiles/grass.png"));
            }
        }
        return texture;
    }
}

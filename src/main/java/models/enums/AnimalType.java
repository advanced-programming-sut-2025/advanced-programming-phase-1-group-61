package models.enums;

import models.building.Barn;
import models.building.Building;

public enum AnimalType {
    COW("","Barn",2,1500),
    DINOSAUR("","Coop",4,14000),
    DUCK("","Coop",1,1200),
    FISH("","Lake",0,0),
    GOAT("","Barn",1,4000),
    HEN("","Coop",1,800),
    PIG("","Barn",1,16000),
    RABBIT("","Coop",1,8000),
    SHEEP("","Barn",1,8000);
    private String texturePath;
    private String house;
    private int size=0;
    private int price;
    AnimalType(String texturePath, String house, int size,int price) {
        this.house = house;
        this.texturePath = texturePath;
        this.size = size;
        this.price = price;
    }
    public int getSize() {
        return size;
    }
    public String getTexturePath() {
        return texturePath;
    }
    public String getHouse() {
        return house;
    }
    public int getPrice() {
        return price;
    }
}

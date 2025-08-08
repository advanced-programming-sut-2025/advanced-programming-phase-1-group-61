package models.building;


import com.badlogic.gdx.graphics.Texture;
import io.github.camera.Main;
import models.AssetManager;
import models.enums.ShopType;
import models.resource.Resource;

public class Shop  {
    protected String owner;
    protected ShopType type;
    protected int x;
    protected int y;

    public Shop() {
    }

    public Shop(String name , int X, int Y, ShopType type) {
        this.type = type;
        this.x = X;
        this.y =Y;
    }
    public String getOwnerName() {
        return owner;
    }
    public String showAllProducts(){
        return "salam shop";
    }
    public String showAllAvailableProducts(){
        return "hello shop";
    }
    public String purchaseProduct(String product,int count){
        return "by shop";
    }
    public void restoreStocks(){
        return;
    }
    public int getOpenHour(){
        return 0;
    }
    public int getCloseHour(){
        return 0;
    }

    public void draw(){
        if(type.equals(ShopType.BlackSmith)){
            Main.getBatch().draw(type.getTexture() , getX()-AssetManager.getTileSize() , getY()-AssetManager.getTileSize() , AssetManager.getTileSize()*6 , AssetManager.getTileSize()*5);
        }else {
            Main.getBatch().draw(type.getTexture() , getX()-AssetManager.getTileSize() , getY()-AssetManager.getTileSize() , AssetManager.getTileSize()*5 , AssetManager.getTileSize()*5);
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public ShopType getType() {
        return type;
    }
}

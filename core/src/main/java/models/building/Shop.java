package models.building;


import com.badlogic.gdx.graphics.Texture;
import io.github.camera.Main;
import models.AssetManager;
import models.enums.ShopType;

public class Shop extends Building {
    protected String owner;
    protected ShopType type;
    public Shop( String name , int X, int Y,ShopType type) {
        super( name, X, Y);
        this.type = type;
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

    public ShopType getType() {
        return type;
    }
}

package models.shops;

import com.badlogic.gdx.graphics.Texture;
import models.enums.ToolType;
import models.tool.FishingPole;

public class ShopFishingPoleUpgrades {
    private  String upgradeName;
    private  int price;
    private  int limit;
    private  int requiredLevel;
    private int stock;
    private  String description;
    private  String displayName;

    public ShopFishingPoleUpgrades() {
    }

    public ShopFishingPoleUpgrades(String upgradeName, int price, int limit, int requiredLevel, String description, String displayName) {
        this.upgradeName = upgradeName;
        this.price = price;
        this.limit = limit;
        this.requiredLevel = requiredLevel;
        this.description = description;
        this.displayName = displayName;
        this.stock=limit;
    }
    public String getUpgradeName() {
        return upgradeName;
    }
    public int getPrice() {
        return price;
    }
    public int getLimit() {
        return limit;
    }
    public int getRequiredLevel() {
        return requiredLevel;
    }
    public String getDescription() {
        return description;
    }
    public int getStock() {
        return stock;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void restoreStock(){
        this.stock=limit;
    }
    public Texture getTextureByUpgradeName(){
        return ToolType.FishingPole.getTextureForLevel(upgradeName);
    }
}

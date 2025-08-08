package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum ShopType {

    Carpenter("ShopTextures/Carpenter.png"),
    FishShop("ShopTextures/FishShop.png"),
    BlackSmith("ShopTextures/BlackSmith.png"),
    JojaMart("ShopTextures/JojaMart.png"),
    Marnie("ShopTextures/Marnie.png"),
    Pierre("ShopTextures/Pierre.png"),
    StarDrop("ShopTextures/StarDrop.png");

    private Texture texture;
    private String texturePath;

    ShopType(String texture) {
        this.texturePath = texture;
    }

    public Texture getTexture() {
        if(texture == null){
            texture = new Texture(texturePath);
        }
        return texture;
    }
}

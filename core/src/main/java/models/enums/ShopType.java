package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum ShopType {

    Carpenter(new Texture("ShopTextures/Carpenter.png")),
    FishShop(new Texture("ShopTextures/FishShop.png")),
    BlackSmith(new Texture("ShopTextures/BlackSmith.png")),
    JojaMart(new Texture("ShopTextures/JojaMart.png")),
    Marnie(new Texture("ShopTextures/Marnie.png")),
    Pierre(new Texture("ShopTextures/Pierre.png")),
    StarDrop(new Texture("ShopTextures/StarDrop.png"));

    private final Texture texture;

    ShopType(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}

package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TrashcanType {
    PRIMARY(0,"Primary","Items/Trash_Can_Copper.png"),
    COPPER(15,"Copper","Items/Trash_Can_Copper.png"),
    IRON(30,"Iron","Items/Trash_Can_Steel.png"),
    GOLDEN(45,"Golden","Items/Trash_Can_Gold.png"),
    IRIDIUM(60,"Iridium","Items/Trash_Can_Iridium.png"),;
    private final int returnPercentage;
    private final String displayName;
    private final String texturePath;
    TrashcanType(int returnPercentage, String displayName,String texturePath) {
        this.returnPercentage = returnPercentage;
        this.displayName = displayName;
        this.texturePath = texturePath;
    }
    public int getReturnPercentage() {
        return returnPercentage;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getNextLevel() {
        return switch (this.displayName) {
            case "Primary" -> "Copper";
            case "Copper" -> "Iron";
            case "Iron" -> "Golden";
            case "Golden" -> "Iridium";
            default -> null;
        };
    }
    public Texture getTexture() {
        return new Texture(Gdx.files.internal(texturePath));
    }
}

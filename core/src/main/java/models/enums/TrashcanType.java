package models.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public enum TrashcanType {
    PRIMARY(0,"Primary","Items/Trash_Can_Copper.png"),
    COPPER(0.15f,"Copper","Items/Trash_Can_Copper.png"),
    IRON(0.30f,"Iron","Items/Trash_Can_Steel.png"),
    GOLDEN(0.45f,"Golden","Items/Trash_Can_Gold.png"),
    IRIDIUM(0.60f,"Iridium","Items/Trash_Can_Iridium.png"),;
    private final float returnPercentage;
    private final String displayName;
    private final String texturePath;
    TrashcanType(float returnPercentage, String displayName,String texturePath) {
        this.returnPercentage = returnPercentage;
        this.displayName = displayName;
        this.texturePath = texturePath;
    }
    public float getReturnPercentage() {
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

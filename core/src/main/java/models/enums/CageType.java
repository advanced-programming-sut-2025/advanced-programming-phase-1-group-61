package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum CageType {
    Coop(6,3,"Houses 4 coop-dwelling animals.","Coop","Items/Cages.png"),
    BigCoop(6,3,"Houses 8 coop-dwelling animals. Unlocks ducks.","Big Coop","Items/Cages.png"),
    DeluxCoop(6,3,"Houses 12 coop-dwelling animals. Unlocks rabbits.","Deluxe Coop","Items/Cages.png"),
    Barn(7,4,"Houses 4 barn-dwelling animals.","Barn","Items/Cages.png"),
    BigBarn(7,4,"Houses 8 barn-dwelling animals. Unlocks goats.","Big Barn","Items/Cages.png"),
    DeluxeBarn(7,4,"Houses 12 barn-dwelling animals. Unlocks sheep and pigs.","Deluxe Barn","Items/Cages.png"),
    Well(3,3,"Provides a place for you to refill your watering can.","Well","Items/Cages.png"),
    ShippingBin(1,1,"Items placed in it will be included in the nightly shipment.","Shipping Bin","Items/Cages.png"),;
    private final int width;
    private final int height;
    private final String description;
    private final String displayName;
    private final String internalPath;
    private Texture texture;
    CageType(int width, int height,String description,String displayName,String internalPath) {
        this.width = width;
        this.height = height;
        this.description = description;
        this.displayName = displayName;
        this.internalPath = internalPath;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public String getDescription() {
        return description;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static CageType getCageType(String type) {
        try {
            return CageType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public Texture getTexture() {
        if(texture == null){
            texture = new Texture(internalPath);
        }
        return texture;
    }
}

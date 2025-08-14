package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum CageType {
    Coop("Coop","Items/Cages.png",ItemType.Coop,3),
    Barn("Barn","Items/Cages.png" , ItemType.Barn,2);

    private final String displayName;
    private final String internalPath;
    private Texture texture;
    private ItemType item;
    private int size;

    CageType(String displayName,String internalPath ,ItemType type , int size) {
        this.item = type;
        this.displayName = displayName;
        this.internalPath = internalPath;
        this.size = size;
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

    public ItemType getItem() {
        return item;
    }

    public int getSize() {
        return size;
    }
}

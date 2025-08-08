package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum StoneType {
    Gold(10,ItemType.Gold,"Stones/Gold_Node.png"),
    Iron(30,ItemType.Iron ,"Stones/Iron_Node.png"),
    Quartz(20,ItemType.Quartz,"Stones/Quartz.png"),
    EarthCrystal(20,ItemType.EarthCrystal,"Stones/Earth_Crystal.png"),
    FrozenTear(7,ItemType.FrozenTear,"Stones/Frozen_Tear.png"),
    FireQuartz(5,ItemType.FireQuartz,"Stones/Fire_Quartz.png"),
    Emerald(2,ItemType.Emerald,"Stones/Emerald_Node.png"),
    Aquamarine(3,ItemType.Aquamarine,"Stones/Aquamarine_Node.png"),
    Ruby(2,ItemType.Ruby,"Stones/Ruby_Node.png"),
    Amethyst(5,ItemType.Amethyst,"Stones/Amethyst_Node.png"),
    Topaz(6,ItemType.Topaz,"Stones/Topaz_Node.png"),
    Jade(3,ItemType.Jade,"Stones/Jade_Node.png"),
    Diamond(1,ItemType.Diamond,"Stones/Diamond_Node.png"),
    PrismaticShard(1,ItemType.PrismaticShard,"Stones/Star_Shards.png"),
    Copper(40,ItemType.Copper,"Stones/Copper_Node.png"),
    Iridium(5,ItemType.Iridium,"Stones/Iridium_Node.png"),
    Coal(20,ItemType.Coal,"Stones/Coal_Node.png");
    private int harvestAbleAmount;
    private ItemType oreType;
    private Texture texture;
    private String texturePath;
    StoneType(int harvestAbleAmount, ItemType oreType,String texture) {
        this.harvestAbleAmount = harvestAbleAmount;
        this.oreType = oreType;
        this.texturePath = texture;
    }

    public int getHarvestAbleAmount() {
        return harvestAbleAmount;
    }

    public ItemType getOreType() {
        return oreType;
    }

    public Texture getTexture() {
        if(texture == null){
            texture = new Texture(texturePath);
        }
        return texture;
    }
}

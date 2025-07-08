package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum StoneType {
    Gold(10,ItemType.Gold,new Texture("Stones/Gold_Node.png")),
    Iron(30,ItemType.Iron ,new Texture("Stones/Iron_Node.png")),
    Quartz(20,ItemType.Quartz,new Texture("Stones/Quartz.png")),
    EarthCrystal(20,ItemType.EarthCrystal,new Texture("Stones/Earth_Crystal.png")),
    FrozenTear(7,ItemType.FrozenTear,new Texture("Stones/Frozen_Tear.png")),
    FireQuartz(5,ItemType.FireQuartz,new Texture("Stones/Fire_Quartz.png")),
    Emerald(2,ItemType.Emerald,new Texture("Stones/Emerald_Node.png")),
    Aquamarine(3,ItemType.Aquamarine,new Texture("Stones/Aquamarine_Node.png")),
    Ruby(2,ItemType.Ruby,new Texture("Stones/Ruby_Node.png")),
    Amethyst(5,ItemType.Amethyst,new Texture("Stones/Amethyst_Node.png")),
    Topaz(6,ItemType.Topaz,new Texture("Stones/Topaz_Node.png")),
    Jade(3,ItemType.Jade,new Texture("Stones/Jade_Node.png")),
    Diamond(1,ItemType.Diamond,new Texture("Stones/Diamond_Node.png")),
    PrismaticShard(1,ItemType.PrismaticShard,new Texture("Stones/Star_Shards.png")),
    Copper(40,ItemType.Copper,new Texture("Stones/Copper_Node.png")),
    Iridium(5,ItemType.Iridium,new Texture("Stones/Iridium_Node.png")),
    Coal(20,ItemType.Coal,new Texture("Stones/Coal_Node.png"));
    private int harvestAbleAmount;
    private ItemType oreType;
    private Texture texture;
    StoneType(int harvestAbleAmount, ItemType oreType,Texture texture) {
        this.harvestAbleAmount = harvestAbleAmount;
        this.oreType = oreType;
        this.texture = texture;
    }

    public int getHarvestAbleAmount() {
        return harvestAbleAmount;
    }

    public ItemType getOreType() {
        return oreType;
    }

    public Texture getTexture() {
        return texture;
    }
}

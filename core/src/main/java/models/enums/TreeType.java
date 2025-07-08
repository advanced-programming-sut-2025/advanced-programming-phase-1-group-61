package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum TreeType {
    Oak(Season.Spring,7,ItemType.OakResin,ItemType.Acorns
        ,new Texture("Trees/Oak_Stage_2.png"),new Texture("Trees/Oak_Stage_3.png"),new Texture("Trees/Oak_Stage_4.png"),new Texture("Trees/Oak_stage_5.png"),new Texture("Trees/Oak_stage_5.png")),
    ApricotTree(Season.Spring,1,ItemType.Apricot,ItemType.ApricotTreeSapling
        ,new Texture("Trees/Apricot_Stage_1.png"),new Texture("Trees/Apricot_Stage_2.png"),new Texture("Trees/Apricot_Stage_3.png"),new Texture("Trees/Apricot_Stage_4.png"),new Texture("Trees/Apricot_Stage_4.png")),
    CherryTree(Season.Spring,1,ItemType.Cherry,ItemType.CherryTreeSapling
        ,new Texture("Trees/Cherry_Stage_1.png"),new Texture("Trees/Cherry_Stage_2.png"),new Texture("Trees/Cherry_Stage_3.png"),new Texture("Trees/Cherry_Stage_4.png"),new Texture("Trees/Cherry_Stage_5_Fruit.png")),
    BananaTree(Season.Summer,1,ItemType.Banana,ItemType.BannaTreeSapling
        ,new Texture("Trees/Banana_Stage_2.png"),new Texture("Trees/Banana_Stage_3.png"),new Texture("Trees/Banana_Stage_4.png"), new Texture("Trees/Banana_Stage_4.png"),new Texture("Trees/Banana_Stage_5_Fruit.png")),
    MangoTree(Season.Summer , 1 , ItemType.Mango,ItemType.MangoTreeSapling
        ,new Texture("Trees/Mango_Stage_1.png"),new Texture("Trees/Mango_Stage_2.png"),new Texture("Trees/Mango_Stage_3.png"),new Texture("Trees/Mango_Stage_4.png"),new Texture("Trees/Mango_Stage_5_Fruit.png")),
    OrangeTree(Season.Summer,1,ItemType.Orange,ItemType.OrangeTreeSapling
        ,new Texture("Trees/Orange_Stage_1.png"),new Texture("Trees/Orange_Stage_2.png"),new Texture("Trees/Orange_Stage_3.png"),new Texture("Trees/Orange_Stage_4.png"),new Texture("Trees/Orange_Stage_5_Fruit.png")),
    PeachTree(Season.Summer , 1 , ItemType.Peach,ItemType.PeachTreeSapling
        ,new Texture("Trees/Peach_Stage_1.png"),new Texture("Trees/Peach_Stage_2.png"),new Texture("Trees/Peach_Stage_3.png"),new Texture("Trees/Peach_Stage_4.png"),new Texture("Trees/Peach_Stage_5_Fruit.png")),
    AppleTree(Season.Fall , 1 , ItemType.Apple,ItemType.AppleTreeSapling
        ,new Texture("Trees/Apple_Stage_1.png"),new Texture("Trees/Apple_Stage_2.png"),new Texture("Trees/Apple_Stage_3.png"),new Texture("Trees/Apple_Stage_4.png"),new Texture("Trees/Apple_Stage_5_Fruit.png")),
    Pomegranate(Season.Fall , 1 , ItemType.Pomegranate,ItemType.PomegranateTreeSapling
        ,new Texture("Trees/Pomegranate_Stage_1.png"),new Texture("Trees/Pomegranate_Stage_2.png"),new Texture("Trees/Pomegranate_Stage_3.png"),new Texture("Trees/Pomegranate_Stage_4.png"),new Texture("Trees/Pomegranate_Stage_5_Fruit.png")),
    MapleTree(null ,9 , ItemType.MapleSyrup,ItemType.MapleSeed
        ,new Texture("Trees/Maple_Stage_1.png"),new Texture("Trees/Maple_Stage_2.png"),new Texture("Trees/Maple_Stage_3.png"),new Texture("Trees/Maple_Stage_4.png"),new Texture("Trees/Maple_Stage_5.png")),
    PineTree(null , 5 , ItemType.PineTar,ItemType.PineCones
        ,new Texture("Trees/Pine_Stage_1.png"),new Texture("Trees/Pine_Stage_2.png"),new Texture("Trees/Pine_Stage_3.png"),new Texture("Trees/Pine_Stage_4.png"),new Texture("Trees/Pine_Stage_5.png")),
    MahoganyTree(null , 1,ItemType.Sap,ItemType.MahoganySeed
        ,new Texture("Trees/Mahogany_Stage_1.png"),new Texture("Trees/Mahogany_Stage_2.png"),new Texture("Trees/Mahogany_Stage_3.png"),new Texture("Trees/Mahogany_Stage_4.png"),new Texture("Trees/Mahogany_Stage_5.png")),
    MushroomTree(null , 1 , ItemType.CommonMushroom,ItemType.MushroomTreeSeed
        ,new Texture("Trees/MushroomTree_Stage_1.png"),new Texture("Trees/MushroomTree_Stage_2.png"),new Texture("Trees/MushroomTree_Stage_3.png"),new Texture("Trees/MushroomTree_Stage_4.png"),new Texture("Trees/MushroomTree_Stage_5.png")),
    MysticTree(null , 7 , ItemType.MysticSyrup,ItemType.MysticTreeSeed
        ,new Texture("Trees/Mystic_Tree_Stage_1.png"),new Texture("Trees/Mystic_Tree_Stage_2.png"),new Texture("Trees/Mystic_Tree_Stage_3.png"),new Texture("Trees/Mystic_Tree_Stage_4.png"),new Texture("Trees/Mystic_Tree_Stage_5.png"))
    ;

    private Season seasonToGrowIn;
    private int[] stages ;
    private int harvestCycle;
    private ItemType fruit;
    private ItemType source;
    private Texture stageOneTexture , stageTwoTexture , stageThreeTexture , stageFourTexture ,stageFruit;

    TreeType(Season seasonToGrowIn,int harvestCycle,ItemType fruit,ItemType source
        , Texture stageOneTexture,Texture stageTwoTexture  ,Texture stageThreeTexture , Texture stageFourTexture,Texture stageFruit) {
        this.seasonToGrowIn = seasonToGrowIn;
        this.stages = new int[]{7, 7, 7, 7};
        this.harvestCycle = harvestCycle;
        this.fruit = fruit;
        this.stageOneTexture = stageOneTexture;
        this.stageTwoTexture = stageTwoTexture;
        this.stageThreeTexture = stageThreeTexture;
        this.stageFourTexture = stageFourTexture;
        this.stageFruit = stageFruit;
    }

    public int getHarvestCycle() {
        return harvestCycle;
    }

    public ItemType getFruit() {
        return fruit;
    }

    public ItemType getSource() {
        return source;
    }

    public int getStageByTreeAge(int age){
        int stage = 0;
        int dayCounter =0;
        for (int i = 0; i < this.stages.length; i++) {
            dayCounter += stages[i];
            if(dayCounter < age){
                stage++;
            }else {
                return stage;
            }
        }
        return 1;
    }

    public int getTotalCycleTime(){
        int counter =0;
        for (int stage : stages) {
            counter+=stage;
        }
        return counter;
    }

    public Season getSeasonToGrowIn() {
        return seasonToGrowIn;
    }

    public static TreeType getTreeType(String type) {
        try {
            return TreeType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static TreeType getTreeTypeBySource(ItemType source) {
        for (TreeType treeType : TreeType.values()) {
            if(treeType.getSource() != null){
                if (treeType.getSource().equals(source)) {
                    return treeType;
                }
            }
        }
        return null;
    }

    public Texture getStageOneTexture() {
        return stageOneTexture;
    }

    public Texture getStageTwoTexture() {
        return stageTwoTexture;
    }

    public Texture getStageThreeTexture() {
        return stageThreeTexture;
    }

    public Texture getStageFourTexture() {
        return stageFourTexture;
    }

    public Texture getStageFruit() {
        return stageFruit;
    }
}

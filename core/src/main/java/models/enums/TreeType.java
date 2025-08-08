package models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum TreeType {
    Oak(Season.Spring,7,ItemType.OakResin,ItemType.Acorns
        ,"Trees/Oak_Stage_2.png","Trees/Oak_Stage_3.png","Trees/Oak_Stage_4.png","Trees/Oak_stage_5.png","Trees/Oak_stage_5.png"),
    ApricotTree(Season.Spring,1,ItemType.Apricot,ItemType.ApricotTreeSapling
        ,"Trees/Apricot_Stage_1.png","Trees/Apricot_Stage_2.png","Trees/Apricot_Stage_3.png","Trees/Apricot_Stage_4.png","Trees/Apricot_Stage_4.png"),
    CherryTree(Season.Spring,1,ItemType.Cherry,ItemType.CherryTreeSapling
        ,"Trees/Cherry_Stage_1.png","Trees/Cherry_Stage_2.png","Trees/Cherry_Stage_3.png","Trees/Cherry_Stage_4.png","Trees/Cherry_Stage_5_Fruit.png"),
    BananaTree(Season.Summer,1,ItemType.Banana,ItemType.BannaTreeSapling
        ,"Trees/Banana_Stage_2.png","Trees/Banana_Stage_3.png","Trees/Banana_Stage_4.png","Trees/Banana_Stage_4.png","Trees/Banana_Stage_5_Fruit.png"),
    MangoTree(Season.Summer , 1 , ItemType.Mango,ItemType.MangoTreeSapling
        ,"Trees/Mango_Stage_1.png","Trees/Mango_Stage_2.png","Trees/Mango_Stage_3.png","Trees/Mango_Stage_4.png","Trees/Mango_Stage_5_Fruit.png"),
    OrangeTree(Season.Summer,1,ItemType.Orange,ItemType.OrangeTreeSapling
        ,"Trees/Orange_Stage_1.png","Trees/Orange_Stage_2.png","Trees/Orange_Stage_3.png","Trees/Orange_Stage_4.png","Trees/Orange_Stage_5_Fruit.png"),
    PeachTree(Season.Summer , 1 , ItemType.Peach,ItemType.PeachTreeSapling
        ,"Trees/Peach_Stage_1.png","Trees/Peach_Stage_2.png","Trees/Peach_Stage_3.png","Trees/Peach_Stage_4.png","Trees/Peach_Stage_5_Fruit.png"),
    AppleTree(Season.Fall , 1 , ItemType.Apple,ItemType.AppleTreeSapling
        ,"Trees/Apple_Stage_1.png","Trees/Apple_Stage_2.png","Trees/Apple_Stage_3.png","Trees/Apple_Stage_4.png","Trees/Apple_Stage_5_Fruit.png"),
    Pomegranate(Season.Fall , 1 , ItemType.Pomegranate,ItemType.PomegranateTreeSapling
        ,"Trees/Pomegranate_Stage_1.png","Trees/Pomegranate_Stage_2.png","Trees/Pomegranate_Stage_3.png","Trees/Pomegranate_Stage_4.png","Trees/Pomegranate_Stage_5_Fruit.png"),
    MapleTree(null ,9 , ItemType.MapleSyrup,ItemType.MapleSeed
        ,"Trees/Maple_Stage_1.png","Trees/Maple_Stage_2.png","Trees/Maple_Stage_3.png","Trees/Maple_Stage_4.png","Trees/Maple_Stage_5.png"),
    PineTree(null , 5 , ItemType.PineTar,ItemType.PineCones
        ,"Trees/Pine_Stage_1.png","Trees/Pine_Stage_2.png","Trees/Pine_Stage_3.png","Trees/Pine_Stage_4.png","Trees/Pine_Stage_5.png"),
    MahoganyTree(null , 1,ItemType.Sap,ItemType.MahoganySeed
        ,"Trees/Mahogany_Stage_1.png","Trees/Mahogany_Stage_2.png","Trees/Mahogany_Stage_3.png","Trees/Mahogany_Stage_4.png","Trees/Mahogany_Stage_5.png"),
    MushroomTree(null , 1 , ItemType.CommonMushroom,ItemType.MushroomTreeSeed
        ,"Trees/MushroomTree_Stage_1.png","Trees/MushroomTree_Stage_2.png","Trees/MushroomTree_Stage_3.png","Trees/MushroomTree_Stage_4.png","Trees/MushroomTree_Stage_5.png"),
    MysticTree(null , 7 , ItemType.MysticSyrup,ItemType.MysticTreeSeed
        ,"Trees/Mystic_Tree_Stage_1.png","Trees/Mystic_Tree_Stage_2.png","Trees/Mystic_Tree_Stage_3.png","Trees/Mystic_Tree_Stage_4.png","Trees/Mystic_Tree_Stage_5.png")
    ;

    private Season seasonToGrowIn;
    private int[] stages ;
    private int harvestCycle;
    private ItemType fruit;
    private ItemType source;
    private String stageOne , stageTwo , stageThree , stageFour ,stageFruit;
    private Texture stageOneTexture , stageTwoTexture , stageThreeTexture , stageFourTexture , stageFruitTexture;
    TreeType(Season seasonToGrowIn,int harvestCycle,ItemType fruit,ItemType source
        , String stageOneTexture,String stageTwoTexture  ,String stageThreeTexture , String stageFourTexture,String stageFruit) {
        this.seasonToGrowIn = seasonToGrowIn;
        this.stages = new int[]{7, 7, 7, 7};
        this.harvestCycle = harvestCycle;
        this.fruit = fruit;
        this.stageOne = stageOneTexture;
        this.stageTwo = stageTwoTexture;
        this.stageThree = stageThreeTexture;
        this.stageFour = stageFourTexture;
        this.stageFruit = stageFruit;
        this.source = source;
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
        if(stageOneTexture == null){
            try {
                stageOneTexture = new Texture(stageOne);
            } catch (Exception e) {
                stageOneTexture = new Texture("Trees/Oak_Stage_2.png");
            }
        }
        return stageOneTexture;
    }

    public Texture getStageTwoTexture() {
        if(stageTwoTexture == null){
            try {
                stageTwoTexture = new Texture(stageTwo);
            } catch (Exception e) {
                stageTwoTexture = new Texture("Trees/Oak_Stage_2.png");
            }
        }
        return stageTwoTexture;
    }

    public Texture getStageThreeTexture() {
        if(stageThreeTexture == null){
            try {
                stageThreeTexture = new Texture(stageThree);
            } catch (Exception e) {
                stageThreeTexture = new Texture("Trees/Oak_Stage_4.png");
            }
        }
        return stageThreeTexture;
    }

    public Texture getStageFourTexture() {
        if(stageFourTexture == null){
            try {
                stageFourTexture = new Texture(stageFour);
            } catch (Exception e) {
                stageFourTexture = new Texture("Trees/Oak_stage_5.png");
            }
        }
        return stageFourTexture;
    }

    public Texture getStageFruit() {
        if(stageFruitTexture == null){
            try {
                stageFruitTexture = new Texture(stageFruit);
            } catch (Exception e) {
                stageFruitTexture = new Texture("Trees/Oak_stage_5.png");
            }
        }
        return stageFruitTexture;
    }
}

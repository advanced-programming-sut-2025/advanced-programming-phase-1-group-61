package models.enums;

public enum TreeType {
    Oak(Season.Spring,7,ItemType.OakResin,ItemType.Acorns),
    ApricotTree(Season.Spring,1,ItemType.Apricot,ItemType.ApricotTreeSapling),
    CherryTree(Season.Spring,1,ItemType.Cherry,ItemType.CherryTreeSapling),
    BananaTree(Season.Summer,1,ItemType.Banana,ItemType.BannaTreeSapling),
    MangoTree(Season.Summer , 1 , ItemType.Mango,ItemType.MangoTreeSapling),
    OrangeTree(Season.Summer,1,ItemType.Orange,ItemType.OrangeTreeSapling),
    PeachTree(Season.Summer , 1 , ItemType.Peach,ItemType.PeachTreeSapling),
    AppleTree(Season.Fall , 1 , ItemType.Apple,ItemType.AppleTreeSapling),
    Pomegranate(Season.Fall , 1 , ItemType.Pomegranate,ItemType.PomegranateTreeSapling),
    MapleTree(null ,9 , ItemType.MapleSyrup,ItemType.MapleSeed),
    PineTree(null , 5 , ItemType.PineTar,ItemType.PineCones),
    MahoganyTree(null , 1,ItemType.Sap,ItemType.MahoganySeed),
    MushroomTree(null , 1 , ItemType.CommonMushroom,ItemType.MushroomTreeSeed),
    MysticTree(null , 7 , ItemType.MysticSyrup,ItemType.MysticTreeSeed)
    ;

    private Season seasonToGrowIn;
    private int[] stages ;
    private int harvestCycle;
    private ItemType fruit;
    private ItemType source;

    TreeType(Season seasonToGrowIn,int harvestCycle,ItemType fruit,ItemType source) {
        this.seasonToGrowIn = seasonToGrowIn;
        this.stages = new int[]{7, 7, 7, 7};
        this.harvestCycle = harvestCycle;
        this.fruit = fruit;
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


}

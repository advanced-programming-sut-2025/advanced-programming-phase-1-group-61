package models.enums;

public enum TreeType {
    Oak(Season.Spring,7,ItemType.OakResin),
    ApricotTree(Season.Spring,1,ItemType.Apricot),
    CherryTree(Season.Spring,1,ItemType.Cherry),
    BananaTree(Season.Summer,1,ItemType.Banana),
    MangoTree(Season.Summer , 1 , ItemType.Mango),
    OrangeTree(Season.Summer,1,ItemType.Orange),
    PeachTree(Season.Summer , 1 , ItemType.Peach),
    AppleTree(Season.Fall , 1 , ItemType.Apple),
    Pomegranate(Season.Fall , 1 , ItemType.Pomegranate),
    MapleTree(null ,9 , ItemType.MapleSyrup),
    PineTree(null , 5 , ItemType.PineTar),
    MahoganyTree(null , 1,ItemType.Sap),
    MushroomTree(null , 1 , ItemType.CommonMushroom),
    MysticTree(null , 7 , ItemType.MysticSyrup)
    ;

    private Season seasonToGrowIn;
    private int[] stages ;
    private int harvestCycle;
    private ItemType fruit;

    TreeType(Season seasonToGrowIn,int harvestCycle,ItemType fruit) {
        this.seasonToGrowIn = seasonToGrowIn;
        this.stages = new int[]{7, 7, 7, 7};
        this.harvestCycle = harvestCycle;
        this.fruit = fruit;
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
}

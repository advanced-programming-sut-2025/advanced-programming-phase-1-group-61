package models.resource;

import models.enums.ItemType;
import models.enums.TreeType;

public class Tree extends Resource{
    private TreeType type;
    private int treeStage;
    private int treeAge;
    private int daysUntilNextCycle;
    private ItemType fruit , source;
    public Tree(TreeType type) {
        this.type = type;
        this.treeStage = 1;
        this.treeAge = 0;
        this.daysUntilNextCycle += type.getTotalCycleTime();
        this.fruit = type.getFruit();
        this.source = type.getSource();
    }

    public TreeType getType() {
        return type;
    }

    public void setTreeStage(int treeStage) {
        this.treeStage = treeStage;
    }

    public void setTreeAge(int treeAge) {
        this.treeAge = treeAge;
    }

    public void setDaysUntilNextCycle(int daysUntilNextCycle) {
        this.daysUntilNextCycle = daysUntilNextCycle;
    }

    public int getTreeStage() {
        return treeStage;
    }

    public ItemType getFruit() {
        return fruit;
    }

    public ItemType getSource() {
        return source;
    }

    public void dayCycleForTrees(){
        this.treeAge++;
        setTreeStage(type.getStageByTreeAge(treeAge));
        if(daysUntilNextCycle>0){
            daysUntilNextCycle--;
        }
    }
}

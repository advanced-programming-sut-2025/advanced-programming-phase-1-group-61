package models.resource;

import models.enums.TreeType;

public class Tree extends Resource{
    private TreeType type;
    private int treeStage;
    private int treeAge;
    private int daysUntilNextCycle;
    public Tree(TreeType type) {
        this.type = type;
        this.treeStage = 1;
        this.treeAge = 0;
        this.daysUntilNextCycle += type.getTotalCycleTime();
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

    public void dayCycleForTrees(){
        this.treeAge++;
        setTreeAge(type.getStageByTreeAge(treeAge));
        if(daysUntilNextCycle>0){
            daysUntilNextCycle--;
        }
    }
}

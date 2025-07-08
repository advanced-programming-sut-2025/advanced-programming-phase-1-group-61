package models.resource;

import com.badlogic.gdx.graphics.Texture;
import io.github.camera.Main;
import models.AssetManager;
import models.enums.ItemType;
import models.enums.TreeType;

public class Tree extends Resource{
    private TreeType type;
    private int treeStage;
    private int treeAge;
    private int daysUntilNextCycle;
    private int x , y;
    private ItemType fruit , source;
    public Tree(TreeType type , int x , int y) {
        this.type = type;
        this.treeStage = 1;
        this.treeAge = 0;
        this.daysUntilNextCycle += type.getTotalCycleTime();
        this.fruit = type.getFruit();
        this.source = type.getSource();
        this.x = x;
        this.y = y;
    }

    public int getTreeAge() {
        return treeAge;
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

    public int getDaysUntilNextCycle() {
        return daysUntilNextCycle;
    }

    public void dayCycleForTrees(){
        this.treeAge++;
        setTreeStage(type.getStageByTreeAge(treeAge));
        if(daysUntilNextCycle>0){
            daysUntilNextCycle--;
        }
    }

    @Override
    public void draw() {
        Texture texture;
        if(treeStage == 1){
            texture = type.getStageOneTexture();
        } else if (treeStage ==2) {
            texture = type.getStageTwoTexture();
        } else if (treeStage == 3) {
            texture = type.getStageThreeTexture();
        } else {
            texture = type.getStageFourTexture();
        }
        if(daysUntilNextCycle == 0){
            texture = type.getStageFruit();
        }
        Main.getBatch().draw(texture ,x , y);
        System.out.println(x + " "+y);
    }
}

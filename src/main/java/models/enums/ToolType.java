package models.enums;

import models.tool.*;

public enum ToolType {
    Axe(new Axe()),
    FishingPole(new FishingPole()),
    Hoe(new Hoe()),
    MilkPail(new MilkPail()),
    PickAxe(new Pickaxe()),
    Scythe(new Scythe()),
    Shear(new Shear()),
    WateringCan(new WateringCan());
    private final Tool tool;
    ToolType(Tool tool) {
        this.tool = tool;
    }
    public Tool getTool() {
        return tool;
    }
    public String toString(){
        return this.name();
    }
}

package models.enums;

import models.Item;

public enum CropType {
    blueJazz("bush", new int[]{1, 2, 2, 2} ,-1 ,false);
    private final String displayName;
    private int[] stages ;
    private int reGrowthTime ;
    private boolean canBecomeGiant;

    CropType(String displayName, int[] stages, int reGrowthTime, boolean canBecomeGiant) {
        this.displayName = displayName;
        this.stages = stages;
        this.reGrowthTime = reGrowthTime;
        this.canBecomeGiant = canBecomeGiant;
    }

    public String getDisplayName() {
        return displayName;
    }
}

package models.enums;

public enum TreeType {
    Oak(Season.Spring),
    ApricotTree(Season.Spring),
    CherryTree(Season.Spring);

    private Season seasonToGrowIn;
    private int[] stages ;

    TreeType(Season seasonToGrowIn) {
        this.seasonToGrowIn = seasonToGrowIn;
    }

    public Season getSeasonToGrowIn() {
        return seasonToGrowIn;
    }
}

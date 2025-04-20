package models.enums;

public enum TreeType {
    Oak(Season.Spring);

    private Season seasonToGrowIn;

    TreeType(Season seasonToGrowIn) {
        this.seasonToGrowIn = seasonToGrowIn;
    }
}

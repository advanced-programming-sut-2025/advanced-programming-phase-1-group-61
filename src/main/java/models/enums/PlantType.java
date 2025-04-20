package models.enums;

public enum PlantType {
    SunFlower(Season.Fall),
    Bean(Season.Spring);

    private Season seasonToGrowIn;

    PlantType(Season season) {
        this.seasonToGrowIn = season;
    }
}

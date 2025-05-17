package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum WorkBenchType {
    BeeHouse(),
    CheesePress(),
    Keg(),
    Dehydrator(),
    CharcoalKlin(),
    Loom(),
    MayonnaiseMachine(),
    OilMaker(),
    PreservesJar(),
    FishSmoker(),
    Furnace();


    WorkBenchType() {
    }
    public static WorkBenchType getWorkBenchType(String type) {
        try {
            return WorkBenchType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

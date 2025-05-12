package models.enums;

public enum TrashcanType {
    PRIMARY(0,"Primary"),
    COPPER(15,"Copper"),
    IRON(30,"Iron"),
    GOLDEN(45,"Golden"),
    IRIDIUM(60,"Iridium"),;
    private final int returnPercentage;
    private final String displayName;
    TrashcanType(int returnPercentage, String displayName) {
        this.returnPercentage = returnPercentage;
        this.displayName = displayName;
    }
    public int getReturnPercentage() {
        return returnPercentage;
    }
    public String getDisplayName() {
        return displayName;
    }
}

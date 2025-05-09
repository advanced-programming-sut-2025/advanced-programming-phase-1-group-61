package models.enums;

public enum TrashcanType {
    PRIMARY(0),
    COPPER(15),
    IRON(30),
    GOLDEN(45),
    IRIDIUM(60);
    private final int returnPercentage;
    TrashcanType(int returnPercentage) {
        this.returnPercentage = returnPercentage;
    }
    public int getReturnPercentage() {
        return returnPercentage;
    }
}

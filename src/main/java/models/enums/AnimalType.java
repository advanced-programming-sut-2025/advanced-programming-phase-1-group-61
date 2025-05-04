package models.enums;

public enum AnimalType {
    COW(""),
    DIANASOUR(""),
    DUCK(""),
    FISH(""),
    GOAT(""),
    HEN(""),
    PIG(""),
    RABBIT(""),
    SHEEP("");
    private String texturePath;
    AnimalType(String texturePath) {
        this.texturePath = texturePath;
    }
}

package models.enums;

public enum Season {
    Spring("spring"),Summer("summer"),Fall("fall"),Winter("winter");
    private String displayName;

    Season(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

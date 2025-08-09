package models.enums;

public enum BackpackType {
    PRIMARY(12,"Primary"),
    BIG(24,"Big"),
    DELCUS(48,"Delcus");
    private final int size;
    private final String displayName;
    BackpackType(int size, String displayName) {
        this.size = size;
        this.displayName = displayName;
    }
    public int getSize() {
        return size;
    }
    public String getDisplayName() {
        return displayName;
    }
}

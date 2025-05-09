package models.enums;

public enum BackpackType {
    PRIMARY(12),
    BIG(24),
    DELCUS(Integer.MAX_VALUE);
    private final int size;
    BackpackType(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
}

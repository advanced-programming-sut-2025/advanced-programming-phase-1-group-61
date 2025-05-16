package models.enums;

public enum FriendshipLevel {
    LOW(0,266),
    MEDIUM(267,533),
    HIGH(534,799);
    private final int floor;
    private final int ceiling;
    FriendshipLevel(int floor, int ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }
    public int getFloor() {
        return floor;
    }
    public int getCeiling() {
        return ceiling;
    }
    public FriendshipLevel getNextLevel() {
        return switch (this) {
            case LOW -> MEDIUM;
            case MEDIUM, HIGH -> HIGH;
        };
    }
    public static FriendshipLevel getFriendshipLevel(int level) {
        for(FriendshipLevel f : FriendshipLevel.values()){
            if(f.floor <= level && f.ceiling >= level) return f;
        }
        return null;
    }
}

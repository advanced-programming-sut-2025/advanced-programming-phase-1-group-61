package models.enums;

public enum FriendshipLevel {
    LOW,
    MEDIUM,
    HIGH;

    public FriendshipLevel getLevel(int level) {
        if(level == 0) return LOW;
        if(level==1) return LOW;
        if(level==2) return MEDIUM;
        return HIGH;
    }
}

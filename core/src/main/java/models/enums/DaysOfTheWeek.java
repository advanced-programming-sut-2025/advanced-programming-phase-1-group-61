package models.enums;

public enum DaysOfTheWeek {
    Sunday("Sunday"),Monday("Monday"),Tuesday("Tuesday"),Wednesday("Wednesday")
    ,Thursday("Thursday"),Friday("Friday"),Saturday("Saturday");
    private String displayName;

    DaysOfTheWeek(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

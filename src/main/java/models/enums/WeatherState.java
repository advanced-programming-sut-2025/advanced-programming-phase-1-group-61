package models.enums;

public enum WeatherState {
    Sunny("sunny"),
    Rain("rainy"),
    Storm("stormy"),
    Snow("snowy");

    private String displayName;

    WeatherState(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static WeatherState fromDisplayName(String name) {
        for (WeatherState state : WeatherState.values()) {
            if (state.getDisplayName().equalsIgnoreCase(name)) {
                return state;
            }
        }
        return null;
    }

}

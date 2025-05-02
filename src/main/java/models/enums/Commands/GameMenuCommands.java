package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    START_GAME("^game\\s+new\\s+-u(?:\\s+(\\S+))?(?:\\s+(\\S+))?(?:\\s+(\\S+))?$"),
    CHOOSE_MAP("^game\\s+map\\s+(?<number>\\d+)$"),
    EQUIP_TOOL("^tools\\s+equip (?<name>.*)$"),
    SHOW_CURRENT_TOOL("^tools\\s+show\\s+current$"),
    TOOLS_SHOW_AVAILABLE("^tools\\s+show\\s+available$"),
    TOOLS_UPGRADE("^tools\\s+upgrade\\s+(?<name>.*)$"),
    TOOLS_USE("^tools\\s+use\\s+-d\\s+(?<direction>.*)$"),
    LOAD_GAME("^load\\s+game$"),
    NEXT_TURN("^next\\s+turn$"),
    SHOW_HOUR("^time$"),
    SHOW_DATE("^date$"),
    SHOW_DATE_AND_TIME("^$datetime"),
    SHOW_WEEKDAY("^day\\s+of\\s+the\\s+week$"),
    CHEAT_HOUR("^cheat\\s+advance\\s+time\\s+(?<hour>\\d+)h$");


    private String pattern;
    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }

    public String extractGroup(Matcher matcher, String groupName) {
        return (matcher != null && matcher.group(groupName) != null) ? matcher.group(groupName) : null;
    }
}
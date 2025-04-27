package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    START_GAME("^start$"),
    CHOOSE_MAP("^game\\s+map\\s+(?<number>\\d+)$"),
    EQUIP_TOOL("^tools\\s+equip (?<name>.*)$");



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
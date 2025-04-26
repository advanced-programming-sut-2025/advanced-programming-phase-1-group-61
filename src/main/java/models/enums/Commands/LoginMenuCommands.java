package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN("^login\\s+-u\\s+(?<username>.*)\\s+-p\\s+(?<password>.+?)(?<stayLoggedIn> -stay-logged-in)?$"),
    GO_BACK("^menu\\s+enter\\s+registermenu$");

    private final String pattern;
    LoginMenuCommands(String pattern) {
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

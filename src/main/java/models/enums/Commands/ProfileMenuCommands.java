package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("^change\\s+username\\s+-u (?<username>.*)$"),
    CHANGE_PASSWORD("^change\\s+password\\s+-p (?<newPassword>.*) -o (?<oldPassword>.*)$"),
    CHANGE_EMAIL("^change\\s+email\\s+-e (?<email>.*)$"),
    CHANGE_NICKNAME("^change\\s+nickname\\s+-u (?<nickname>)$");
    private final String pattern;
    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

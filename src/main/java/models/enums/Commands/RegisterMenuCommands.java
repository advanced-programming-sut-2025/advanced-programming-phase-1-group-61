package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    PASSWORD_PATTERN("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,12}$"),
    USERNAME_PATTERN("^(?=.*[a-z])(?=.*[A-Z])(?=.*-)$"),
    EMAIL_PATTERN("^[A-Za-z\\d\\.](?:[A-Za-z\\d]*\\.)?[A-Za-z\\d]*@[a-z]+\\.com$"),
    Register("^register\\s+-u\\s+(?<username>.*)\\s+-p\\s+(?<password>.*)\\s+(?<passwordConfirm>.*)\\s+-n\\s+(?<nickname>.*)\\s+-e\\s+" +
            "(?<email>.*) -g (?<gender>.*)$"),
    GO_TO_LOGIN_MENU("^menu\\s+enter\\s+$"),
    SHOW_CURRENT_MENU("^show\\s+current\\s+menu$");




    private String pattern;
    RegisterMenuCommands(String pattern) {
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

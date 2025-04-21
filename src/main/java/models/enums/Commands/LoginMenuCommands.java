package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    Login("^login -u (?<username>.*) -p (?<password>.*)( --stay-logged-in)?$");
    private String regex;
    LoginMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input,LoginMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}

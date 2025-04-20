package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ChangeMenu("menu enter (?<menu>.*)");
    private String regex;
    MainMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input,MainMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}

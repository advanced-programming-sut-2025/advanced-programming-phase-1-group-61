package models.enums.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCodes {
    CHEAT_ADVANCE_TIME("^cheat\\s+advance\\s+time\\s+(?<hour>\\d+)h$"),
    CHEAT_ADVANCE_DATE("^cheat\\s+advance\\s+date\\s+(?<day>\\d+)d$"),
    CHEAT_THOR("^cheat\\s+Thor\\s+-l\\s+(?<x>\\d+)\\s+(?<y>\\d+))$"),
    CHEAT_WEATHER_SET("^cheat\\s+weather\\s+set (?<type>.*)$"),
    ENERGY_SET("^energy\\s+set\\s+-v (?<value>.*)$"),
    ENERGY_UNLIMITED("^energy\\s+unlimited$"),
    CHEAT_ADD_ITEM("^cheat\\s+add\\s+item\\s+-n (?<itemName>.*) -c (?<count>.*)$"),
    CHEAT_SET_FRIENDSHIP("^cheat\\s+set\\s+friendship\\s+-n (?<animalName>.*) -c (?<amount>.*)$"),
    CHEAT_ADD_MONEY("^cheat\\s+add (?<count>.*) dollars$");
    private final String pattern;
    CheatCodes(String pattern) {
        this.pattern = pattern;
    }
    public String getPattern() {
        return pattern;
    }
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}

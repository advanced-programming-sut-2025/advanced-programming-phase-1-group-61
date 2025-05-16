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
    SHOW_DATE_AND_TIME("^datetime$"),
    SHOW_WEEKDAY("^day\\s+of\\s+the\\s+week$"),
    SHOW_WEATHER("^weather$"),
    FORECAST_WEATHER("^weather\\s+forecast$"),
    WALK("^walk\\s+-l\\s+<(?<x>\\d+),(?<y>\\d+)>$"),
    INVENTORY_SHOW("^inventory\\s+show$"),
    INVENTORY_TRASH("^inventory\\s+trash\\s+-i (?<itemName>\\S+)( -n (?<number>.*))?$"),
    HELP_READING_MAP("^help\\s+reading\\s+map$"),
    PRINT_MAP("^print\\s+map\\s+-l\\s+<(?<x>\\d+),(?<y>\\d+)>\\s+-s\\s+(?<size>\\d+)$"),
    SHOW_ENERGY("^energy\\S+show$"),
    CRAFT_INFO("^craftinfo\\s+-n (?<craftName>.*)$"),
    BUY_ANIMAL("^buy\\s+animal\\s+-a\\s+(?<animal>\\S+)\\s+-n\\s+(?<animalName>.*)$"),
    PET("^pet\\s+-n\\s+(?<animalname>.*)$"),
    CHEAT_SET_FRIENDSHIP("^cheat\\s+set\\s+\\friendship\\s+-n\\s+(?<animalname>.*)\\s+-c(?<amount>.\\d+)$"),
    ANIMALS("^animals$"),
    SHEPERD_ANIMALS("^sheper\\s+animals\\s+-n\\s+(?<animalname>.*)\\s+-l\\s+<(?<x>.\\d+),(?<y>.\\d+)>$"),
    FEED_HEY("^feed\\s+hey\\s+-n\\s+(?<animalname>.*)$"),
    PRODUCES("^produces$"),
    COLLECT_PRODUCES("^collect\\s+produces\\s+-n\\s+(?<animalname>.*)$"),
    SELL_ANIMAL("^sell\\s+animal\\s+-n\\s+(?<animalname>.*)$"),
    USE_AXE_FOR_SYRUP("^tools\\s+use\\s+-d\\s+(?<direction>.*)\\s+-syrup$"),
    SHOW_ALL_PRODUCTS("^show\\s+all\\s+products$"),
    SHOW_ALL_AVAILABLE_PRODUCTS("^show\\s+all\\s+available\\s+products$"),
    PURCHASE("^purchase (?<productName>.*) -n (?<count>.*)$"),
    PLANT("^plant\\s+-s\\s+(?<seed>.*)\\s+-d\\s+(?<direction>.*)$"),
    SHOW_PLANT("^showplant\\s+-l\\s+<(?<x>.\\d+),(?<y>.\\d+)>$"),
    FERTILIZE("^fertilize\\s+-f\\s+(?<fertilizer>.*)\\s+-d\\s+(?<direction>.*)$"),
    WATER_IN_BUCKET("^howmuch\\s+water$"),
    Refrigerator("^cooking\\s+refrigerator\\s+(?<action>put|pick)\\s+(?<item>.+)$"),
    ShowCookingRecipes("^cooking\\s+show\\s+recipes$"),
    CookingPrepare("^cooking\\s+prepare\\s+(?<recipeName>.*)$"),
    EatFood("^eat\\s+(?<foodName>.*)$");



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
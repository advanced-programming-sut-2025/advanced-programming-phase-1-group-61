package controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import controllers.GameControllers.PlayerController;
import controllers.GameControllers.WorldController;
import models.*;
import models.animal.Animal;
import models.building.Barn;
import models.building.Building;
import models.building.Coop;
import models.building.Shop;
import models.character.Buff;
import models.character.Character;
import models.date.Date;
import models.NPC.NPC;
import models.enums.*;
import models.food.FridgeItem;
import models.food.Refrigerator;
import models.interactions.Interact;
import models.map.Map;
import models.map.Tile;
import models.map.Weather;
import models.character.Inventory;
import models.resource.*;
import models.tool.Axe;
import models.tool.Tool;
import models.workBench.ItemKinds;
import models.workBench.WorkBench;
import models.tool.WateringCan;
import views.GameView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

public class GameMenuController {
    private int neededEnergy;
    private final Game game;
    private OrthographicCamera camera;
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;

    public GameMenuController(Game game) {
        this.game = game;
    }

    public void setView(GameView view, OrthographicCamera camera) {
        this.view = view;
        this.camera = camera;
        playerController = new PlayerController(camera);
        worldController = new WorldController(camera);
    }

    public void updateGame() {
        worldController.update();
        playerController.update();
    }

    public Result repairGreenHouse(Matcher matcher) {
        int x, y;
        x = Integer.parseInt(matcher.group("x"));
        y = Integer.parseInt(matcher.group("y"));
        Tile tile = game.getMap().getTileByCordinate(x, y);
        if (!tile.getType().equals(TileType.BrokenGreenHouse)) {
            return new Result(false, "not green house");
        }
        if (!tile.isCollisionOn()) {
            return new Result(false, "already fixed");
        }
        Character character = game.getCurrentCharacter();
        character.setMoney(character.getMoney() - 100);
        tile.setCollisionOn(false);
        return new Result(true, "successfully repaired");
    }

    public Result equipTool(Matcher matcher) {
        Character character = game.getCurrentCharacter();
        String name = matcher.group("name").trim();
        ToolType tool = Tool.fromString(name);
        if (tool == null) {
            return new Result(false, "Please enter a valid tool!");
        }
        Inventory inventory = character.getInventory();
        if (!inventory.checkToolInInventory(tool)) {
            return new Result(false, "you don't have the tool in your backpack!");
        }
        character.setTool(tool);
        return new Result(true, name + " has been equipped!");
    }

    public Result showCurrentTool() {
        Character character = game.getCurrentCharacter();
        Tool tool = character.getCurrentTool();
        if (tool == null) {
            return new Result(false, "No tool is in your hand!");
        }
        return new Result(true, tool.getType().toString() + " is your current tool!");
    }

    public Result showAvailableTools() {
        Character character = game.getCurrentCharacter();
        Inventory inventory = character.getInventory();
        return new Result(true, "you have (" + inventory.getAllTools() + ") in your backpack!");
    }

    public Result upgradeTool(Matcher matcher) {
        Character character = game.getCurrentCharacter();
        String name = matcher.group("name").trim();
        ToolType toolEx = Tool.fromString(name);
        if (toolEx == null) {
            return new Result(false, "Please enter a valid tool!");
        }
        Inventory inventory = character.getInventory();
        Tool tool = inventory.getToolByType(toolEx);
        if (tool == null) {
            return new Result(false, "You don't have the tool in your inventory!");
        }
        tool.upgrade();
        return new Result(true, name + " has been upgraded successfully!");
    }

    public Result cheatAddTool(Matcher matcher) {
        String toolName = matcher.group("toolName").trim();
        Inventory inventory = game.getCurrentCharacter().getInventory();
        ToolType tool = Tool.fromString(toolName);
        if (tool == null) {
            return new Result(false, "Please enter a valid tool!");
        }
        inventory.addTool(tool);
        return new Result(true, toolName + " added to inventory successfully!");
    }

    public Result useTool(Matcher matcher) {
        String directionStr = matcher.group("direction").trim();
        Character character = game.getCurrentCharacter();
        Direction direction = Direction.fromString(directionStr);
        if (direction == null) {
            return new Result(false, "Please enter a valid direction!(right/left/up/bottom/up_right/up_left/bottom_right/bottom_left)");
        }
        Tool tool = character.getCurrentTool();
        if (tool == null) {
            return new Result(false, "Please select a tool before trying to use it!");
        }
        if (tool.getConsumptionEnergy() > character.getEnergy()) {
            return new Result(false, "You don't have enough energy to use this tool!");
        }
        return new Result(true, tool.use(direction));
    }

    public Result useAxeForSyrup(Matcher matcher) {
        String directionString = matcher.group("direction");
        Direction direction = Direction.fromString(directionString);
        if (direction == null) {
            return new Result(false, directionString + " is not a valid direction.");
        }
        Character character = game.getCurrentCharacter();
        Tool tool = character.getCurrentTool();
        if (tool == null) {
            return new Result(false, "equip an axe first");
        }
        if (tool.getConsumptionEnergy() > character.getEnergy()) {
            return new Result(false, "You don't have enough energy to use this tool!");
        }
        Axe axe = (Axe) tool;
        return new Result(true, axe.useForSyrup(direction));
    }

    public Result loadGame() {
        User user = App.getLoggedInUser();
        if (user.getGameId() == 0) {
            return new Result(false, "you have to make a new game first");
        }
        Game game = App.getGameByID(user.getGameId());
        if (game == null) {
            return new Result(false, "failed to load game");
        }
        App.setCurrentGame(user.getGameId());

        return new Result(true, "game loaded successfully");
    }

    public Result changeTurn() {
        String string = game.changeTurn();
        return new Result(true, string);
    }

    public Result showHour() {
        int hour = game.getDate().getHour();
        return new Result(true, "hour: " + hour);
    }

    public Result showDate() {
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        return new Result(true, "season: " + season.getDisplayName() + " day: " + day.getDisplayName()
            + " (day count: " + dayCount + " )");
    }

    public Result showDateAndTime() {
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        int hour = game.getDate().getHour();
        return new Result(true, "season :" + season.getDisplayName() + "\nday: " + day.getDisplayName()
            + "\nday counter: " + dayCount + "\nhour: " + hour);
    }

    public Result showWeekDay() {
        DaysOfTheWeek day = game.getDate().getDay();
        return new Result(true, day.getDisplayName());
    }

    public Result cheatHour(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("hour"));
        if (amount <= 0) {
            return new Result(false, "number has to be positive");
        }


        if (amount >= 24) {
            game.getDate().changeDay(amount / 24);
        }
        game.getDate().increaseTime(amount % 24);
        return new Result(true, amount + " went by.");
    }

    public Result cheatDay(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("day"));
        game.getDate().changeDay(amount);
        return new Result(true, amount + "went by.");
    }

    public Result cheatThor(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        game.getMap().getWeather().lightning(x, y);
        return new Result(true, "lightning hit at x: " + x + " y: " + y);
    }

    public Result foreCastWeather() {
        WeatherState state = game.getMap().getWeather().getTomorrowWeatherState();
        return new Result(true, "tomorrow we expect : " + state.getDisplayName());
    }

    public Result cheatWeatherState(Matcher matcher) {
        String type = matcher.group("type");
        WeatherState state = WeatherState.fromDisplayName(type);
        if (state == null) {
            return new Result(false, "you cant set weather as " + type);
        }
        Weather weather = game.getMap().getWeather();
        weather.setCheatedWeatherState(state);
        return new Result(true, "you can expect " + state.getDisplayName() + " tomorrow.");
    }

    public Result energyResult(Matcher matcher) {
        Character currentCharacter = game.getCurrentCharacter();
        if (currentCharacter == null) {
            return new Result(false, "no current character");
        }
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Tile tile;
        try {
            tile = game.getMap().getTiles()[y][x];
        } catch (Exception e) {
            return new Result(false, "you cant enter void");
        }
        if (tile == null) {
            return new Result(false, "you cant enter void");
        }
        int turn = tile.getOwnerId();
        if (turn != -1) {
            int userId;
            try {
                userId = game.getCharacterByTurnNumber(turn).getUserId();
            } catch (Exception e) {
                userId = -1;
            }
            if (userId != currentCharacter.getUserId()) {
                return new Result(false, "you cant enter someone else's farm");
            }
        }
        this.neededEnergy = currentCharacter.getNeededEnergy(x, y);
        if (currentCharacter.isUnlimitedEnergy())
            return new Result(false, "you have unlimited energy! do you want to move?(yes/no)");
        return new Result(true, "you need " + neededEnergy + " energy to move\ndo you want to move?(yes/no)");
    }

    public Result walk(String confirmation) {
        if (confirmation.equals("yes")) {
            Character character = game.getCurrentCharacter();
            if (neededEnergy > character.getEnergy()) {

                return new Result(false, "character fainted!");
            }
            character.moveCharacter();
            character.setEnergy(character.getEnergy() - neededEnergy);
            return new Result(true, "you are now in x:" + character.getX() + " y:" + character.getY());
        }
        return new Result(false, "you did not move");
    }

    public Result energySet(Matcher matcher) {
        int value;
        try {
            value = Integer.parseInt(matcher.group("value"));
        } catch (Exception e) {
            return new Result(false, "please enter a valid value!");
        }
        game.getCurrentCharacter().setEnergy(value);
        return new Result(true, "energy set to: " + value);
    }

    public Result unlimitedEnergySet() {
        Character character = game.getCurrentCharacter();
        character.setUnlimitedEnergy(true);
        return new Result(true, "energy set to unlimited!");
    }

    public Result cheatAddItem(Matcher matcher) {
        String itemName = matcher.group("itemName");
        int count;
        try {
            count = Integer.parseInt(matcher.group("count"));
        } catch (Exception e) {
            return new Result(false, "please enter a valid number!");
        }
        ItemType item = Item.getItem(itemName);
        if (item == null) return new Result(false, "please enter a valid item!");
        game.getCurrentCharacter().getInventory().addItem(item, count);
        return new Result(true, count + " " + item.getDisPlayName() + "s added to Inventory!");
    }


    public Result helpRead() {
        return new Result(true, "Tree: T\nOre: O\nForagingItems: I\nCabin Floor: Cf\nCabin wall: Cw\n" +
            "Water: W\nStone: M\nGrass: G\nGreen House Wall: GW\nGreen House Floor: Gf");
    }

    public Result printMap(Matcher matcher) {
        Map map = game.getMap();

        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        int size = Integer.parseInt(matcher.group("size"));

        if (x < 0 || y < 0 || x + size > map.getTiles()[0].length || y + size > map.getTiles().length) {
            return new Result(false, "Error: Specified coordinates or size are out of map bounds.");
        }

        StringBuilder colorfulMap = new StringBuilder();

        for (int i = y; i < y + size && i < map.getTiles().length; i++) {
            for (int j = x; j < x + size && j < map.getTiles()[i].length; j++) {
                colorfulMap.append(getColoredTile(map.getTiles()[i][j])).append(" ");
            }
            colorfulMap.append("\n");
        }

        return new Result(true, colorfulMap.toString());
    }

    public Result showEnergy() {
        return new Result(true, "energy: " + game.getCurrentCharacter().getEnergy());
    }

    private String getColoredTile(Tile tile) {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String BLUE = "\u001B[34m";
        final String YELLOW = "\u001B[33m";
        final String GRAY = "\u001B[90m";
        final String WHITE = "\u001B[37m";
        final String BROWN = "\u001B[38;5;94m";


        if (tile.getType().equals(TileType.Grass)) {
            if (tile.getResource() != null) {
                return BROWN + "T " + RESET;
            }
            return GREEN + "G " + RESET;
        } else if (tile.getType().equals(TileType.Stone)) {
            if (tile.getResource() != null) {
                return WHITE + "S " + RESET;
            }
            return GRAY + "M " + RESET;
        } else if (tile.getType().equals(TileType.CabinFloor)) {
            if (tile.getResource() != null) {
                return BLUE + "R " + RESET;
            }
            return WHITE + "Cf" + RESET;
        }
        if (tile.getType().equals(TileType.Water)) return BLUE + "W " + RESET;
        if (tile.getType().equals(TileType.CabinWall)) return YELLOW + "Cw" + RESET;
        if (tile.getType().equals(TileType.BrokenGreenHouse)) return YELLOW + "Gf" + RESET;
        if (tile.getType().equals(TileType.BrokenGreenHouseWall)) return YELLOW + "GW" + RESET;
        if (tile.getType().equals(TileType.Soil)) return BROWN + "So" + RESET;

        return WHITE + "? " + RESET;
    }

    public Result craftInfo(Matcher matcher) {

        String craftName = matcher.group("craftName").trim();
        CropType cropType = CropType.getCropType(craftName);
        TreeType treeType = TreeType.getTreeType(craftName);
        if (cropType != null) {
            StringBuilder message = new StringBuilder("Crop:\n");
            message.append("Name: ").append(craftName).append("\n");
            message.append("Source: ").append(cropType.getSource().getDisPlayName()).append("\n");
            message.append("Stages: ").append(Arrays.toString(cropType.getStages())).append("\n");
            if (cropType.getReGrowthTime() > 0) {
                message.append("total harvest time: ").append(cropType.getReGrowthTime()).append("\n");
            }
            message.append("Base sell price: ").append(cropType.getProduct().getPrice()).append("\n");
            message.append("Is Edible: ").append(cropType.getProduct().isEdible()).append("\n");
            message.append("Base Energy: ").append(cropType.getProduct().getEnergy()).append("\n");
            message.append("Season: ").append(cropType.getSeason().getDisplayName()).append("\n");
            message.append("Can become giant: ").append(cropType.canBecomeGiant()).append("\n");

            return new Result(true, message.toString());
        }
        if (treeType != null) {
            StringBuilder message = new StringBuilder("Tree:\n");
            message.append("Name: ").append(craftName).append("\n");
            message.append("Source: ").append(treeType.getSource().getDisPlayName()).append("\n");
            message.append("Stages: ").append("[7 , 7 , 7 , 7]").append("\n");
            message.append("Base sell price: ").append(treeType.getFruit().getPrice()).append("\n");
            message.append("Is Edible: ").append(treeType.getFruit().isEdible()).append("\n");

        }
        return new Result(false, "invalid crop/tree");
    }

    public Result buyAnimal(Matcher matcher) {
        String animalName = matcher.group("animalName").trim();
        String animalType = matcher.group("animal").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character == null) {
            return new Result(false, "Error: No character found.");
        }
        AnimalType Type = Animal.TypeOf(animalType);
        if (Type == null) {
            return new Result(false, "pls enter valid animal");
        }
        String House = Animal.getHouse(Type);
        System.out.println(Type);
        System.out.println(House);
        if (House == null) {
            return new Result(false, "no empty house for animal");
        }
        if (character.getMoney() < Type.getPrice()) {
            return new Result(false, "You don't have enough money to buy this animal");
        }
        if (character.getAnimals().containsKey(animalName)) {
            return new Result(false, "You already have this named " + animalName);
        }

        if (Animal.buy(Type, House, animalName)) {
            return new Result(true, "Animal created successfully.");
        }
        return new Result(false, "Error: Invalid animal type.");
    }

    public Result pet(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        Character character = game.getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            if (character.getAnimals().get(animalName).pet(character.getX(), character.getY())) {
                return new Result(true, animalName + ": Yeeeeee common do it");
            }

            return new Result(false, "Go near " + animalName + " you don't have hands that long");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result cheatFriendship(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        int amount = Integer.parseInt(matcher.group("amount"));
        Character character = game.getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            character.getAnimals().get(animalName).petByCheat(amount);
            return new Result(true, "Youre friendship with " + animalName + " is " + amount + " you cheater");
        }
        return new Result(false, "You don't have any " + animalName);

    }

    public Result showAnimals() {
        StringBuilder show = new StringBuilder();
        for (Animal animal : game.getCurrentCharacter().getAnimals().values()) {
            show.append(animal.getType()).append(": ").append(animal.getName()).append(" || friendship: ").append(animal.getFriendship()).append(" || hunger: ").append(animal.isHunger()).append(" || isPet: ").append(animal.isPet());
            show.append("------------------------------\n");
        }
        String massage = show.toString();
        return new Result(true, massage);
    }

    public Result artisanUse(Matcher matcher) {
        String bench = matcher.group("bench").trim().replace("\\s+", "");
        String need1 = matcher.group("need1").trim().replace("\\s+", "");
        String need2 = matcher.group("need2").trim().replace("\\s+", "");
        ItemType item = null;
        ItemType Need1 = ItemType.getItembyname(need1);
        ItemType Need2 = ItemType.getItembyname(need2);
        if ((Need1 == null || Need1 == ItemType.Coal) && Need2 != null) {
            ItemType x = Need1;
            Need1 = Need2;
            Need2 = x;
        }
        java.util.Map<String, List<String>> itemKinds = new ItemKinds().getItemKinds();
        Character character = game.getCurrentCharacter();
        for (WorkBench Bench : game.getWorkBenches()) {
            if (Bench.getType().name().equalsIgnoreCase(bench)) {
                int count1 = 0;
                int count2 = 0;
                int time = 0 ;
                ItemType type2 = null;
                switch (bench.toUpperCase()) {
                    case "BEEHOUSE": {
                        item = ItemType.Honey;
                        time = 96;
                        Bench.addprocess(item,time);
                        return new Result(true, item.name() + " is processing in " + bench);
                    }
                    case "CHEESPRESS": {
                        if (Need1 == ItemType.BigGoatMilk) {
                            item = ItemType.BigGoatCheese;
                        } else if (Need1 == ItemType.GoatMilk) {
                            item = ItemType.GoatCheese;
                        } else if (Need1 == ItemType.BigMilk) {
                            item = ItemType.BigCheese;
                        } else if (Need1 == ItemType.Milk) {
                            item = ItemType.Cheese;
                        } else {
                            count1 = -1;
                            break;
                        }
                        time = 3;
                        count1 = 1;
                        break;
                    }
                    case "KEG": {
                        if (Need1 == null) {
                            count1 = -1;
                            break;
                        } else if (Need1 == ItemType.Wheat) {
                            item = ItemType.Beer;
                            time = 24;
                        } else if (Need1 == ItemType.Rice) {
                            item = ItemType.Vinegar;
                            time = 10;
                        } else if (Need1 == ItemType.CoffeeBean) {
                            item = ItemType.Coffee;
                            count1 = 5;
                            time = 2;
                            break;
                        } else if (itemKinds.get("Vegetable").contains(Need1.name())) {
                            item = ItemType.Juice;
                            time = 96;
                        } else if (Need1 == ItemType.Honey) {
                            item = ItemType.Mead;
                            time = 10;
                        } else if (Need1 == ItemType.Hops) {
                            item = ItemType.PaleAle;
                            time = 72;
                        } else if (itemKinds.get("Fruit").contains(Need1.name())) {
                            item = ItemType.Wine;
                            time = 168;
                        } else {
                            count1 = -1;
                            break;
                        }
                        count1 = 1;
                        break;
                    }
                    case "DEHYDRATOR": {
                        if(Need1 == null) {
                            count1 = -1;
                            break;
                        } else if (itemKinds.get("DryableFruit").contains(Need1.name())) {
                            item = ItemType.DriedFruit;
                        } else if (itemKinds.get("Mushroom").contains(Need1.name())) {
                            item = ItemType.DriedMushrooms;
                        } else if (Need1.name().equalsIgnoreCase(ItemType.Grape.name())) {
                            item = ItemType.Raisins;
                        } else {
                            count1 = -1;
                            break;
                        }
                        time = -1;
                        count1 = 5;
                        break;
                    }
                    case "CHACOALKILN": {
                        if (Need1 != ItemType.Wood) {
                            count1 = -1;
                            break;
                        }
                        item = ItemType.Coal;
                        count1 = 10;
                        time = 10;
                        break;
                    }
                    case "LOOM": {
                        if (Need1 != ItemType.Fiber) {
                            count1 = -1;
                            break;
                        }
                        item = ItemType.Cloth;
                        count1 = 5;
                        time = 4;
                        break;
                    }
                    case "MAYONNAISEMACHINE": {
                        if (Need1 == ItemType.DinosaurEgg) {
                            item = ItemType.DinosaurMayonnaise;
                        } else if (Need1 == ItemType.DuckEgg) {
                            item = ItemType.DuckMayonnaise;
                        } else if (Need1 == ItemType.BigEgg) {
                            item = ItemType.BigMayonnaise;
                        } else if (Need1 == ItemType.Egg) {
                            item = ItemType.Mayonnaise;
                        } else {
                            count1 = -1;
                            break;
                        }
                        time = 3;
                        count1 = 1;
                        break;
                    }
                    case "OILMAKER": {
                        if (Need1 == ItemType.Truffle) {
                            item = ItemType.TruffleOil;
                            time = 6;
                        } else if (Need1 == ItemType.Corn ||
                            Need1 == ItemType.SunflowerSeed ||
                            Need1 == ItemType.Sunflower) {
                            item = ItemType.Oil;
                            time = 10;
                        } else {
                            count1 = -1;
                            break;
                        }
                        count1 = 1;
                        break;
                    }
                    case "PERESERVESJAR": {
                        if (Need1 == null) {
                            count1 = -1;
                            break;
                        } else if (itemKinds.get("Vegetable").contains(Need1.name())) {
                            item = ItemType.Pickles;
                            time = 6;
                        } else if (itemKinds.get("Fruit").contains(Need1.name())) {
                            item = ItemType.Jelly;
                            time = 72;
                        } else {
                            count1 = -1;
                            break;
                        }
                        count1 = 1;
                        break;
                    }
                    case "FISHSMOKER": {
                        if (Need1 == null || !itemKinds.get("Fish").contains(Need1.name())) {
                            count1 = -1;
                            break;
                        }
                        item = ItemType.SmokedFish;
                        count1 = 1;
                        type2 = ItemType.Coal;
                        count2 = 1;
                        time = 96;
                        break;
                    }
                    case "FURNACE": {
                        if (Need1 == ItemType.CopperOre) {
                            item = ItemType.CopperBar;
                        } else if (Need1 == ItemType.IronOre) {
                            item = ItemType.IronBar;
                        } else if (Need1 == ItemType.GoldOre) {
                            item = ItemType.GoldBar;
                        } else if (Need1 == ItemType.IridiumOre) {
                            item = ItemType.IridiumBar;
                        } else {
                            count1 = -1;
                            break;
                        }
                        count1 = 5;
                        type2 = ItemType.Coal;
                        count2 = 1;
                        time = 4;
                        break;
                    }
                }
                if (count1 == -1 || item == null) return new Result(false, "worng items");
                if (character.getInventory().getCountOfItem(Need1) < count1) {
                    return new Result(false, "not enough " + Need1.name());
                }
                if (type2 != null) {
                    if (type2 != Need2) {
                        return new Result(false, "you need " + type2.name());
                    }
                    if (character.getInventory().getCountOfItem(type2) < count2) {
                        return new Result(false, "not enough " + type2.name());
                    }
                    character.getInventory().removeItem(type2, count2);
                }
                character.getInventory().removeItem(Need1, count1);
                Bench.addprocess(item,time);
                return new Result(true, item.name() + " is processing in " + bench);
            }
        }
        return new Result(false, "you dont have any " + bench);
    }

    public Result artisancollect(Matcher matcher) {
        String bench = matcher.group("bench").trim().replace("\\s+", "");
        for (WorkBench Bench : game.getWorkBenches()) {
            if (Bench.getType().name().equalsIgnoreCase(bench)) {
                if (Bench.Collect()) {
                    return new Result(true, bench + " is collected");
                }
                return new Result(false, bench + " has no item ready");
            }
        }
        return new Result(false, "you dont have any " + bench);
    }

    public Result shepherd(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Character character = App.getCurrentGame().getCurrentCharacter();
        Animal animal = character.getAnimals().get(animalName);
        if (animal == null) {
            return new Result(false, "You don't have any " + animalName);
        } else {
            Game game = App.getCurrentGame();
            assert game != null;
            Map map = game.getMap();
            Tile tile = map.getTileByCordinate(x, y);
            if (!tile.getType().isCollisionOn()) {
                Resource resource = tile.getResource();
                if (resource instanceof BuildingReference) {
                    String name = ((BuildingReference) resource).getName();
                    Building building = character.getBuilding(name);
                    if (building == null) {
                        return new Result(false, "There is a building there with name " + name + " that is not yours");
                    }
                    if (Objects.equals(animal.getHouse(), name)) {
                        return new Result(false, animalName + " is alredy there");
                    }
                    if (building.getBaseType().equals(animal.getType().getHouse()) &&
                        animal.getType().getHouseSize() <= building.getSize() &&
                        building.getSpace() > 0) {
                        building.addInput(animal);
                        animal.shepherd(x, y, false, building.getName());
                        return new Result(true, animalName + " is in " + name);
                    }
                    return new Result(false, animalName + "can't go in " + name);
                } else if (!(tile.getType().getTypeNum().equals("4") && tile.getType().getTypeNum().equals("8"))) {
                    animal.shepherd(x, y, true, null);
                    return new Result(true, animalName + "is out eating");
                }
                return new Result(false, animalName + "can't go in the cabin");
            } else {
                return new Result(false, "You can't go there");
            }
        }
    }

    public Result feedHay(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            character.getAnimals().get(animalName).feed();
            return new Result(true, animalName + ": I am well fed yes");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result animalsProducts() {
        StringBuilder show = new StringBuilder();
        for (Animal animal : App.getCurrentGame().getCurrentCharacter().getAnimals().values()) {
            show.append(animal.getType()).append(animal.getName()).append(" : ").append("\n");
            for (ItemType product : animal.products()) {
                show.append(product.getDisPlayName());
                show.append(" - ");
            }
            show.append("----------------------\n");
        }
        String massage = show.toString();
        return new Result(true, massage);
    }

    public Result sellItem(Matcher matcher) {
        int count = Integer.parseInt(matcher.group("count"));
        String ItemString = matcher.group("itemName");
        ItemType itemType = ItemType.getItemType(ItemString);
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getInventory().getCountOfItem(itemType) < count) {
            return new Result(false, "not in inventory");
        }
        ShippingBin shippingBin1 = null;
        for (ShippingBin shippingBin : App.getCurrentGame().getShippingBins()) {
            if (App.getCurrentGame().getCharacterByTurnNumber(shippingBin.getOwner()).getUserId() ==
                App.getCurrentGame().getCurrentCharacter().getUserId()) {
                shippingBin1 = shippingBin;
            }
        }
        if (shippingBin1 != null) {
            shippingBin1.addItemType(itemType);
        }
        return new Result(true, "item is now in shipping bin");
    }

    public Result getAnimalProduct(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            if (character.getAnimals().get(animalName).getProducts()) {
                return new Result(true, "You have collected " + animalName + " products");
            }
            return new Result(false, "Cannot collect " + animalName + " products");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result sellAnimal(Matcher matcher) {
        String animalName = matcher.group("animalname").trim();
        int gained = App.getCurrentGame().getCurrentCharacter().sellAnimal(animalName);
        if (gained > 0) {
            return new Result(true, "You gained" + gained);
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result plant(Matcher matcher) {
        String directionString = matcher.group("direction");
        String seedString = matcher.group("seed");
        Direction direction = Direction.fromString(directionString);
        if (direction == null) {
            return new Result(false, "not valid direction");
        }
        Game game = App.getCurrentGame();
        Character character = game.getCurrentCharacter();
        int x = character.getX() + direction.getDx();
        int y = character.getY() + direction.getDy();
        Tile tile = game.getMap().getTileByCordinate(x, y);
        if (tile == null) {
            return new Result(false, "where TF you want to plant tish shit");
        }
        if (tile.getResource() != null) {
            return new Result(false, "already some thing panted there");
        }
        if (!ItemType.isItem(seedString)) {
            return new Result(false, "is not a valid item");
        }
        ItemType seed = ItemType.getItemType(seedString);

        TreeType treeType = TreeType.getTreeTypeBySource(seed);
        if (character.getInventory().getCountOfItem(seed) <= 0) {
            return new Result(false, "you don't have the item to plant it here");
        }
        if (treeType != null) {
            if (tile.getType().isCollisionOn() || !tile.getType().equals(TileType.Grass)) {
                return new Result(false, "you cant plant here only on grass");
            }
            tile.setResource(new Tree(treeType, tile.getX(), tile.getY()));
            return new Result(true, treeType.name() + " is now planted at : " + x + " " + y);
        }

        character.getInventory().removeItem(seed, 1);
        CropType cropType = CropType.getCropTypeBySource(seed);

        if (cropType != null) {
            if (!tile.getType().equals(TileType.Soil) || tile.getType().isCollisionOn()) {
                return new Result(false, "you cant plant here only on soil");
            }
            tile.setResource(new Crop(cropType));
            return new Result(true, cropType.name() + " is now planted at : " + x + " " + y);
        }

        return new Result(false, "not a valid seed/sapling");
    }

    public Result showPlant(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Tile tile = App.getCurrentGame().getMap().getTileByCordinate(x, y);
        if (tile == null) {
            return new Result(false, "not a valid coordinate");
        }
        Resource resource = tile.getResource();
        if (resource == null) {
            return new Result(false, "not thing planted here");
        }
        if (resource instanceof Crop) {
            Crop crop = (Crop) resource;
            StringBuilder message = new StringBuilder("Crop\n");
            message.append("name: ").append(crop.getType().name()).append("\n");
            message.append("stage: ").append(crop.getCropAge()).append("\n");
            message.append("days until harvest: ").append(crop.getDaysTillNextHarvest()).append("\n");
            message.append("crop age:").append(crop.getCropAge()).append("\n");
            message.append("has water: ").append(crop.isWatered()).append("\n");
            message.append("is fertilized: ").append(crop.isFertilized()).append("\n");
            return new Result(true, message.toString());
        } else if (resource instanceof Tree) {
            Tree tree = (Tree) resource;
            StringBuilder message = new StringBuilder("Tree\n");
            message.append("name: ").append(tree.getType().name()).append("\n");
            message.append("stage: ").append(tree.getTreeStage()).append("\n");
            message.append("days until harvest: ").append(tree.getDaysUntilNextCycle()).append("\n");
            message.append("tree age: ").append(tree.getTreeAge()).append("\n");
            return new Result(true, message.toString());
        } else {
            return new Result(false, "resource in this tile is not a plant");
        }
    }

    public Result fertilize(Matcher matcher) {
        String fertilizerString = matcher.group("fertilizer");
        String directionString = matcher.group("direction");
        Direction direction = Direction.fromString(directionString);
        if (direction == null) {
            return new Result(false, "invalid direction");
        }
        ItemType fertilizer = ItemType.getItemType(fertilizerString);
        if (fertilizer == null) {
            return new Result(false, fertilizerString + " is not a valid item");
        }
        Game game = App.getCurrentGame();
        Character character = game.getCurrentCharacter();
        Tile tile = game.getMap().getTileByCordinate(
            character.getX() + direction.getDx(), character.getY() + direction.getDy());
        if (tile == null) {
            return new Result(false, "pls stop trying to break our game");
        }
        Resource resource = tile.getResource();
        if (resource == null) {
            return new Result(false, "not thing to fertilize");
        }
        if (!(resource instanceof Crop)) {
            return new Result(false, "not a plant what do you want to fertilize");
        }
        if (character.getInventory().getCountOfItem(fertilizer) <= 0) {
            return new Result(false, "you don't have the fertilizer");
        }
        if (fertilizer.equals(ItemType.DeluxeRetainingSoil)) {
            Crop crop = (Crop) resource;
            crop.setHasDeuxRetailingSoil(true);
            character.getInventory().removeItem(fertilizer, 1);
            return new Result(true, "crop fertilized");
        } else if (fertilizer.equals(ItemType.SpeedGro)) {
            Crop crop = (Crop) resource;
            crop.setHasSpeedGro(true);
            character.getInventory().removeItem(fertilizer, 1);
            return new Result(true, "crop fertilized");
        } else {
            return new Result(false, "not a valid fertilized");
        }
    }

    public Result cooking(Matcher matcher) {
        String recipeNameString = matcher.group("recipeName");
        Game game = App.getCurrentGame();
        Character character = game.getCurrentCharacter();
        CookingRecipes recipe = CookingRecipes.getCookingRecipes(recipeNameString);

        if (recipe == null) {
            return new Result(false, "Recipe not found.");
        }

        if (!character.getCookingRecipes().contains(recipe)) {
            return new Result(false, "You don't know how to cook this yet.");
        }

        Tile characterTile = game.getMap().getTileByCordinate(character.getX(), character.getY());
        if (!characterTile.getType().equals(TileType.CabinFloor)) {
            return new Result(false, "You need to be in the cabin to cook.");
        }

//        Tile fridgeTile = game.getMap().getTileByCordinate(character.getxRefrigerator(), character.getyRefrigerator());
//        if (!(fridgeTile.getResource() instanceof Refrigerator refrigerator)) {
//            return new Result(false, "Cannot find your fridge.");
//        }

        Inventory inventory = character.getInventory();

        for (ItemType itemType : recipe.getIngredients().keySet()) {
            int requiredCount = recipe.getIngredients().get(itemType);

            int availableInInventory = inventory.getCountOfItem(itemType);
//            int availableInFridge = refrigerator.getCountOfItemInFridge(itemType);

            if (availableInInventory < requiredCount) {
                return new Result(false, "Not enough " + itemType.getDisPlayName() + " to cook this.");
            }
        }

        for (ItemType itemType : recipe.getIngredients().keySet()) {
            int requiredCount = recipe.getIngredients().get(itemType);

            int availableInInventory = inventory.getCountOfItem(itemType);

            if (availableInInventory >= requiredCount) {
                inventory.removeItem(itemType, requiredCount);
            } else {
                int remaining = requiredCount - availableInInventory;
                inventory.removeItem(itemType, availableInInventory);
//                refrigerator.removeItemFromFridge(itemType, remaining);
            }
        }

        inventory.addItem(ItemType.getItemType(recipe.name()), 1);

        return new Result(true, "Successfully cooked " + recipe.getName() + ".");
    }


    public Result eatFood(Matcher matcher) {
        String foodString = matcher.group("foodName");
        ItemType food = ItemType.getItemType(foodString);
        if (food == null) {
            return new Result(false, "not a valid item");
        }
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getInventory().getCountOfItem(food) <= 0) {
            return new Result(false, "not in your inventory");
        }
        if (!food.isEdible()) {
            return new Result(false, "you cant kill your self by eating " + food.getDisPlayName());
        }
        if (CookingRecipes.getCookingRecipes(food.name()) != null) {
            CookingRecipes cookedFood = CookingRecipes.getCookingRecipes(food.name());
            Buff buff = cookedFood.getBuff();
            if (buff != null) {
                character.setBuff(buff);
                buff.use();
            }
            character.getInventory().removeItem(food, 1);
            int newEnergy = character.getEnergy() + food.getEnergy();
            character.setEnergy(newEnergy);
        } else {
            character.getInventory().removeItem(food, 1);
            int newEnergy = character.getEnergy() + food.getEnergy();
            character.setEnergy(newEnergy);
        }
        return new Result(true, "you ate " + foodString + " successfully.");
    }

    public Result showCookingRecipes() {
        Character character = App.getCurrentGame().getCurrentCharacter();
        StringBuilder message = new StringBuilder("Cooking Recipes:\n");
        for (CookingRecipes cookingRecipe : character.getCookingRecipes()) {
            message.append(cookingRecipe.toString());
        }

        return new Result(true, message.toString());
    }

    public Result showWaterInBucket() {
        Character character = App.getCurrentGame().getCurrentCharacter();
        Tool tool = character.getInventory().getToolByType(ToolType.WateringCan);
        if (tool == null) {
            return new Result(false, "you don't have a water bucket");
        }
        WateringCan water = (WateringCan) tool;
        return new Result(true, "you have " + water.getDurability() + " in your bucket.");
    }

    public Result putOrPickItemInRefrigerator(Matcher matcher) {
        String itemString = matcher.group("item");
        String action = matcher.group("action");
        ItemType itemType = ItemType.getItemType(itemString);
        if (itemType == null) {
            return new Result(false, "not valid item");
        }
        Character character = App.getCurrentGame().getCurrentCharacter();

        Tile characterTile = App.getCurrentGame().getMap().getTileByCordinate(character.getX(), character.getY());
        if (!characterTile.getType().equals(TileType.CabinFloor)) {
            return new Result(false, "you need to be in a cabin to do this");
        }
        if (action.equalsIgnoreCase("put")) {
            int count = character.getInventory().getCountOfItem(itemType);
            if (count <= 0) {
                return new Result(false, "you dont have this item in your inventory");
            }
            character.getInventory().removeItem(itemType);
            int x = character.getxRefrigerator();
            int y = character.getyRefrigerator();
            Tile tile = App.getCurrentGame().getMap().getTileByCordinate(x, y);
            Refrigerator refrigerator = (Refrigerator) tile.getResource();
            refrigerator.addItem(itemType, count);
            return new Result(true, "is now in fridge");
        } else if (action.equalsIgnoreCase("pick")) {
            int x = character.getxRefrigerator();
            int y = character.getyRefrigerator();
            Tile tile = App.getCurrentGame().getMap().getTileByCordinate(x, y);
            Refrigerator refrigerator = (Refrigerator) tile.getResource();
            int count = 0;
            for (FridgeItem fridgeItem : refrigerator.getItems()) {
                if (fridgeItem.getItem().equals(itemType)) {
                    count = fridgeItem.getQuantity();
                    fridgeItem.setQuantity(0);
                }
            }
            if (count > 0) {
                character.getInventory().addItem(itemType, count);
                return new Result(true, "now in your inventory");
            } else {
                return new Result(false, "not in fridge");
            }
        } else {
            return new Result(false, "invalid command");
        }
    }

    public Result meetNpc(Matcher matcher) {
        String name = matcher.group("name");
        if (!NpcInfo.checkName(name)) {
            return new Result(false, "please enter a valid npc name!");
        }
        NPC npc = NPC.getNPC(name);
        if (npc == null) return new Result(false, "npc not found");
        return new Result(true, npc.getDialog());
    }

    public Result giftNPC(Matcher matcher) {
        String name = matcher.group("name").trim();
        String itemName = matcher.group("item").trim();
        ItemType item = ItemType.getItemType(itemName);
        if (item == null) {
            return new Result(false, "please enter a valid item!");
        }
        if (!NpcInfo.checkName(name)) {
            return new Result(false, "please enter a valid npc name!");
        }
        NPC npc = NPC.getNPC(name);
        if (npc == null) return new Result(false, "npc not found");
        List<ItemType> favorites = npc.getInfo().getFavorites();
        boolean found = false;
        for (ItemType favorite : favorites) {
            if (favorite.equals(item)) {
                found = true;
                break;
            }
        }
        Character character = App.getCurrentGame().getCurrentCharacter();
        App.getCurrentGame().changeDayActivities();
        if (npc.isFirstGiftOfDay()) {
            npc.setFirstGiftOfDay(false);
            if (found) {
                npc.getFriendships(character).setFriendshipPoints(200);
                return new Result(true, "You have gained 200 friendship points with " + name);
            }
            npc.getFriendships(character).setFriendshipPoints(50);
            return new Result(true, "You have gained 50 friendship points with " + name);
        }
        return new Result(false, "you have not gained a point because this is not your first time in this day that you are giving " + name + " a gift!");
    }

    public Result friendshipNPCList() {
        return new Result(true, NPC.getNPCFriendshipsDetails(App.getCurrentGame().getCurrentCharacter()));
    }

    public Result cheatSetNpcFriendship(Matcher matcher) {
        String npcName = matcher.group("name").trim();
        int count;
        try {
            count = Integer.parseInt(matcher.group("count").trim());
        } catch (NumberFormatException e) {
            return new Result(false, "please enter a valid number");
        }
        if (!NpcInfo.checkName(npcName)) {
            return new Result(false, "please enter a valid npc name!");
        }
        NPC npc = NPC.getNPC(npcName);
        if (npc == null) return new Result(false, "npc not found");
        if (count > 799) {
            return new Result(false, "the max amount of level is 800");
        } else if (count < 0) {
            return new Result(false, "the level cannot be negative!");
        }
        npc.getFriendships(App.getCurrentGame().getCurrentCharacter()).setFriendshipLevel(count);
        return new Result(true, "friendship set successfully");
    }

    public Result questsList() {
        return new Result(true, NPC.getQuests(App.getCurrentGame().getCurrentCharacter()));
    }

    public Result questsFinish(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        NPC npc = character.getNPC();
        if (npc == null) return new Result(false, "you are not near a npc!");
        int index;
        try {
            index = Integer.parseInt(matcher.group("index").trim());
        } catch (NumberFormatException e) {
            return new Result(false, "please enter a valid number");
        }
        if (!npc.checkQuestAvailability(character, npc.getFriendships(character), index)) {
            return new Result(false, "this quest is not available for you yet!");
        }
        return new Result(true, npc.checkCharacterEnoughItems(character, index, npc.getFriendships(character)));
    }

    public Result buildCage(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String cageTypeString = matcher.group("cageType");
        String name = matcher.group("name");
        Character character = App.getCurrentGame().getCurrentCharacter();
        CageType cageType = CageType.getCageType(cageTypeString);
        if (cageType == null) {
            return new Result(false, "cage type not valid");
        }
        Game game = App.getCurrentGame();
        Map map = game.getMap();
        Tile charachterTile = map.getTileByCordinate(character.getX(), character.getY());
        if (!charachterTile.getType().equals(TileType.Carpenter)) {
            return new Result(false, "you have to be in carpenter shop.");
        }
        if (character.getInventory().getCageTypeNumber(cageType) <= 0) {
            return new Result(false, "you have not bough this cage in shop buy it first");
        }


        for (int height = y; height < cageType.getHeight() + y; height++) {
            for (int width = x; width < cageType.getWidth() + x; width++) {
                Tile tile = map.getTileByCordinate(width, height);
                if (tile == null) {
                    return new Result(false, "invalid coordinate");
                }
                if (game.getCharacterByTurnNumber(tile.getOwnerId()) == null) {
                    return new Result(false, "you cant build in city");
                }
                if (game.getCharacterByTurnNumber(tile.getOwnerId()).getUserId() != character.getUserId()) {
                    return new Result(true, "this tile is not yours");
                }
                if (tile.getType().isCollisionOn() || tile.getResource() != null) {
                    return new Result(false, "not valid tile to build on");
                }
            }
        }

        for (int height = y; height < cageType.getHeight() + y; height++) {
            for (int width = x; width < cageType.getWidth() + x; width++) {
                Tile tile = map.getTileByCordinate(width, height);
                tile.setResource(new BuildingReference(name));
            }
        }
        if (cageType.equals(CageType.Barn) || cageType.equals(CageType.BigBarn) || cageType.equals(CageType.DeluxeBarn)) {
            character.getBuildings().add(new Barn(cageType, name, x, y));
        } else {
            character.getBuildings().add(new Coop(cageType, name, x, y));
        }
        character.getInventory().removeCage(1, cageType);
        return new Result(true, "cage built successfully");

    }

    public Result showRecipes() {
        StringBuilder message = new StringBuilder("Recipes:\n");
        for (Recipe recipe : App.getCurrentGame().getCurrentCharacter().getRecipes()) {
            message.append(recipe.toString()).append("\n");
        }
        return new Result(true, message.toString());
    }

    public Result craft(Matcher matcher) {
        String item = matcher.group("craftName");
        Recipe recipe = Recipe.getRecipe(item);
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (recipe == null) {
            return new Result(false, "invalid item");
        }
        for (ItemType itemRequired : recipe.getRecipe().keySet()) {
            if (character.getInventory().getCountOfItem(itemRequired) <= recipe.getRecipe().get(itemRequired)) {
                return new Result(false, "you don't have enough " + itemRequired.getDisPlayName());
            }
        }
        for (ItemType itemRequired : recipe.getRecipe().keySet()) {
            character.getInventory().removeItem(itemRequired, recipe.getRecipe().get(itemRequired));
        }
        character.getInventory().addItem(ItemType.getItemType(recipe.name()), 1);
        return new Result(true, "item built successfully");
    }

    public Result placeItem(Matcher matcher) {
        Direction direction = Direction.fromString(matcher.group("direction"));
        if (direction == null) {
            return new Result(false, "invalid direction");
        }
        ItemType itemType = ItemType.getItemType(matcher.group("itemName"));
        if (itemType == null) {
            return new Result(false, "invalid item");
        }
        Character character = App.getCurrentGame().getCurrentCharacter();
        Tile tile = App.getCurrentGame().getMap().getTileByCordinate(character.getX() + direction.getDx(), character.getY() + direction.getDy());
        if (tile.getResource() != null || tile.isCollisionOn()) {
            return new Result(false, "you cant place item here");
        }
        int count = character.getInventory().getCountOfItem(itemType);
        if (count <= 0) {
            return new Result(false, "you don't have this item in your inventory");
        }

        character.getInventory().removeItem(itemType, 1);
        WorkBenchType workBenchType = WorkBenchType.getWorkBenchType(itemType.name());
        if (workBenchType != null) {
            WorkBench workBench = new WorkBench(workBenchType);
            tile.setResource(workBench);
            game.getWorkBenches().add(workBench);
            return new Result(true, "successfully placed your work bench");
        }
        tile.setItem(new Item(itemType));
        return new Result(true, "successfully placed your item");
    }

    public Result AskMarriage(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        String friend = matcher.group("username");
        String ringName = matcher.group("ring");
        int frienfid = App.getIdByUserName(friend);


        if (character.getIteractions().addInteract("marrige", "", frienfid, character.getUserId() + "is askind" + friend,
            ItemType.WeddingRing, null, null, null)) {
            return new Result(true, "marrife request is sent");
        }
        return new Result(false, "ask marriage");
    }

    public Result RespondMarriage(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        String friend = matcher.group("username");
        String answer = matcher.group("answer");
        boolean ans;
        if (answer.equals("accept")) {
            ans = true;
        } else if (answer.equals("reject")) {
            ans = false;
        } else return new Result(false, "invalid answer");
        if (character.getIteractions().marriagerate(ans)) {
            return new Result(ans, "you are marriaged to " + friend);
        }
        return new Result(false, "invalid command");
    }

    public Result StartTrade(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        List<Interact> interacts = character.getIteractions().tradelist();
        if (interacts.isEmpty()) {
            return new Result(true, "invalid command");
        }
        StringBuilder trades = new StringBuilder();
        for (Interact interact : interacts) {
            trades.append(interact.getValue()).append("\\n");
        }
        return new Result(true, trades.toString());
    }

    public Result Trade(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        String friend = matcher.group("username");
        String kind = matcher.group("tradeType");
        String item1 = matcher.group("item");
        String item2 = matcher.group("targetItem");
        ItemType Item1 = ItemType.getItemType(item1);
        ItemType Item2 = ItemType.getItemType(item2);
        int frienfid = App.getIdByUserName(friend);
        int amount1 = Integer.parseInt(matcher.group("amount"));
        int amount2;
        if (item2 != null) {
            amount2 = Integer.parseInt(matcher.group("price"));
        } else {
            amount2 = Integer.parseInt(matcher.group("targetAmount"));
        }
        if (character.getIteractions().addInteract("trade", kind, frienfid, "you " + kind + friend +
            item1 + amount1 + item2 + amount2, Item1, amount1, Item2, amount2)) {
            return new Result(true, "successfully added trade");
        }

        return new Result(false, "rong inputes");
    }

    public Result TradeList(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
        StringBuilder massage = new StringBuilder();
        for (Interact interact : character.getIteractions().tradelist()) {
            massage.append(interact.getValue()).append("\\n");
        }
        return new Result(true, massage.toString());
    }

    public Result TradeResponse(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result Flower(Matcher matcher) {
        String friend = matcher.group("username");
        int friendid = App.getIdByUserName(friend);
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getIteractions().addInteract(
            "flower", "", friendid, null, null, null, null, null
        )) {
            return new Result(true, "flower request is sent");
        }
        return new Result(false, "invalid input");
    }

    public Result Friendships(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result GiftRate(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result TradeHistory(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result GiftHistory(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result Hug(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result Talk(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result TalkHistory(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result Gift(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result GiftList(Matcher matcher) {
        return new Result(false, "ask marriage");
    }

    public Result cheatAddMoney(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("count"));
        App.getCurrentGame().getCurrentCharacter().setMoney(App.getCurrentGame().getCurrentCharacter().getMoney() + amount);
        return new Result(true, "you are rich now " + amount);
    }
}

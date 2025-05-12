package controllers;

import models.*;
import models.animal.Animal;
import models.building.Shop;
import models.character.Character;
import models.enums.*;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import models.map.Tile;
import models.map.Weather;
import models.character.Inventory;
import models.resource.Tree;
import models.tool.Axe;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameMenuController {
    private int neededEnergy;

    public Result equipTool(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
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
        Character character = App.getCurrentGame().getCurrentCharacter();
        Tool tool = character.getCurrentTool();
        if (tool == null) {
            return new Result(false, "No tool is in your hand!");
        }
        return new Result(true, tool.getType().toString() + " is your current tool!");
    }

    public Result showAvailableTools() {
        Character character = App.getCurrentGame().getCurrentCharacter();
        Inventory inventory = character.getInventory();
        return new Result(true, "you have (" + inventory.getAllTools() + ") in your backpack!");
    }

    public Result upgradeTool(Matcher matcher) {
        Character character = App.getCurrentGame().getCurrentCharacter();
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
        Inventory inventory = App.getCurrentGame().getCurrentCharacter().getInventory();
        ToolType tool = Tool.fromString(toolName);
        if (tool == null) {
            return new Result(false, "Please enter a valid tool!");
        }
        inventory.addTool(tool);
        return new Result(true, toolName + " added to inventory successfully!");
    }

    public Result useTool(Matcher matcher) {
        String directionStr = matcher.group("direction").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
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
        Character character = App.getCurrentGame().getCurrentCharacter();
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

    public Result startGame(List<String> usernames, int[] mapNumbers) {
        List<User> userList = new ArrayList<>();

        if (usernames.isEmpty()) {
            return new Result(false, "uou need at least one other player");
        }


        for (String username : usernames) {
            userList.add(User.getUserByUsername(username));
        }
        Map map = MapBuilder.buildFullMap(mapNumbers[0], mapNumbers[1], mapNumbers[2], mapNumbers[3]);
        List<Character> characterList = new ArrayList<>();
        int i = 0;
        for (User user : userList) {
            Character character = new Character(user.getId());
            character.setX(map.getXSpawnPoints().get(i));
            character.setY(map.getYSpawnPoints().get(i));
            i++;
            characterList.add(character);
        }
        for (User user : userList) {
            if (user.getGameId() != 0) {
                return new Result(false, user.getUsername() + " is already in another game.");
            }
        }
        Game game = new Game(map, characterList);
        for (User user : userList) {
            user.setGameId(game.getId());
        }
        App.addGame(game);
        App.setCurrentGame(game.getId());
        return new Result(true, "game started successfully");
    }

    public Result startGameErrors(List<String> usernames) {
        for (int i = 1; i < usernames.size(); i++) {
            String username = usernames.get(i);
            if (username.equals(App.getLoggedInUser().getUsername())) {
                return new Result(false, "you can't pick yourself!");
            }
        }
        StringBuilder names = new StringBuilder();
        for (String username : usernames) {
            User user = User.getUserByUsername(username.trim());
            if (user == null) names.append(username).append(" is invalid!").append("\n");
        }
        if (!names.isEmpty()) names.deleteCharAt(names.length() - 1);
        if (!names.isEmpty()) return new Result(false, names.toString());
        return new Result(true, "");
    }

    public Result userListIsValid(List<String> usernames) {
        for (String username : usernames) {
            if (User.getUserByUsername(username) == null) {
                return new Result(false, username + "is not valid username");
            }
        }
        return new Result(true, "all players are available");
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
        Game game = App.getCurrentGame();
        String string = game.changeTurn();
        return new Result(true, string);
    }

    public Result showHour() {
        Game game = App.getCurrentGame();
        int hour = game.getDate().getHour();
        return new Result(true, "hour: " + hour);
    }

    public Result showDate() {
        Game game = App.getCurrentGame();
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        return new Result(true, "season: " + season.getDisplayName() + " day: " + day.getDisplayName()
                + " (day count: " + dayCount + " )");
    }

    public Result showDateAndTime() {
        Game game = App.getCurrentGame();
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        int hour = game.getDate().getHour();
        return new Result(true, "season :" + season.getDisplayName() + "\nday: " + day.getDisplayName()
                + "\nday counter: " + dayCount + "\nhour: " + hour);
    }

    public Result showWeekDay() {
        Game game = App.getCurrentGame();
        DaysOfTheWeek day = game.getDate().getDay();
        return new Result(true, day.getDisplayName());
    }

    public Result cheatHour(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("hour"));
        if (amount <= 0) {
            return new Result(false, "number has to be positive");
        }

        Game game = App.getCurrentGame();
        if (amount >= 24) {
            game.getDate().changeDay(amount / 24);
        }
        game.getDate().increaseTime(amount % 24);
        return new Result(true, amount + " went by.");
    }

    public Result cheatDay(Matcher matcher) {
        int amount = Integer.parseInt(matcher.group("day"));
        Game game = App.getCurrentGame();
        game.getDate().changeDay(amount);
        return new Result(true, amount + "went by.");
    }

    public Result cheatThor(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        App.getCurrentGame().getMap().getWeather().lightning(x, y);
        return new Result(true, "lightning hit at x: " + x + " y: " + y);
    }

    public Result foreCastWeather() {
        WeatherState state = App.getCurrentGame().getMap().getWeather().getTomorrowWeatherState();
        return new Result(true, "tomorrow we expect : " + state.getDisplayName());
    }

    public Result cheatWeatherState(Matcher matcher) {
        String type = matcher.group("type");
        WeatherState state = WeatherState.fromDisplayName(type);
        if (state == null) {
            return new Result(false, "you cant set weather as " + type);
        }
        Weather weather = App.getCurrentGame().getMap().getWeather();
        weather.setCheatedWeatherState(state);
        return new Result(true, "you can expect " + state.getDisplayName() + " tomorrow.");
    }

    public Result energyResult(Matcher matcher) {
        Game game = App.getCurrentGame();
        Character currentCharacter = game.getCurrentCharacter();
        if (currentCharacter == null) {
            return new Result(false, "no current character");
        }
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Tile tile = game.getMap().getTiles()[y][x];
        if (tile == null) {
            return new Result(false, "you cant enter void");
        }
        int turn = tile.getOwnerId();
        if (turn != -1) {
            int userId = game.getCharacterByTurnNumber(turn).getUserId();
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
            Character character = App.getCurrentGame().getCurrentCharacter();
            if (neededEnergy > character.getEnergy()) {
                character.faint();
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
        App.getCurrentGame().getCurrentCharacter().setEnergy(value);
        return new Result(true, "energy set to: " + value);
    }

    public Result unlimitedEnergySet() {
        Character character = App.getCurrentGame().getCurrentCharacter();
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
        App.getCurrentGame().getCurrentCharacter().getInventory().addItem(item, count);
        return new Result(true, count + " " + item.getDisPlayName() + "s added to Inventory!");
    }

    public Result inventoryShow() {
        Inventory inventory = App.getCurrentGame().getCurrentCharacter().getInventory();
        if (inventory.getItems().isEmpty()) return new Result(false, "you have no items in your inventory!");
        return new Result(true, inventory.getItemsInfo());
    }

    public Result inventoryTrash(Matcher matcher) {
        Inventory inventory = App.getCurrentGame().getCurrentCharacter().getInventory();
        String itemName = matcher.group("itemName").trim();
        int number = -1;
        if (matcher.group("number") != null) {
            try {
                number = Integer.parseInt(matcher.group("number"));
            } catch (Exception e) {
                return new Result(false, "please enter a valid number!");
            }
        }
        ItemType item = Item.getItem(itemName);
        if (item == null) return new Result(false, "please enter a valid item!");
        if (number == -1) {
            inventory.removeItem(item);
            return new Result(true, "successfully removed " + itemName + " from your Inventory!");
        } else {
            if (inventory.getCountOfItem(item) == 0)
                return new Result(false, "you don't have such an item in your inventory!");
            if (number > inventory.getCountOfItem(item))
                return new Result(false, "you have less items in your inventory!");
            inventory.removeItem(item, number);
            return new Result(true, "successfully removed " + number + " " + itemName + " from your Inventory!");
        }
    }

    public Result helpRead() {
        return new Result(true, "Tree: T\nOre: O\nForagingItems: I\nCabin Floor: Cf\nCabin wall: Cw\n" +
                "Water: W\nStone: M\nGrass: G\nGreen House Wall: GW\nGreen House Floor: Gf");
    }

    public Result printMap(Matcher matcher) {
        Game game = App.getCurrentGame();
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
        return new Result(true, "energy: " + App.getCurrentGame().getCurrentCharacter().getEnergy());
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
                Tree tree = (Tree) tile.getResource();
                return BROWN + "T " + RESET;
            }
            return GREEN + "G " + RESET;
        } else if (tile.getType().equals(TileType.Stone)) {
            if (tile.getResource() != null) {
                return WHITE + "S " + RESET;
            }
            return GRAY + "M " + RESET;
        }
        if (tile.getType().equals(TileType.Water)) return BLUE + "W " + RESET;
        if (tile.getType().equals(TileType.CabinFloor)) return WHITE + "Cf" + RESET;
        if (tile.getType().equals(TileType.CabinWall)) return YELLOW + "Cw" + RESET;
        if (tile.getType().equals(TileType.BrokenGreenHouse)) return YELLOW + "Gf" + RESET;
        if (tile.getType().equals(TileType.BrokenGreenHouseWall)) return YELLOW + "GW" + RESET;
        if (tile.getType().equals(TileType.Soil)) return BROWN + "So" + RESET;

        return WHITE + "? " + RESET;
    }

    public Result craftInfo(Matcher matcher) {
        String craftName = matcher.group("craftName").trim();
        return new Result(true, "");
    }

    public Result buyAnimal(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        String animalType = matcher.group("animal_type").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character == null) {
            return new Result(false, "Error: No character found.");
        }
       AnimalType Type = Animal.TypeOf(animalType);
        if(character.getMoney() < Type.getPrice()) {
            return new Result(false , "You don't have enough money to buy this animal");
        }
        if (Animal.buy(animalType, App.getCurrentGame().getCurrentCharacter().getUserId(), animalName)) {
            return new Result(true, "Animal created successfully.");
        }
        return new Result(false, "Error: Invalid animal type.");
    }

    public Result pet(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            if (character.getAnimals().get(animalName).pet(character.getX(), character.getY())) {
                return new Result(true, animalName + ": Yeeeeee comon do it");
            }

            return new Result(false, "Go near " + animalName + " you don't hands that long");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result cheatFriendship(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        int amount = Integer.parseInt(matcher.group("amount"));
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            character.getAnimals().get(animalName).petByCheat(amount);
            return new Result(true, "Youre friendship with "+animalName+" is "+amount+" you cheater");
        }
        return new Result(false, "You don't have any " + animalName);

    }

    public Result showanimals(Matcher matcher) {
        App.getCurrentGame().getCurrentCharacter().showAnimals();
        return new Result(true, "\n");
    }

    public Result sheperd(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            if(character.getAnimals().get(animalName).shepherd(x, y)){
                return new Result(true, "");
            }
            return new Result(false, animalName + " cannot go there");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result feedHay(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            character.getAnimals().get(animalName).feed();
                return new Result(true, animalName+": I am well fed yes");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result animalsproductes(Matcher matcher) {
        for(Animal animal: App.getCurrentGame().getCurrentCharacter().getAnimals().values()){
            animal.showproducts();
        }
        return new Result(true, "\n");
    }

    public Result getAnimalProduct(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        Character character = App.getCurrentGame().getCurrentCharacter();
        if (character.getAnimals().containsKey(animalName)) {
            if (character.getAnimals().get(animalName).getProducts()){
                return new Result(true, "You have collected "+animalName+" products");
            }
            return new Result(true, "Cannot collect "+animalName+" products");
        }
        return new Result(false, "You don't have any " + animalName);
    }

    public Result sellAnimal(Matcher matcher) {
        String animalName = matcher.group("animal_name").trim();
        int gained =App.getCurrentGame().getCurrentCharacter().sellAnimal(animalName);
        if(gained>0){
            return new Result(true, "You gained"+gained);
        }
        return new Result(false, "You don't have any " + animalName);
    }
    public Result showShopProducts(){
        Shop currentShop=App.getCurrentGame().getCurrentCharacter().getCurrentShop();
        if(currentShop==null){
            return new Result(false, "You are not in a shop!");
        }
        return new Result(true, currentShop.showAllProducts());
    }
}

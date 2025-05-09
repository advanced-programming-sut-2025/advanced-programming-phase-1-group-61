package controllers;

import models.*;
import models.character.Character;
import models.enums.DaysOfTheWeek;
import models.enums.Season;
import models.enums.WeatherState;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import models.map.Tile;
import models.map.Weather;
import models.character.Inventory;
import models.tool.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameMenuController {
    public Result equipTool(Matcher matcher, Character character) {
        String name=matcher.group("name").trim();
        Tool tool=Tool.fromString(name);
        if(tool==null){
            return new Result(false, "Invalid tool!");
        }
        Inventory inventory =character.getInventory();
        if(!inventory.checkToolInBackPack(tool)){
            return new Result(false,"you don't have the tool in your backpack!");
        }
        character.setTool(tool);
        return new Result(true,name+" has been equipped!");
    }
    public Result showCurrentTool(Character character) {
        Tool tool=character.getCurrentTool();
        if(tool==null){
            return new Result(false,"the thing you have in your hand is not a tool!");
        }
        return new Result(true,tool.getType().toString());
    }
    public Result showAvailableTools(Character character) {
        Inventory inventory =character.getInventory();
        return new Result(true,"you have ("+ inventory.getAllTools()+") in your backpack!");
    }
    public Result upgradeTool(Matcher matcher, Character character) {
        String name=matcher.group("name").trim();
        Tool tool=Tool.fromString(name);
        if(tool==null){
            return new Result(false,"Invalid tool!");
        }
        //other errors will be implemented later
        character.upgradeTool(tool);
        return new Result(true,name+" has been upgraded!");
    }
    public Result startGame(List<String> usernames,int[] mapNumbers){
        List<User> userList =new ArrayList<>();

        if(usernames.isEmpty()){
            return new Result(false , "uou need at least one other player");
        }


        for (String username : usernames) {
            userList.add(User.getUserByUsername(username));
        }
        Map map = MapBuilder.buildFullMap(mapNumbers[0],mapNumbers[1],mapNumbers[2],mapNumbers[3]);
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
            if(user.getGameId() != 0){
                return new Result(false , user.getUsername()+" is already in another game.");
            }
        }
        Game game = new Game(map , characterList);
        for (User user : userList) {
            user.setGameId(game.getId());
        }
        App.addGame(game);
        App.setCurrentGame(game.getId());
        return new Result(true , "game started successfully");
    }
    public Result startGameErrors(List<String> usernames){
        for (int i = 1; i < usernames.size(); i++) {
            String username=usernames.get(i);
            if(username.equals(App.getLoggedInUser().getUsername())){
                return new Result(false , "you can't pick yourself!");
            }
        }
        StringBuilder names=new StringBuilder();
        for(String username : usernames){
            User user=User.getUserByUsername(username.trim());
            if(user==null) names.append(username).append(" is invalid!").append("\n");
        }
        if(!names.isEmpty()) names.deleteCharAt(names.length()-1);
        if(!names.isEmpty()) return new Result(false,names.toString());
        return new Result(true,"");
    }
    public Result userListIsValid(List<String> usernames){
        for (String username : usernames) {
            if(User.getUserByUsername(username)==null){
                return new Result(false , username+"is not valid username");
            }
        }
        return new Result(true , "all players are available");
    }
    public Result loadGame(){
        User user = App.getLoggedInUser();
        if(user.getGameId()==0){
            return new Result(false , "you have to make a new game first");
        }
        Game game = App.getGameByID(user.getGameId());
        if(game==null){
            return new Result(false , "failed to load game");
        }
        App.setCurrentGame(user.getGameId());

        return new Result(true , "game loaded successfully");
    }
    public Result changeTurn(){
        Game game = App.getCurrentGame();
        String string =  game.changeTurn();
        return new Result(true , string);
    }
    public Result showHour(){
        Game game = App.getCurrentGame();
        int hour = game.getDate().getHour();
        return new Result(true , "hour: "+hour);
    }
    public Result showDate(){
        Game game = App.getCurrentGame();
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        return new Result(true , "season: "+season.getDisplayName() + " day: "+day.getDisplayName()
        +" (day count: "+dayCount+" )");
    }
    public Result showDateAndTime(){
        Game game = App.getCurrentGame();
        int dayCount = game.getDate().getDayCounter();
        DaysOfTheWeek day = game.getDate().getDay();
        Season season = game.getDate().getSeason();
        int hour = game.getDate().getHour();
        return new Result(true, "season :"+season.getDisplayName()+"\nday: "+day.getDisplayName()
        +"\nday counter: "+dayCount+"\nhour: "+hour);
    }
    public Result showWeekDay(){
        Game game = App.getCurrentGame();
        DaysOfTheWeek day = game.getDate().getDay();
        return new Result(true , day.getDisplayName());
    }
    public Result cheatHour(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("hour"));
        if(amount<=0){
            return new Result(false , "number has to be positive");
        }

        Game game = App.getCurrentGame();
        if(amount >= 24){
            game.getDate().changeDay(amount/24);
        }
        game.getDate().increaseTime(amount%24);
        return new Result(true , amount+" went by.");
    }
    public Result cheatDay(Matcher matcher){
        int amount = Integer.parseInt(matcher.group("day"));
        Game game = App.getCurrentGame();
        game.getDate().changeDay(amount);
        return new Result(true , amount + "went by.");
    }
    public Result cheatThor(Matcher matcher){
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        App.getCurrentGame().getMap().getWeather().lightning(x , y);
        return new Result(true , "lightning hit at x: "+x + " y: "+y);
    }
    public Result foreCastWeather(){
      WeatherState state = App.getCurrentGame().getMap().getWeather().getTomorrowWeatherState();
      return new Result(true , "tomorrow we expect : "+state.getDisplayName());
    }
    public Result cheatWeatherState(Matcher matcher){
        String type = matcher.group("type");
        WeatherState state = WeatherState.fromDisplayName(type);
        if(state == null){
            return new Result(false , "you cant set weather as "+type);
        }
        Weather weather = App.getCurrentGame().getMap().getWeather();
        weather.setCheatedWeatherState(state);
        return new Result(true , "you can expect "+state.getDisplayName()+" tomorrow.");
    }
    public Result energyResult(Matcher matcher){
        Game game = App.getCurrentGame();
        Character currentCharacter=game.getCurrentCharacter();
        if(currentCharacter==null){
            return new Result(false , "no current character");
        }
        int x= Integer.parseInt(matcher.group("x"));
        int y= Integer.parseInt(matcher.group("y"));
        Tile tile = game.getMap().getTiles()[y][x];
        if(tile == null){
            return new Result(false ,"you cant enter void");
        }
        int turn = tile.getOwnerId();
        if(turn != -1){
            int userId = game.getCharacterByTurnNumber(turn).getUserId();
            if(userId != currentCharacter.getUserId()){
                return new Result(false , "you cant enter someone else's farm");
            }
        }
        int neededEnergy=currentCharacter.getNeededEnergy(x,y);
        if(currentCharacter.isUnlimitedEnergy())
            return new Result(false , "you have unlimited energy! do you want to move?(yes/no)");
        return new Result(true , "you need "+neededEnergy+" energy to move\ndo you want to move?(yes/no)");
    }
    public Result walk(String confirmation){
        if(confirmation.equals("yes")){
            Character character = App.getCurrentGame().getCurrentCharacter();
            character.moveCharacter();
            return new Result(true , "you are now in x:"+character.getX()+" y:"+character.getY());
        }
        return new Result(false , "you did not move");
    }
    public Result energySet(Matcher matcher){
        int value;
        try{
            value = Integer.parseInt(matcher.group("value"));
        }catch (Exception e){
            return new Result(false , "please enter a valid value!");
        }
        App.getCurrentGame().getCurrentCharacter().setEnergy(value);
        return new Result(true,"energy set to: "+value);
    }
    public Result unlimitedEnergySet(){
        Character character = App.getCurrentGame().getCurrentCharacter();
        character.setUnlimitedEnergy(true);
        return new Result(true,"energy set to unlimited!");
    }
    public Result cheatAddItem(Matcher matcher){
        String itemName = matcher.group("itemName");
        int count;
        try {
            count = Integer.parseInt(matcher.group("count"));
        }catch (Exception e){
            return new Result(false , "please enter a valid number!");
        }
        Item item=Item.getItem(itemName);
        if(item==null) return new Result(false , "please enter a valid item!");
        App.getCurrentGame().getCurrentCharacter().getInventory().addItem(item,count);
        return new Result(true,count+ " " + itemName +"s added to Inventory!");
    }
    public Result inventoryShow(){
        Inventory inventory=App.getCurrentGame().getCurrentCharacter().getInventory();
        if(inventory.getItems().isEmpty()) return new Result(false,"you have no items in your inventory!");
        return new Result(true,inventory.getItemsInfo());
    }
    public Result inventoryTrash(Matcher matcher){
        Inventory inventory=App.getCurrentGame().getCurrentCharacter().getInventory();
        String itemName = matcher.group("itemName").trim();
        int number=-1;
        if(matcher.group("number")!=null){
            try{
                number=Integer.parseInt(matcher.group("number"));
            }catch (Exception e){
                return new Result(false , "please enter a valid number!");
            }
        }
        Item item=Item.getItem(itemName);
        if(item==null) return new Result(false , "please enter a valid item!");
        if(number==-1) {
            inventory.removeItem(item);
            return new Result(true,"successfully removed "+itemName+" from your Inventory!");
        }
        else {
            if(number>inventory.getCountOfItem(item))
                return new Result(false,"you have less items in your inventory!");
            inventory.removeItem(item, number);
            return new Result(true,"successfully removed "+number+" "+itemName+" from your Inventory!");
        }
    }
}

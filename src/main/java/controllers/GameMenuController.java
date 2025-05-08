package controllers;

import models.App;
import models.Game;
import models.Result;
import models.User;
import models.character.Character;
import models.enums.DaysOfTheWeek;
import models.enums.Season;
import models.enums.WeatherState;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
import models.map.Weather;
import models.tool.Backpack;
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
        Backpack backpack=character.getBackpack();
        if(!backpack.checkToolInBackPack(tool)){
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
        Backpack backpack=character.getBackpack();
        return new Result(true,"you have ("+backpack.getAllTools()+") in your backpack!");
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
        for (User user : userList) {
            Character character = new Character(user.getId());
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
}

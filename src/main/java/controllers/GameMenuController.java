package controllers;

import models.App;
import models.Game;
import models.Result;
import models.User;
import models.character.Character;
import models.map.Map;
import models.map.MapCreator.MapBuilder;
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

        for (String username : usernames) {
            userList.add(User.getUserByUsername(username));
        }
        Map map = MapBuilder.buildFullMap(mapNumbers[0],mapNumbers[1],mapNumbers[2],mapNumbers[3]);
        List<Character> characterList = new ArrayList<>();
        for (User user : userList) {
            characterList.add(new Character(user.getId()));
        }
        Game game = new Game(map , characterList);
        for (User user : userList) {
            user.setGameId(game.getId());
        }
        App.addGame(game);
        App.setCurrentGame(game.getId());
        return new Result(true , "game started successfully");
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
}

package controllers;

import models.Game;
import models.Result;
import models.User;
import models.character.Character;
import models.enums.Commands.RegisterMenuCommands;
import models.map.Map;
import models.map.MapCreator.MapBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class GameMenuController {
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
}

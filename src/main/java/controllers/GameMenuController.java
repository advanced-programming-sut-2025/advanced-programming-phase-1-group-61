package controllers;

import models.Result;
import models.User;
import models.character.Character;
import models.tool.Backpack;
import models.tool.Tool;

import java.util.regex.Matcher;

public class GameMenuController {
    public static Result equipTool(Matcher matcher, Character character) {
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
}

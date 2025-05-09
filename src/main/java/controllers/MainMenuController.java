package controllers;

import models.App;
import models.Result;
import models.enums.Menu;

import java.util.regex.Matcher;

public class MainMenuController {
    public Result logout(){
        App.setLoggedInUser(-1);
        return new Result(true , "successfully logged out");
    }
    public Result changeMenu(Matcher matcher){
        String name= matcher.group("menu");
        if(name.equals("game")){
            App.setCurrentMenu(Menu.GAME_MENU);
            return new Result(true , "Redirecting to gameMenu...");
        } else if (name.equals("profile")) {
            App.setCurrentMenu(Menu.PROFILE_MENU);
            return new Result(true , "Redirecting to profileMenu...");
        }
        return new Result(true , "invalid menu");
    }
}

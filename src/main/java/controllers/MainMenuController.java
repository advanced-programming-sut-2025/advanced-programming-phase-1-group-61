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
}

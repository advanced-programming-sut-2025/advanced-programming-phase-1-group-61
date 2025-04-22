package controllers;

import models.App;
import models.Result;
import models.User;

import java.util.regex.Matcher;

public class LoginMenuController {
    public Result login(Matcher login){
        String username=login.group("username");
        String password=login.group("password");
        User user=User.getUserByUsername(username);
        if(user==null){
            return new Result(false , username+" dose not exist");
        }

        //TODO


        App.setLoggedInUser(user);
        return new Result(true , username + "logged in");
    }
}
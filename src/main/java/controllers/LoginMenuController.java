package controllers;

import models.User;

import java.util.regex.Matcher;

public class LoginMenuController {
    public static void login(Matcher login){
        String username=login.group("username");
        String password=login.group("password");
        User user=User.getUserByUsername(username);
        if(user==null){
            
        }
    }
}
package controllers;

import models.App;
import models.Result;
import models.User;
import models.enums.Commands.RegisterMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenuController {
    public static Result register(Matcher register){
        String username=register.group("username");
        String password=register.group("password");
        String confirmPassword=register.group("passwordConfirm");
        String nickname=register.group("nickname");
        String email=register.group("email");
        String gender=register.group("gender");
        User user=User.getUserByUsername(username);
        if(user!=null){
            return new Result(false , "Username already exists");
        }
        if(username.length() > 8){
            return new Result(false , "username format is invalid!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(), email)){
            return new Result(false , "email format is invalid!");
        }
        if(password.length()<8){
            return new Result(false , "password is too short!");
        }

        if(!Pattern.matches(RegisterMenuCommands.PASSWORD_PATTERN.getPattern(), password)){
            return new Result(false , "password has invalid format");
        }

        if(!password.equals(confirmPassword)){
            return new Result(false , "repeated password is wrong");
        }

        if(!gender.equals("male") && !gender.equals("female")){
            return new Result(false , "you cant choose "+gender+" as your gender!\n(male\\female)");
        }

        App.addUserToList(new User(username , email , password ,gender));
        return new Result(true , username+" successfully registered!");
    }
}

package controllers;

import models.App;
import models.Result;
import models.SHA256;
import models.User;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Gender;

import java.io.IOException;
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
            return new Result(false , "username too long");
        }
        if(!Pattern.matches(RegisterMenuCommands.USERNAME_PATTERN.getPattern(),username)){
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

        Gender gender1 = Gender.getGender(gender);

        if(gender1==null){
            return new Result(false , "you cant choose "+gender+" as your gender!\n(male\\female)");
        }

        App.addUserToList(new User(username , email , SHA256.toSHA256(password) ,gender,nickname));
        return new Result(true , username+" successfully registered!");
    }
    public static Result exitGame(){
        try {
            App.saveApp();
        } catch (IOException e) {
            return new Result(false , "failed to save App :)");
        }
        return new Result(true , "App saved successfully(hopefully)");
    }
}

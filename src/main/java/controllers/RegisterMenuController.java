package controllers;

import models.User;
import models.enums.Commands.RegisterMenuCommands;

import java.util.regex.Matcher;

public class RegisterMenuController {
    public static boolean register(Matcher register){
        String username=register.group("username");
        String password=register.group("password");
        String confirmPassword=register.group("password_confirm");
        String nickname=register.group("nickname");
        String email=register.group("email");
        String gender=register.group("gender");
        User user=User.getUserByUsername(username);
        if(user!=null){
            System.out.println("Username already exists");
        }
        if(!RegisterMenuCommands.checkUsername(username)){
            System.out.println("username format is invalid!");
            return false;
        }
        if(!RegisterMenuCommands.checkEmail(email)){
            System.out.println("email format is invalid!");
            return false;
        }
        if(password.length()<8){
            System.out.println("password is too short!");
            return false;
        }
        if(RegisterMenuCommands.checkPasswordSmallLetter(password)){
            System.out.println("password does not contain small letter!");
            return false;
        }
        if(RegisterMenuCommands.checkPasswordCapitalLetter(password)){
            System.out.println("password does not contain capital letter!");
            return false;
        }
        if(RegisterMenuCommands.checkPasswordNumber(password)){
            System.out.println("password does not contain number!");
            return false;
        }
        if(RegisterMenuCommands.checkPasswordUniqueLetter(password)){
            System.out.println("password does not contain unique letter!");
            return false;
        }
        return true;
    }
}

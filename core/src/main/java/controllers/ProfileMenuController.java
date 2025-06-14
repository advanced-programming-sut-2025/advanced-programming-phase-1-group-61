package controllers;

import models.Result;
import models.SHA256;
import models.User;
import models.enums.Commands.RegisterMenuCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {
    public static Result changeUsername(Matcher matcher, User currentUser){
        String username=matcher.group("username").trim();
        if(username.equals(currentUser.getUsername())){
            return new Result(false,"new username must be different!");
        }
        User user=User.getUserByUsername(username);
        if(user!=null){
            return new Result(false,"username already exists!");
        }
        if(!Pattern.matches(RegisterMenuCommands.USERNAME_PATTERN.getPattern(),username)){
            return new Result(false,"username format is wrong!");
        }
        currentUser.setUsername(username);
        return new Result(true,"username has been changed!");
    }
    public static Result changeNickname(Matcher matcher, User currentUser){
        String nickname=matcher.group("nickname").trim();
        if(nickname.equals(currentUser.getUsername())){
            return new Result(false,"new nickname must be different!");
        }
        if(nickname.isEmpty()){
            return new Result(false,"nickname cannot be empty!");
        }
        currentUser.setNickName(nickname);
        return new Result(true,"nickname has been changed!");
    }
    public static Result changeEmail(Matcher matcher, User currentUser){
        String email=matcher.group("email").trim();
        if(email.equals(currentUser.getEmail())){
            return new Result(false,"new email must be different!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(),email)){
            return new Result(false,"email format is wrong!");
        }
        currentUser.setEmail(email);
        return new Result(true,"email has been changed!");
    }
    public static Result changePassword(Matcher matcher, User currentUser){
        String newPassword=matcher.group("newPassword").trim();
        String oldPassword=matcher.group("oldPassword").trim();
        if(newPassword.equals(oldPassword)){
            return new Result(false,"new password must be different!");
        }
        if(!currentUser.getPassword().equals(SHA256.toSHA256(oldPassword))){
            return new Result(false,"old password is wrong!");
        }
        if(newPassword.length()<8){
            return new Result(false , "password is too short!");
        }
        if(!RegisterMenuController.checkPasswordLowerCaseLetter(newPassword)){
            return new Result(false , "password does not contain lowercase letters!");
        }
        if(!RegisterMenuController.checkPasswordUpperCaseLetter(newPassword)){
            return new Result(false , "password does not contain uppercase letters!");
        }
        if(!RegisterMenuController.checkPasswordDigit(newPassword)){
            return new Result(false , "password does not contain any numbers!");
        }
        if(!RegisterMenuController.checkPasswordUniqueLetters(newPassword)){
            return new Result(false , "password does not contain unique letters!");
        }
        if(!Pattern.matches(RegisterMenuCommands.PASSWORD_PATTERN.getPattern(),newPassword)){
            return new Result(false,"password format is wrong!");
        }
        currentUser.setPassword(SHA256.toSHA256(newPassword));
        return new Result(true,"password has been changed!");
    }
    public static Result userInfo(User currentUser){
        String username=currentUser.getUsername();
        String nickname=currentUser.getNickName();
        int games=currentUser.getGamesPlayed();
        int mostMoneyGot=0;
        String builder = "username: " + username + "\n" +
                "nickname: " + nickname + "\n" +
                "gamesPlayed: " + games + "\n"
                + "most money got: " + mostMoneyGot + "\n";
        return new Result(true, builder);
    }
}

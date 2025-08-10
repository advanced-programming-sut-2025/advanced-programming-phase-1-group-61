package controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.camera.Main;
import models.*;
import models.enums.Commands.RegisterMenuCommands;
import views.PreGameMenu;
import views.ProfileMenu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {
    private final ProfileMenu view;
    public ProfileMenuController(ProfileMenu view) {
        this.view = view;
    }
    public Result changeUsername(String username, User currentUser){
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
    public Result changeNickname(String nickname, User currentUser){
        if(nickname.equals(currentUser.getUsername())){
            return new Result(false,"new nickname must be different!");
        }
        if(nickname.isEmpty()){
            return new Result(false,"nickname cannot be empty!");
        }
        currentUser.setNickName(nickname);
        return new Result(true,"nickname has been changed!");
    }
    public Result changeEmail(String email, User currentUser){
        if(email.equals(currentUser.getEmail())){
            return new Result(false,"new email must be different!");
        }
        if(!Pattern.matches(RegisterMenuCommands.EMAIL_PATTERN.getPattern(),email)){
            return new Result(false,"email format is wrong!");
        }
        currentUser.setEmail(email);
        return new Result(true,"email has been changed!");
    }
    public Result changePassword(String newPassword,String oldPassword, User currentUser){
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
    public Result userInfo(User currentUser){
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
    public void addMainTableListeners(){
        view.getChangeUsername().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                view.setUpUsernameUI();
            }
        });
        view.getChangeNickname().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                view.setUpNicknameUI();
            }
        });
        view.getChangeEmail().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                view.setUpEmailUI();
            }
        });
        view.getChangePassword().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                view.setUpPasswordUI();
            }
        });
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
            }
        });
        view.getBackToProfile().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                view.setUpMainUI();
            }
        });
    }
    public void submitUsername(){
        view.getSubmitUsername().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                Result res=changeUsername(view.getUsernameField().getText(),Main.getApp().getLoggedInUser());
                AlertGenerator.showAlert(res.isSuccessful()?"Success":"failed",res.message(),view.getStage());
            }
        });
    }
    public void submitPassword(){
        view.getSubmitPassword().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                Result res=changePassword(view.getPasswordField().getText(),view.getOldPasswordField().getText(),Main.getApp().getLoggedInUser());
                AlertGenerator.showAlert(res.isSuccessful()?"Success":"failed",res.message(),view.getStage());
            }
        });
    }
    public void submitEmail(){
        view.getSubmitEmail().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                Result res=changeEmail(view.getEmailField().getText(),Main.getApp().getLoggedInUser());
                AlertGenerator.showAlert(res.isSuccessful()?"Success":"failed",res.message(),view.getStage());
            }
        });
    }
    public void submitNickname(){
        view.getSubmitNickname().addListener(new ClickListener(){
            public void clicked(InputEvent e,float x,float y) {
                AssetManager.getUiClicks().play();
                Result res=changeNickname(view.getNicknameField().getText(),Main.getApp().getLoggedInUser());
                AlertGenerator.showAlert(res.isSuccessful()?"Success":"failed",res.message(),view.getStage());
            }
        });
    }
    public void userInfo(){
        view.getUserInfo().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e,float x, float y) {
                AssetManager.getUiClicks().play();
                AlertGenerator.showAlert("",userInfo(Main.getApp().getLoggedInUser()).message(),view.getStage());
            }
        });
    }
    public void handle(){
        addMainTableListeners();
        submitUsername();
        submitPassword();
        submitEmail();
        submitNickname();
        userInfo();
    }
}

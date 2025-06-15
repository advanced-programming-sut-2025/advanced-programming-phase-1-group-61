package controllers;

import io.github.camera.Main;
import models.Result;
import views.LoginMenu;
import views.MainMenu;
import views.RegisterMenu;



public class MainMenuController {
    private MainMenu view;

    public void setView(MainMenu view) {
        this.view = view;
    }

    public Result register(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new RegisterMenu(new RegisterMenuController()));
        return new Result(true , "successfully logged out");
    }
    public Result logIn(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new LoginMenu(new LoginMenuController()));
        return new Result(true , "invalid menu");
    }
}

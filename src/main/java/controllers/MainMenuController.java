package controllers;

import models.App;
import models.enums.Menu;

import java.util.regex.Matcher;

public class MainMenuController {
    public static void changeMenu(Matcher changeMenu){
        String menuName = changeMenu.group("menu");
        if(menuName.equals("game")){
            App.setCurrentMenu(Menu.GAME_MENU);
        }
        if(menuName.equals("profile")){
            App.setCurrentMenu(Menu.PROFILE_MENU);
        }
    }
}

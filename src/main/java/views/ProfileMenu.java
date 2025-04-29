package views;

import controllers.ProfileMenuController;
import models.App;
import models.Result;
import models.enums.Commands.ProfileMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        if(input.equals("user info")){
            Result result=ProfileMenuController.userInfo(App.getLoggedInUser());
            System.out.println(result.message());
            return;
        } else if (input.equals("back")) {
            App.setCurrentMenu(Menu.MAIN_MENU);
            return;
        }
        Matcher changeUsername= ProfileMenuCommands.CHANGE_USERNAME.getMatcher(input);
        Matcher changePassword= ProfileMenuCommands.CHANGE_PASSWORD.getMatcher(input);
        Matcher changeEmail= ProfileMenuCommands.CHANGE_EMAIL.getMatcher(input);
        Matcher changeNickname= ProfileMenuCommands.CHANGE_NICKNAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);

        if(changeUsername!=null){
            Result result= ProfileMenuController.changeUsername(changeUsername, App.getLoggedInUser());
            System.out.println(result.message());
        } else if (showCurrentMenu != null) {
            System.out.println("you are in profile menu");
        } else if(changePassword!=null){
            Result result= ProfileMenuController.changePassword(changePassword, App.getLoggedInUser());
            System.out.println(result.message());
        }
        else if(changeEmail!=null){
            Result result= ProfileMenuController.changeEmail(changeEmail, App.getLoggedInUser());
            System.out.println(result.message());
        }
        else if(changeNickname!=null){
            Result result= ProfileMenuController.changeNickname(changeNickname, App.getLoggedInUser());
            System.out.println(result.message());
        }
        else {
            System.out.println("Invalid input");
        }
    }
}

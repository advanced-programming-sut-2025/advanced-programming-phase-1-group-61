package views;

import controllers.RegisterMenuController;
import models.App;
import models.Result;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {

        String input=scanner.nextLine().trim();

        Matcher Register= RegisterMenuCommands.Register.getMatcher(input);
        Matcher goToLogInMenu = RegisterMenuCommands.GO_TO_LOGIN_MENU.getMatcher(input.toLowerCase());
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);

        if(Register != null){
            Result result = RegisterMenuController.register(Register);
            System.out.println(result.message());
        } else if (goToLogInMenu != null) {
            App.setCurrentMenu(Menu.LOGIN_MENU);
        } else if (showCurrentMenu != null) {
            System.out.println("you are in register menu");
        } else{
            System.out.println("Invalid input");
        }

    }
}

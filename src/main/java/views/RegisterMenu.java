package views;

import controllers.RegisterMenuController;
import models.App;
import models.Result;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
            String input = scanner.nextLine().trim();

            Matcher Register = RegisterMenuCommands.Register.getMatcher(input);
            Matcher goToLogInMenu = RegisterMenuCommands.GO_TO_LOGIN_MENU.getMatcher(input.toLowerCase());
            Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
            Matcher leaveGame = RegisterMenuCommands.LEAVE_GAME.getMatcher(input);
            Matcher RegisterWithRandomPassword=RegisterMenuCommands.RegisterWithRandomPass.getMatcher(input);
            Matcher pickQuestion=RegisterMenuCommands.PICK_QUESTION.getMatcher(input);
            if (Register != null) {
                Result result = RegisterMenuController.register(Register);
                System.out.println(result.message());
            }
            else if(RegisterWithRandomPassword!=null){
                Result result = RegisterMenuController.registerWithRandomPassword(RegisterWithRandomPassword);
                System.out.println(result.message());
            }
            else if(pickQuestion!=null){

            }
            else if (goToLogInMenu != null) {
                System.out.println("Redirecting to LoginMenu...");
                App.setCurrentMenu(Menu.LOGIN_MENU);
            } else if (showCurrentMenu != null) {
                System.out.println("you are in register menu");
            } else if (leaveGame != null) {
                Result result = RegisterMenuController.exitGame();
                System.out.println(result.message());
                App.setCurrentMenu(Menu.EXIT_MENU);
            } else {
                System.out.println("Invalid input");
            }

    }
}

package views;

import controllers.LoginMenuController;
import models.App;
import models.Result;
import models.enums.Commands.LoginMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.MenuEnum;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu{
    LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
            String input = scanner.nextLine();
            Matcher login= LoginMenuCommands.LOGIN.getMatcher(input);
            Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
            Matcher goToRegisterMenu = LoginMenuCommands.GO_BACK.getMatcher(input);

            if(login != null){
                Result result = controller.login(login);
                System.out.println(result.message());
                if(result.isSuccessful()){
                    App.setCurrentMenu(MenuEnum.MAIN_MENU);
                }
            } else if (showCurrentMenu != null) {
                System.out.println("you are in login menu");
            } else if (goToRegisterMenu != null) {
                App.setCurrentMenu(MenuEnum.REGISTER_MENU);
                System.out.println("you are now in register menu");
            } else {
                System.out.println("invalid input");
            }

    }
}

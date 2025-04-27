package views;

import controllers.MainMenuController;
import models.App;
import models.Result;
import models.enums.Commands.MainMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu{

    private final MainMenuController controller = new MainMenuController();
    @Override
    public void check(Scanner scanner) {
            String input = scanner.nextLine().trim();

            Matcher changeMenu= MainMenuCommands.ChangeMenu.getMatcher(input);
            Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
            Matcher logOut = MainMenuCommands.LOG_OUT.getMatcher(input);

            if(changeMenu != null){
               String name= changeMenu.group("menu");
               if(name.equals("game")){
                   App.setCurrentMenu(Menu.GAME_MENU);
               }
            } else if (showCurrentMenu != null) {
                System.out.println("you are in main menu");
            } else if (logOut != null) {
               Result result = controller.logout();
               if(result.isSuccessful()){
                   App.setCurrentMenu(Menu.LOGIN_MENU);
               }
            } else {
                System.out.println("Invalid input");
            }
        }
       

}

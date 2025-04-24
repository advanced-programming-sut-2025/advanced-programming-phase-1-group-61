package views;

import controllers.MainMenuController;
import models.enums.Commands.MainMenuCommands;
import models.enums.Commands.RegisterMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        while(true){
            String input = scanner.nextLine();
            Matcher changeMenu= MainMenuCommands.getMatcher(input,MainMenuCommands.ChangeMenu);
            Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);

            if(changeMenu.matches()){
                MainMenuController.changeMenu(changeMenu);
            } else if (showCurrentMenu != null) {
                System.out.println("you are in main menu");
            } else {
                System.out.println("Invalid input");
            }
        }
       
    }
}

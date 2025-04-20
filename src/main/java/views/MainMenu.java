package views;

import controllers.MainMenuController;
import models.enums.Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        while(true){
            String input = scanner.nextLine();
            Matcher changeMenu= MainMenuCommands.getMatcher(input,MainMenuCommands.ChangeMenu);
            if(changeMenu.matches()){
                MainMenuController.changeMenu(changeMenu);
            }
            else {
                System.out.println("Invalid input");
            }
        }
       
    }
}

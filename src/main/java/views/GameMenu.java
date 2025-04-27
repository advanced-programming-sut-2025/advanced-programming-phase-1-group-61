package views;

import models.enums.Commands.GameMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.map.MapCreator.MapBuilder;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
       String input = scanner.nextLine();
        Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);


        if(start!= null){
            StringBuilder city = MapBuilder.buildFullMap(1,2,1,2);
            System.out.println(city);
        } else if (showCurrentMenu != null) {
            System.out.println("you are in game");
        } else {
            System.out.println("invalid command");
        }

    }
}

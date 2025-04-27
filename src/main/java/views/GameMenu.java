package views;

import models.Game;
import models.character.Character;
import models.enums.Commands.GameMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.map.Map;
import models.map.MapCreator.MapBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
       String input = scanner.nextLine();
        Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);



        if(start!= null){
            int[] mapNumber = new int[4];
            int i = 0;
            do{
                System.out.println("choosing map for player number "+i+1);
                String mapChoosing = scanner.nextLine();
                Matcher chooseMap = GameMenuCommands.CHOOSE_MAP.getMatcher(mapChoosing);
                if(chooseMap != null){
                    int number = Integer.parseInt(chooseMap.group("number"));
                    if(number>2 || number <= 0){
                        System.out.println("unavailable map number");
                    }else {
                        mapNumber[i] = number;
                        i++;
                    }
                }else {
                    System.out.println("you have to choose map using command: game map <number>");
                }
            }while (i<4);

            Map map = MapBuilder.buildFullMap(mapNumber[0], mapNumber[1],mapNumber[2],mapNumber[3]);
            System.out.println("map created successfully.");
        } else if (showCurrentMenu != null) {
            System.out.println("you are in game");
        } else {
            System.out.println("invalid command");
        }

    }
}

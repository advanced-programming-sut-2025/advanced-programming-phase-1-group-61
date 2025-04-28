package views;

import controllers.GameMenuController;
import models.Game;
import models.RandomNumber;
import models.Result;
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
   private final GameMenuController controller = new GameMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
        Matcher equipTool=GameMenuCommands.EQUIP_TOOL.getMatcher(input);


        if(start!= null){
            List<String> usernames = new ArrayList<>();

            for (int i = 1; i <= 3; i++) {
                String user = start.group(i);
                if (user != null) {
                    usernames.add(user);
                }
            }

            Result userValidation = controller.userListIsValid(usernames);
            if(userValidation.isSuccessful()){
                int[] mapNumber = new int[4];
                int i = 0;
                while (i<usernames.size()){
                    System.out.println("choosing map for "+usernames.get(i));
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
                }

                while (i < 4){
                    mapNumber[i] = RandomNumber.getRandomNumberWithBoundaries(1,3);
                    i++;
                }
                Result result = controller.startGame(usernames,mapNumber);
                System.out.println(result.message());
            }else {
                System.out.println(userValidation.message());
            }
        }
        else if (showCurrentMenu != null) {
            System.out.println("you are in game");
        }
        else if (equipTool != null) {

        } else {
            System.out.println("invalid command");
        }

    }
}
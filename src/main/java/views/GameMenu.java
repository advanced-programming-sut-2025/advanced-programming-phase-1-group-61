package views;

import controllers.GameMenuController;
import models.App;
import models.Game;
import models.RandomNumber;
import models.Result;
import models.character.Character;
import models.enums.Commands.GameMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;
import models.map.Map;
import models.map.MapCreator.MapBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{
   private final GameMenuController controller = new GameMenuController();
   private boolean inGame = false;
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
        Matcher equipTool=GameMenuCommands.EQUIP_TOOL.getMatcher(input);
        Matcher exitGame = RegisterMenuCommands.LEAVE_GAME.getMatcher(input);
        Matcher loadGame = GameMenuCommands.LOAD_GAME.getMatcher(input);

        if(!inGame){
            if (showCurrentMenu != null){
                System.out.println("you are in game menu start a game to enter game");
            }
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
                    if(result.isSuccessful()){
                        inGame = true;
                    }
                }else {
                    System.out.println(userValidation.message());
                }
            }
            else if (loadGame != null){
            Result result = controller.loadGame();
            System.out.println(result.message());
                if(result.isSuccessful()){
                    inGame = true;
                }
            }
            else{
                System.out.println("invalid command");
            }
        }else {
            if (showCurrentMenu != null){
                System.out.println("you are in game");
            }
            if (equipTool != null){

            }else if (exitGame != null){
                try {
                    App.saveApp();
                } catch (IOException e) {
                    System.out.println("failed to save app");
                }
                App.setCurrentMenu(Menu.EXIT_MENU);
            }
            else{
                System.out.println("invalid command");
            }
        }



    }
}
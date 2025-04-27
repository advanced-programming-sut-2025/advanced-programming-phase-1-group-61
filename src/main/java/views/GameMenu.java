package views;

import controllers.GameMenuController;
import models.Result;
import models.character.Character;
import models.enums.Commands.GameMenuCommands;
import models.enums.Commands.RegisterMenuCommands;
import models.map.MapCreator.MapBuilder;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher start = GameMenuCommands.START_GAME.getMatcher(input);
        Matcher showCurrentMenu = RegisterMenuCommands.SHOW_CURRENT_MENU.getMatcher(input);
        Matcher equipTool=GameMenuCommands.EQUIP_TOOL.getMatcher(input);
        Matcher showCurrentTool=GameMenuCommands.SHOW_CURRENT_TOOL.getMatcher(input);
        Matcher showAvailableTools=GameMenuCommands.TOOLS_SHOW_AVAILABLE.getMatcher(input);
        Matcher upgradeTool=GameMenuCommands.TOOLS_UPGRADE.getMatcher(input);
        Matcher useTool=GameMenuCommands.TOOLS_USE.getMatcher(input);

        if(start!= null){
            StringBuilder city = MapBuilder.buildFullMap(1,2,1,2);
            System.out.println(city);
        } else if (showCurrentMenu != null) {
            System.out.println("you are in game");
        }
        else if (equipTool != null) {
            //character should be implemented for this part!
//            Result result= GameMenuController.equipTool(equipTool,);
//            System.out.println(result.message());
        } else if (showCurrentTool!=null){

        } else if(showAvailableTools!=null){

        } else if(upgradeTool!=null){

        } else if(useTool!=null) {

        }else {
            System.out.println("invalid command");
        }

    }
}

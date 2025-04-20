package views;

import models.enums.Commands.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        while(true){
            String input = scanner.nextLine();
            Matcher login= LoginMenuCommands.getMatcher(input,LoginMenuCommands.Login);
            if(login.matches()){

            }
            else {
                System.out.println("invalid input");
            }
        }
    }
}

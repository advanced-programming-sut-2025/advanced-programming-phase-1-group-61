package views;

import controllers.RegisterMenuController;
import models.App;
import models.enums.Commands.RegisterMenuCommands;
import models.enums.Menu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu implements AppMenu{
    @Override
    public void check(Scanner scanner) {
        while(true){
            String input=scanner.nextLine();
            if(input.equals("back")) break;
            Matcher Register= RegisterMenuCommands.getMatcher(input,RegisterMenuCommands.Register);
            if(Register.matches()){
                if(!RegisterMenuController.register(Register)){
                    String password=Register.group("password");
                    String confirmPassword=Register.group("password_confirm");
                    while(!password.equals(confirmPassword)){
                        System.out.println("confirm password is incorrect! please reEnter it!");
                        confirmPassword=scanner.nextLine();
                    }
                }
            }
            else{
                System.out.println("Invalid input");
            }
        }
    }
}

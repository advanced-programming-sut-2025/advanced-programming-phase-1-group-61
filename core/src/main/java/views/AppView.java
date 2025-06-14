package views;

import models.App;
import models.enums.MenuEnum;

import java.util.Scanner;

public class AppView {
    private static final Scanner scanner = new Scanner(System.in);
    public void run(){
        App.loadApp();
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != MenuEnum.EXIT_MENU);
    }
}

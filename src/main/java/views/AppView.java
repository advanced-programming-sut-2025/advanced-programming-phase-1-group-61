package views;

import models.App;
import models.enums.Menu;

import java.util.Scanner;

public class AppView {
    private static final Scanner scanner = new Scanner(System.in);
    public void run(){
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.EXIT_MENU);
    }
}

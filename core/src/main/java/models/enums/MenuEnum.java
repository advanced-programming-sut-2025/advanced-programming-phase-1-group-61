package models.enums;

import views.*;

import java.util.Scanner;

public enum MenuEnum {
    MAIN_MENU((AppMenu) new MainMenu()),
    LOGIN_MENU((AppMenu) new LoginMenu()),
    GAME_MENU((AppMenu) new GameMenu()),
    STORE_MENU((AppMenu) new ShopMenu()),
    USER_MENU((AppMenu) new InventoryMenu()),
    PROFILE_MENU((AppMenu) new ProfileMenu()),
    REGISTER_MENU((AppMenu) new RegisterMenu()),
    EXIT_MENU((AppMenu) new ExitMenu());

    private final AppMenu menu;

    MenuEnum(AppMenu menu) {
        this.menu = menu;
    }

    public AppMenu getMenu() {
        return menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }
}

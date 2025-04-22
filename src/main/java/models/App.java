package models;

import com.google.gson.Gson;
import models.enums.Menu;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static User loggedInUser;
    private static Menu currentMenu = Menu.REGISTER_MENU;
    private static  List<User> allUsers = new ArrayList<>();
    private ArrayList<Game> allGames = new ArrayList<>();

    public void saveApp(){
        Gson gson = new Gson();
    }

    public static void setLoggedInUser(User user){
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
    public static void addUserToList(User user){
        allUsers.add(user);
    }
    public static Menu getCurrentMenu() {
        return currentMenu;
    }

}

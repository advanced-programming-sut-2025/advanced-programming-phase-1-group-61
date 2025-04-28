package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.enums.Menu;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static User loggedInUser;
    private static User registeredUser;
    private static Menu currentMenu = Menu.REGISTER_MENU;
    private static  List<User> allUsers = new ArrayList<>();
    private static ArrayList<Game> allGames = new ArrayList<>();
    private static int currentGameId;


    public static Game getCurrentGame(){
        for (Game game : allGames) {
            if(game.getId() == currentGameId){
                return game;
            }
        }
        return null;
    }
    public static void setCurrentGame(int id ){
        currentGameId = id;
    }
    public static void addGame(Game game){
        allGames.add(game);
    }

    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("users.json");
        gson.toJson(allUsers , fileWriter);
        fileWriter.close();
        FileWriter fileWriter1 = new FileWriter("games.json");
        gson.toJson(allGames , fileWriter1);
        fileWriter1.close();
    }
    public static void loadApp() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File userFile = new File("users.json");
        File gameFile = new File("games.json");
        File loggedInUser = new File("loggedInUser.json");

        if (!userFile.exists()) {
            allUsers = new ArrayList<>();
            return;
        }
        if(!gameFile.exists()){
            allGames = new ArrayList<>();
            return;
        }

        try {
            FileReader fileReader = new FileReader(userFile);
            Type userListType = new TypeToken<List<User>>() {}.getType();
            allUsers = gson.fromJson(fileReader, userListType);

            if (allUsers == null) {
                allUsers = new ArrayList<>();
            }



            fileReader.close();
            System.out.println("users loaded successfully " + allUsers.size());

            FileReader gameFileReader = new FileReader(gameFile);
            Type gameListType = new TypeToken<List<Game>>() {}.getType();
            allGames = gson.fromJson(gameFileReader , gameListType);
            if(allGames == null){
                allGames = new ArrayList<>();
            }
            gameFileReader.close();
            System.out.println("games loaded successfully "+allGames.size());

            if(loggedInUser.exists()){
                FileReader loggedInUserFileReader = new FileReader(loggedInUser);
                Type loggedInUserType = new TypeToken<User>() {}.getType();
                User user = gson.fromJson(loggedInUserFileReader , loggedInUserType);
                if(user != null){
                App.setLoggedInUser(user);
                App.setCurrentMenu(Menu.MAIN_MENU);
                }
                System.out.println("welcome back "+user.getNickName());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error raeding games/users file");
            allUsers = new ArrayList<>();
        }
    }
    public static void setLoggedInUser(User user){
        loggedInUser = user;
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static void setRegisteredUser(User user){
        registeredUser = user;
    }
    public static User getRegisteredUser() {
        return registeredUser;
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

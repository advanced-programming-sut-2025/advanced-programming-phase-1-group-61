package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.character.Character;
import models.enums.Menu;
import models.enums.TileType;
import models.enums.ToolType;
import models.resource.Resource;
import models.tool.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static int loggedInUser;
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

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }
    public static Game getGameByID(int id){
        for (Game game : allGames) {
            if(game.getId()==id){
                return game;
            }
        }
        return null;
    }

    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Tool.class, new ToolAdapter())
                .registerTypeAdapter(Resource.class, new ResourceAndBuildingAdapter())
                .setPrettyPrinting()
                .create();



        try (FileWriter fileWriter = new FileWriter("users.json")) {
            gson.toJson(allUsers, fileWriter);
        }


        try (FileWriter fileWriter1 = new FileWriter("games.json")) {
            gson.toJson(allGames, fileWriter1);
        }


        File loggedInUserFile = new File("loggedInUser.json");
        if (loggedInUserFile.exists()) {
            try (FileWriter fileWriter2 = new FileWriter(loggedInUserFile.getName())) {
                gson.toJson(loggedInUser, fileWriter2);
            }
        }
    }

    public static void loadApp() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Tool.class, new ToolAdapter())
                .registerTypeAdapter(Resource.class, new ResourceAndBuildingAdapter())
                .setPrettyPrinting()
                .create();


        File userFile = new File("users.json");
        File gameFile = new File("games.json");
        File loggedInUserFile = new File("loggedInUser.json");

        try {

            if (userFile.exists()) {
                try (FileReader fileReader = new FileReader(userFile)) {
                    Type userListType = new TypeToken<List<User>>() {}.getType();
                    allUsers = gson.fromJson(fileReader, userListType);
                }
            }


            if (gameFile.exists()) {
                try (FileReader gameFileReader = new FileReader(gameFile)) {
                    Type gameListType = new TypeToken<List<Game>>() {}.getType();
                    allGames = gson.fromJson(gameFileReader, gameListType);
                }
            }
            System.out.println("loaded games:"+allGames.size());
            System.out.println("loaded users:"+allUsers.size());

            if (loggedInUserFile.exists()) {
                try (FileReader loggedInUserFileReader = new FileReader(loggedInUserFile)) {
                    Type loggedInUserType = new TypeToken<Integer>() {}.getType();
                    loggedInUser = gson.fromJson(loggedInUserFileReader, loggedInUserType);
                    App.setCurrentMenu(Menu.MAIN_MENU);
                    System.out.println("welcome back "+getLoggedInUser().getUsername());
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading files");
        }
    }

    public static void setLoggedInUser(int user){
        loggedInUser = user;
    }
    public static User getLoggedInUser() {
        for (User user : allUsers) {
            if(user.getId() == loggedInUser){
                return user;
            }
        }
        return null;
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

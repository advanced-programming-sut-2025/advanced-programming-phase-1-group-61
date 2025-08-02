package models;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controllers.PreGameMenuController;
import io.github.camera.Main;
import views.PreGameMenu;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static int loggedInUser;
    private static models.User registeredUser;

    private static List<models.User> allUsers = new ArrayList<>();
    private static ArrayList<models.Game> allGames = new ArrayList<>();
    private static int currentGameId;
    private static float musicVolume=1f;
    private static Screen lastScreenBeforeShop;


    public static models.Game getCurrentGame(){
        return getGameByID(currentGameId);
    }
    public static void setCurrentGame(int id ){
        currentGameId = id;
    }
    public static void addGame(models.Game game){
        allGames.add(game);
    }

    public static ArrayList<models.Game> getAllGames() {
        return allGames;
    }
    public static models.Game getGameByID(int id){
        for (models.Game game : allGames) {
            if(game.getId()==id){
                return game;
            }
        }
        return null;
    }

    public static void saveApp() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
                .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
                .registerTypeAdapter(models.building.Shop.class , new models.ShopAdapter())
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
                .registerTypeAdapter(models.tool.Tool.class, new models.ToolAdapter())
                .registerTypeAdapter(models.resource.Resource.class, new models.ResourceAndBuildingAdapter())
                .registerTypeAdapter(models.building.Shop.class , new models.ShopAdapter())
                .setPrettyPrinting()
                .create();


        File userFile = new File("users.json");
        File gameFile = new File("games.json");
        File loggedInUserFile = new File("loggedInUser.json");

        try {

            if (userFile.exists()) {
                try (FileReader fileReader = new FileReader(userFile)) {
                    Type userListType = new TypeToken<List<models.User>>() {}.getType();
                    allUsers = gson.fromJson(fileReader, userListType);
                }
            }


            if (gameFile.exists()) {
                try (FileReader gameFileReader = new FileReader(gameFile)) {
                    Type gameListType = new TypeToken<List<models.Game>>() {}.getType();
                    allGames = gson.fromJson(gameFileReader, gameListType);
                }
            }
            System.out.println("loaded games:"+allGames.size());
            System.out.println("loaded users:"+allUsers.size());

            if (loggedInUserFile.exists()) {
                try (FileReader loggedInUserFileReader = new FileReader(loggedInUserFile)) {
                    Type loggedInUserType = new TypeToken<Integer>() {}.getType();
                    loggedInUser = gson.fromJson(loggedInUserFileReader, loggedInUserType);
                    if(gson == null){
                    }
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
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
    public static models.User getLoggedInUser() {
        for (models.User user : allUsers) {
            if(user.getId() == loggedInUser){
                return user;
            }
        }
        return null;
    }
    public static void setRegisteredUser(models.User user){
        registeredUser = user;
    }
    public static models.User getRegisteredUser() {
        return registeredUser;
    }
    public static List<models.User> getAllUsers() {
        return allUsers;
    }

    public static void addUserToList(models.User user){
        allUsers.add(user);
    }

    public static int getIdByUserName(String username){
        for (models.User user : allUsers) {
            if(user.getUsername().equals(username)){
                return user.getId();
            }
        }
        return 0;
    }
    public static void setMusicVolume(float musicVolume){
        App.musicVolume = musicVolume;
    }
    public static float getMusicVolume(){
        return musicVolume;
    }
    public static void Extract(int i, ImageButton[] items){
        ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
        Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
        Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
        style.up=imageUp;
        style.over=imageOver;
        style.down=imageOver;
        items[i]=new ImageButton(style);
    }
    public static void setLastScreenBeforeShop(Screen lastScreenBeforeShop){
        App.lastScreenBeforeShop = lastScreenBeforeShop;
    }
    public static Screen getLastScreenBeforeShop(){
        return App.lastScreenBeforeShop;
    }
}

package models;


import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.github.camera.Main;

import network.Lobby.OfflineRequest;
import network.Network;
import network.NetworkRequest;
import network.Requsets;

import java.util.ArrayList;
import java.util.List;


public class App {

    private User loggedInUser;
    private Game currentGame;
    private float musicVolume=1f;
    private int newGameId;

    private List<User> allUsers;


    public models.Game getCurrentGame(){
        return currentGame;
    }



    public void addGame(models.Game game){
        Main.getClient().sendMessage(new Network.updateGame(game , loggedInUser.getId()));
    }

    public int getNewGameId() {
        Main.getClient().sendMessage(new Requsets(NetworkRequest.newGameId, 0, 0));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("new game: "+newGameId);
        return newGameId;
    }

    public models.Game getGameByID(int id, int userId) {
        Main.getClient().sendMessage(new Requsets(NetworkRequest.GameRequest, id, userId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("new game:by id"+currentGame);
        return currentGame;
    }



    public  void setLoggedInUser(User user){
        if(user != null){
            loggedInUser = user;
            Main.getClient().sendMessage(user);
        }
    }
    public void clearApp(){
        loggedInUser.setConnectionId(0);
        Main.getClient().sendMessage(new OfflineRequest( loggedInUser.getId() ,loggedInUser.getUsername() ));
        this.loggedInUser = null;
        this.currentGame = null;
    }
    public  models.User getLoggedInUser() {
        return loggedInUser;
    }
    public  List<models.User> getAllUsers() {
        Main.getClient().sendMessage(new Requsets(NetworkRequest.AllUsersRequest , 0 ,0));
        if(allUsers == null){
            allUsers = new ArrayList<>();
        }
        return allUsers;
    }

    public  void addUserToList(models.User user){
        Main.getClient().sendMessage(user);
    }

    public  void setMusicVolume(float musicVolume){
        this.musicVolume = musicVolume;
    }
    public  float getMusicVolume(){
        return musicVolume;
    }
    public  void Extract(int i, ImageButton[] items){
        ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
        Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
        Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
        style.up=imageUp;
        style.over=imageOver;
        style.down=imageOver;
        items[i]=new ImageButton(style);
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setNewGameId(int newGameId) {
        this.newGameId = newGameId;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
    public void ExtractCooking(int i, ImageButton[] items) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        Drawable imageUp = new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
        Drawable imageOver = new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
        style.up = imageUp;
        style.over = imageOver;
        style.down = imageOver;
        items[i] = new ImageButton(style);
    }


}

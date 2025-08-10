package controllers;

import io.github.camera.Main;
import models.User;
import network.Lobby.Lobby;
import network.NetworkRequest;
import views.AllPlayersView;

import java.util.ArrayList;
import java.util.List;

public class AllPlayersController {
    private AllPlayersView view;
    private List<Lobby> lobbies ;
    public void setView(AllPlayersView view) {
        this.view = view;
        lobbies = new ArrayList<>();
    }
    public List<User> getAllOnlineUsers(){
        List<User> users = new ArrayList<>();
        for (User user : Main.getApp().getAllUsers()) {
            if(user.getConnectionId() > 0){
                users.add(user);
            }
        }
        return users;
    }

    public void setLobbies(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }

    public List<Lobby> getLobbies() {
        Main.getClient().sendMessage(NetworkRequest.UpdateLobbies);
        if(lobbies == null){
            lobbies = new ArrayList<>();
        }
        return lobbies;
    }
}

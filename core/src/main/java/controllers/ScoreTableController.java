package controllers;

import io.github.camera.Main;
import models.Game;
import models.User;
import network.NetworkRequest;

import java.util.ArrayList;
import java.util.List;

public class ScoreTableController {
    private List<User> allUsers;
    private List<Game> allGames;

    public List<User> getAllUsers() {
        if(allUsers == null){
            allUsers = new ArrayList<>();
        }
        return allUsers;
    }

    public List<Game> getAllGames() {
        if(allGames == null){
            allGames = new ArrayList<>();
        }
        return allGames;
    }

    public void refreshGameAndusers(List<Game> allGames , List<User> allUsers) {
        this.allGames = allGames;
        this.allUsers = allUsers;
    }
    public void refreshRequest(){
        Main.getClient().sendMessage(NetworkRequest.ScoreTableRefresh);
    }
}

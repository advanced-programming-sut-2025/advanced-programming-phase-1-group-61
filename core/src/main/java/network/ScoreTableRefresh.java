package network;

import models.Game;
import models.User;

import java.util.List;

public class ScoreTableRefresh {
    private List<User> allUsers;
    private List<Game> allGames;

    public ScoreTableRefresh() {
    }

    public ScoreTableRefresh(List<User> allUsers, List<Game> allGames) {
        this.allUsers = allUsers;
        this.allGames = allGames;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public List<Game> getAllGames() {
        return allGames;
    }
}

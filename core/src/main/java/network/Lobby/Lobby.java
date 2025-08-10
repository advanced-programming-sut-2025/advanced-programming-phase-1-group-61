package network.Lobby;

import models.RandomNumber;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private String id;
    private String name , Password;
    private List<User> users;
    private LobbyPrivacy privacy;
    private LobbyType type;

    public Lobby(String name, String password , LobbyPrivacy privacy , LobbyType type) {
        this.name = name;
        Password = password;
        this.users = new ArrayList<>();
        id = String.valueOf(RandomNumber.getRandomNumberWithBoundaries(10000, 99999));
        this.privacy = privacy;
        this.type = type;
    }

    public Lobby() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public LobbyPrivacy getPrivacy() {
        return privacy;
    }

    public LobbyType getType() {
        return type;
    }
}

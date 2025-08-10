package network.Lobby;

import models.User;

public class LeaveLobbyRequest {
    private User user;
    private String lobbyId , name;

    public LeaveLobbyRequest(User user, String lobbyId, String name) {
        this.user = user;
        this.lobbyId = lobbyId;
        this.name = name;
    }

    public LeaveLobbyRequest() {
    }

    public User getUser() {
        return user;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getName() {
        return name;
    }
}

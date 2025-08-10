package network.Lobby;

import models.User;

public class AddUserLobbyRequest {
    private User user;
    private String lobbyId;

    public AddUserLobbyRequest(User user, String lobbyId) {
        this.user = user;
        this.lobbyId = lobbyId;
    }

    public AddUserLobbyRequest() {
    }

    public User getUser() {
        return user;
    }

    public String getLobbyId() {
        return lobbyId;
    }
}

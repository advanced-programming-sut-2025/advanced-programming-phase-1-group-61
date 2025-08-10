package network.Lobby;

public class LobbyRequest {
    private String lobbyId , lobbyName;

    public LobbyRequest() {
    }

    public LobbyRequest(String lobbyId, String lobbyName) {
        this.lobbyId = lobbyId;
        this.lobbyName = lobbyName;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}

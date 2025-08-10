package network.Lobby;

public class OnlineClientResponse {
    private String name;
    private String lobbyName;

    public OnlineClientResponse() {
    }

    public OnlineClientResponse(String name, String lobbyName) {
        this.name = name;
        this.lobbyName = lobbyName;
    }

    public String getName() {
        return name;
    }

    public String getLobbyName() {
        return lobbyName;
    }
}

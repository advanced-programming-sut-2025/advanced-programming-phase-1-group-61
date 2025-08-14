package network.Lobby;

public class GameStartRequest {
    private int gameId ,  userId;
    private String lobbyId;

    public GameStartRequest() {
    }

    public GameStartRequest(int userId, int gameId,String lobbyId) {
        this.userId = userId;
        this.gameId = gameId;
        this.lobbyId = lobbyId;
    }

    public int getUserId() {
        return userId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getLobbyId() {
        return lobbyId;
    }
}

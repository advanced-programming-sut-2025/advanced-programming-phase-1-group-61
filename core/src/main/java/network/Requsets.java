package network;

public class Requsets {
    private NetworkRequest requestType;
    private int gameId;
    private int userId;
    public Requsets() {}
    public Requsets(NetworkRequest request, int gameId, int userId) {
        this.requestType = request;
        this.gameId = gameId;
        this.userId = userId;
    }

    public NetworkRequest getRequestType() {
        return requestType;
    }

    public int getGameId() {
        return gameId;
    }

    public int getUserId() {
        return userId;
    }
}

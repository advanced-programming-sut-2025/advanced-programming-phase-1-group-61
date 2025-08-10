package network.Lobby;

public class VotingRequest {
    private VoteType type;
    private int userId;
    private int gameId;


    public VotingRequest() {
    }

    public VotingRequest(int gameId ,VoteType type, int userId) {
        this.type = type;
        this.userId = userId;
        this.gameId = gameId;
    }

    public VoteType getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public int getGameId() {
        return gameId;
    }
}

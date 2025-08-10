package network.Lobby;

public class Vote {
    private String vote;
    private int gameId ;

    public Vote() {
    }

    public Vote(int gameId, String vote) {
        this.gameId = gameId;
        this.vote = vote;
    }


    public String getVote() {
        return vote;
    }

    public int getGameId() {
        return gameId;
    }
}

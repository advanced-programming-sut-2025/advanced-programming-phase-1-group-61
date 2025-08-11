package network;

import network.Lobby.VoteType;

public class VotingGames {
    private int gameId;
    private int votesNeeded;
    private int votesAccepted;
    private int votesDeclined;
    private VoteType type;
    private int userId;

    public VotingGames(int id , VoteType type, int gameId,int votesNeeded) {
        this.userId = id;
        this.type = type;
        this.gameId = gameId;
        this.votesNeeded = votesNeeded;
        votesAccepted = 0;
        votesDeclined = 0;

    }

    public int getGameId() {
        return gameId;
    }

    public int getVotesNeeded() {
        return votesNeeded;
    }

    public int getVotesAccepted() {
        return votesAccepted;
    }

    public int getVotesDeclined() {
        return votesDeclined;
    }

    public VoteType getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public void setVotesAccepted(int votesAccepted) {
        this.votesAccepted = votesAccepted;
    }

    public void setVotesDeclined(int votesDeclined) {
        this.votesDeclined = votesDeclined;
    }

    public void setVotesNeeded(int votesNeeded) {
        this.votesNeeded = votesNeeded;
    }
}

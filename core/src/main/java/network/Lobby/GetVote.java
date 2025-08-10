package network.Lobby;

public class GetVote {

    private VoteType type;
    private String userName;

    public GetVote() {
    }

    public GetVote( VoteType type, String userName) {
        this.type = type;
        this.userName = userName;
    }



    public VoteType getType() {
        return type;
    }

    public String getUserName() {
        return userName;
    }
}

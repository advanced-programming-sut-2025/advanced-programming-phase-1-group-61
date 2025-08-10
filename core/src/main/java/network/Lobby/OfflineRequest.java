package network.Lobby;

public class OfflineRequest {
    private int id;
    private String name;

    public OfflineRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OfflineRequest() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

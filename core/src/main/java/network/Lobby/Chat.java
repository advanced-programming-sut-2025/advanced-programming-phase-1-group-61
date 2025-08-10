package network.Lobby;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String text;
    private boolean isPrivate;
    private List<Integer> userIdList ;

    public Chat(String text, boolean isPrivate) {
        this.text = text;
        this.isPrivate = isPrivate;
        userIdList = new ArrayList<>();
    }

    public Chat() {
    }

    public String getText() {
        return text;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void addUserId(int userId){
        if(!userIdList.contains(userId)){
            userIdList.add(userId);
        }
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }
}

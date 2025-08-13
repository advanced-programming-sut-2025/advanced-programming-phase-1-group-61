package models.character;

import models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

public class FriendShip {
    private int friendShipXp;
    private List<ItemType> giftsReceived;
    private int UserID;
    public FriendShip() {
    }

    public FriendShip(int userID) {
        UserID = userID;
        this.friendShipXp = 0;
        giftsReceived = new ArrayList<>();
    }



    public int getFriendShipXp() {
        return friendShipXp;
    }

    public List<ItemType> getGiftsReceived() {
        return giftsReceived;
    }

    public void setFriendShipXp(int friendShipXp) {
        this.friendShipXp = friendShipXp;
    }
    public void addGift(ItemType type){
        giftsReceived.add(type);
    }

    public int getUserID() {
        return UserID;
    }
}

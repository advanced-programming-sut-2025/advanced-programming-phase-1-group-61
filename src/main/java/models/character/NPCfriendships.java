package models.character;

import models.enums.FriendshipLevel;

public class NPCfriendships {
    private final Character character;
    private int friendshipLevel;
    private int friendshipPoints;
    private FriendshipLevel lvl;
    public NPCfriendships(Character character) {
        this.character = character;
        friendshipPoints=0;
        friendshipLevel=0;
        lvl=FriendshipLevel.LOW;
    }
    public Character getCharacter() {
        return character;
    }
    public int getFriendshipLevel() {
        return friendshipLevel;
    }
    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
        if(friendshipLevel>lvl.getCeiling()){
            lvl=lvl.getNextLevel();
        }
    }
    public int getFriendshipPoints() {
        return friendshipPoints;
    }
    public void setFriendshipPoints(int friendshipPoints) {
        this.friendshipPoints = friendshipPoints;
    }
    public boolean equals(NPCfriendships npcfriendships) {
        return this.character.getUserId() == npcfriendships.getCharacter().getUserId();
    }
    public FriendshipLevel getLvl() {
        return lvl;
    }
}

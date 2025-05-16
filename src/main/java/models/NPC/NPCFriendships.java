package models.NPC;

import models.character.Character;
import models.enums.FriendshipLevel;

public class NPCFriendships {
    private final Character character;
    private int friendshipLevel;
    private int friendshipPoints;
    private FriendshipLevel lvl;
    public NPCFriendships(models.character.Character character) {
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
        lvl=lvl.getLevel(friendshipLevel);
    }
    public int getFriendshipPoints() {
        return friendshipPoints;
    }
    public void setFriendshipPoints(int friendshipPoints) {
        this.friendshipPoints = friendshipPoints%800;
        setFriendshipLevel(this.friendshipPoints/200);
    }
    public boolean equals(NPCFriendships npcfriendships) {
        return this.character.getUserId() == npcfriendships.getCharacter().getUserId();
    }
    public FriendshipLevel getLvl() {
        return lvl;
    }
}

package models.NPC;

import models.App;
import models.character.Character;
import models.enums.FriendshipLevel;

public class NPCFriendships {
    private final int characterId;
    private int friendshipLevel;
    private int friendshipPoints;
    private FriendshipLevel lvl;
    public NPCFriendships(int userId) {
        this.characterId = userId;
        friendshipPoints=0;
        friendshipLevel=0;
        lvl=FriendshipLevel.LOW;
    }
    public Character getCharacter() {
        return App.getCurrentGame().getCharachterByUserId(characterId);
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
        return App.getCurrentGame().getCharachterByUserId(characterId).getUserId()
                == npcfriendships.getCharacter().getUserId();
    }
    public FriendshipLevel getLvl() {
        return lvl;
    }
}

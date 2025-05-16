package models.character;

import models.App;
import models.enums.*;

import java.util.ArrayList;

public class NPC {
    private final static ArrayList<NPC> allNPCs = new ArrayList<>();
    private final NpcInfo info;
    private final NpcDialog dialogs;
    private final ArrayList<NPCfriendships> friendships = new ArrayList<>();

    public NPC(NpcInfo info, NpcDialog dialogs,Character character) {
        this.info = info;
        this.dialogs = dialogs;
        friendships.add(new NPCfriendships(character));
        allNPCs.add(this);
    }
    public String getDialog() {
        Character currentCharacter= App.getCurrentGame().getCurrentCharacter();
        NPCfriendships friendship=getFriendships(currentCharacter);
        if(friendship==null) return "";
        WeatherState currentWeather=App.getCurrentGame().getMap().getWeather().getState();
        Season season=App.getCurrentGame().getDate().getSeason();
        FriendshipLevel level=friendship.getLvl();
        return this.dialogs.getDialogue(season,level,currentWeather);
    }
    public static NPC getNPC(String name) {
        for(NPC npc : allNPCs) {
            if(npc.info.name().equals(name)) return npc;
        }
        return null;
    }
    public NPCfriendships getFriendships(Character character) {
        for(NPCfriendships npCfriendships: friendships){
            if(npCfriendships.getCharacter().getUserId()==character.getUserId()) return npCfriendships;
        }
        return null;
    }
}

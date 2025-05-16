package models.NPC;

import models.App;
import models.character.Character;
import models.enums.*;

import java.util.ArrayList;

public class NPC {
    private final static ArrayList<NPC> allNPCs = new ArrayList<>();
    private final NpcInfo info;
    private final NpcDialog dialogs;
    private final ArrayList<NPCfriendships> friendships = new ArrayList<>();

    public NPC(NpcInfo info, NpcDialog dialogs, models.character.Character character) {
        this.info = info;
        this.dialogs = dialogs;
        friendships.add(new NPCfriendships(character));
        allNPCs.add(this);
    }
    public String getDialog() {
        models.character.Character currentCharacter= App.getCurrentGame().getCurrentCharacter();
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
    public static String getNPCFriendshipsDetails(Character character) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<allNPCs.size();i++) {
            NPC npc = allNPCs.get(i);
            for(NPCfriendships friendship:npc.friendships) {
                if(friendship.getCharacter().getUserId()==character.getUserId()) {
                    builder.append(npc.info.name()).append(":\n")
                            .append("friendship level: ")
                            .append(friendship.getFriendshipLevel())
                            .append(" (")
                            .append(friendship.getLvl().name())
                            .append(")")
                            .append("friendship points: ")
                            .append(friendship.getFriendshipPoints());
                    if(i!=allNPCs.size()-1) builder.append("\n");
                }
            }
        }
        return builder.toString();
    }
}

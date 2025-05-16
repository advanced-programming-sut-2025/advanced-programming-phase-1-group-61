package models.character;

import models.App;
import models.date.Date;
import models.enums.*;

import java.util.ArrayList;
import java.util.HashMap;

public class NPC {
    private final static ArrayList<NPC> allNPCs = new ArrayList<>();
    private final NpcInfo info;
    private final NpcDialog dialogs;
    private final HashMap<Character,Integer> friendships=new HashMap<>();

    public NPC(NpcInfo info, NpcDialog dialogs) {
        this.info = info;
        this.dialogs = dialogs;
        allNPCs.add(this);
    }
    public String getDialog() {
        Character currentCharacter= App.getCurrentGame().getCurrentCharacter();
        int friendshipLevel=friendships.get(currentCharacter);
        WeatherState currentWeather=App.getCurrentGame().getMap().getWeather().getState();
        Season season=App.getCurrentGame().getDate().getSeason();
        FriendshipLevel level=FriendshipLevel.LOW;
        return this.dialogs.getDialogue(season,level,currentWeather);
    }
    public static NPC getNPC(String name) {
        for(NPC npc : allNPCs) {
            if(npc.info.name().equals(name)) return npc;
        }
        return null;
    }
}

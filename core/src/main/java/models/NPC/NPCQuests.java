package models.NPC;

import models.enums.ItemType;
import models.enums.NpcInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class NPCQuests {
    private final ArrayList<ItemWithCount> requests=new ArrayList<>();
    private final ArrayList<ItemWithCount> rewards=new ArrayList<>();
    public NPCQuests(NpcInfo info) {
        HashMap<ItemType,Integer> q=info.getRequests();
        HashMap<ItemType,Integer> r=info.getRewards();
        for(ItemType item:q.keySet()){
            requests.add(new ItemWithCount(item,q.get(item)));
        }
        for(ItemType item:r.keySet()){
            rewards.add(new ItemWithCount(item,r.get(item)));
        }
    }
    public boolean checkQuestAvailability(int questIndex) {
        if(questIndex>=0 && questIndex<requests.size()){
            return requests.get(questIndex).isDone();
        }
        return false;
    }
    public ArrayList<ItemWithCount> getRequests() {
        return requests;
    }
    public ArrayList<ItemWithCount> getRewards() {
        return rewards;
    }
}

package models.character;

import models.Item;
import models.enums.ItemType;
import models.enums.NpcInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NPC {
    private NpcInfo info;
    private HashMap<Character,Integer> friendships=new HashMap<>();
    private final HashMap<ItemType,Integer> requestList;

    public NPC(NpcInfo info) {
        this.info = info;
        this.requestList = info.getRequests();
    }

    public void move(){
        //todo
    }
    public void talk(Character character){}
    public void gift(Character character){}
}

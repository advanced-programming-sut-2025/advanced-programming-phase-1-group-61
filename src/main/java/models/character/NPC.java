package models.character;

import models.enums.NpcInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NPC {
    private NpcInfo info;
    private HashMap<Character,Integer> friendships=new HashMap<>();
    private List<Request> requestList = new ArrayList<>();
    List<String> dialogues = new ArrayList<>();

    public NPC(NpcInfo info) {
        this.info = info;
//        this.requestList = info.getQuests();
//        this.dialogues = info.getDialogues();
    }

    public void move(){
        //todo
    }
    public void talk(Character character){}
    public void gift(Character character){}
}

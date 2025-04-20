package models.enums;

import models.Item;
import models.character.Request;

import java.util.List;

public enum NpcInfo {
    ALEX(List.of("Hello ,how are you?" , "Hi.") ,
            List.of(new Request(new Item(ItemType.Iron) ,new Item(ItemType.Diamond))));




    private List<String> dialogues ;
   private List<Request> quests ;

    NpcInfo(List<String> dialogues , List<Request> quests) {
        this.dialogues = dialogues;
        this.quests = quests;
    }

    public List<Request> getQuests() {
        return quests;
    }

    public List<String> getDialogues() {
        return dialogues;
    }
}

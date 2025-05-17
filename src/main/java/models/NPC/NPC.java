package models.NPC;

import models.App;
import models.RandomNumber;
import models.character.Character;
import models.enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NPC {
    private final static ArrayList<NPC> allNPCs = new ArrayList<>();
    private final NpcInfo info;
    private final NpcDialog dialogs;
    private final ArrayList<NPCFriendships> friendships = new ArrayList<>();
    private boolean firstGiftOfDay=true;
    private int x;
    private int y;

    public NPC(NpcInfo info, NpcDialog dialogs, List<Character> characters, int x, int y) {
        this.info = info;
        this.dialogs = dialogs;
        for (Character character : characters) {
            friendships.add(new NPCFriendships(character.getUserId()));
        }
        allNPCs.add(this);
        this.x = x;
        this.y = y;
    }
    public String getDialog() {
        Character currentCharacter= App.getCurrentGame().getCurrentCharacter();
        NPCFriendships friendship=getFriendships(currentCharacter);
        if(friendship==null) return "";
        WeatherState currentWeather=App.getCurrentGame().getMap().getWeather().getState();
        Season season=App.getCurrentGame().getDate().getSeason();
        FriendshipLevel level=friendship.getLvl();
        friendship.setFriendshipPoints(friendship.getFriendshipPoints() + 1);
        return this.dialogs.getDialogue(season,level,currentWeather);
    }
    public boolean isFirstGiftOfDay() {
        return firstGiftOfDay;
    }
    public void setFirstGiftOfDay(boolean firstGiftOfDay) {
        this.firstGiftOfDay = firstGiftOfDay;
    }
    public static NPC getNPC(String name) {
        for(NPC npc : allNPCs) {
            if(npc.info.name().equals(name)) return npc;
        }
        return null;
    }
    public NpcInfo getInfo() {
        return info;
    }
    public NPCFriendships getFriendships(Character character) {
        for(NPCFriendships npCfriendships: friendships){
            if(npCfriendships.getCharacter().getUserId()==character.getUserId()) return npCfriendships;
        }
        return null;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public static String getNPCFriendshipsDetails(Character character) {
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<allNPCs.size();i++) {
            NPC npc = allNPCs.get(i);
            for(NPCFriendships friendship:npc.friendships) {
                if(friendship.getCharacter().getUserId()==character.getUserId()) {
                    builder.append(npc.info.name())
                            .append(":\n")
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
    public static void resetFirstTimeInDay(){
        for(NPC npc : allNPCs) {
            npc.setFirstGiftOfDay(true);
        }
    }
    public ItemType getRandomFavoriteItem() {
        int bound=this.info.getFavorites().size();
        int randomIndex=RandomNumber.getRandomNumber()%bound;
        return this.info.getFavorites().get(randomIndex);
    }
    public static void changeDayActivities(){
        resetFirstTimeInDay();
        for(NPC npc : allNPCs) {
            for(NPCFriendships friendship : npc.friendships) {
                if(friendship.getLvl().equals(FriendshipLevel.HIGH)){
                    boolean sendGift= RandomNumber.getRandomNumber() % 2 == 1;
                    if(sendGift)
                        friendship.getCharacter().getInventory().addItem(npc.getRandomFavoriteItem(),1);
                }
            }
        }
    }
    public static String getQuests(Character character) {
        StringBuilder builder = new StringBuilder();
        for(NPC npc : allNPCs) {
            HashMap<ItemType,Integer> requests=npc.info.getRequests();
            for(NPCFriendships friendship : npc.friendships) {
                if(friendship.getCharacter().getUserId()==character.getUserId()) {
                    builder.append(npc.info.name()).append("'s active quests:\n");
                    int index=0;
                    for(ItemType item:requests.keySet()) {
                        int count = requests.get(item);
                        if(npc.checkQuestAvailability(character,friendship,index)) {
                            builder.append("delivering ")
                                    .append(count)
                                    .append(item.getDisPlayName())
                                    .append("\n");
                        }
                        index++;
                    }
                    break;
                }
            }
        }
        return builder.toString();
    }
    public boolean checkQuestAvailability(Character character,NPCFriendships friendship,int questIndex) {
        if(questIndex==0) return true;
        if(questIndex==1)
            if(friendship.getFriendshipLevel()>=1) return true;
        if(questIndex==2)
            return App.getCurrentGame().getDate().hasASeasonPassed();
        return false;
    }
    public static ArrayList<NPC> getAllNPCs(){
        return allNPCs;
    }
    public String checkCharacterEnoughItems(Character character,int questIndex,NPCFriendships friendship) {
        HashMap<ItemType,Integer> requests=this.info.getRequests();
        for(ItemType item:requests.keySet()) {
            int count = requests.get(item);
            if(character.getInventory().getCountOfItem(item)<count){
                return "you don't have enough items!";
            }
        }
        for(ItemType item:requests.keySet()) {
            int count = requests.get(item);
            character.getInventory().removeItem(item,count);
        }
        for(ItemType item:info.getRewards().keySet()) {
            int count = requests.get(item);
            character.getInventory().addItem(item,count);
        }
        return "quest successfully finished!";
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

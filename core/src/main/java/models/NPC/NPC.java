package models.NPC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.RandomNumber;
import models.character.Character;
import models.enums.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NPC {

    private final NpcInfo info;
    private final NpcDialog dialogs;
    private final ArrayList<NPCFriendships> friendships = new ArrayList<>();
    private boolean firstGiftOfDay=true;
    private int x;
    private int y;
    private final int chatIconWidth=32;
    private final int chatIconHeight=32;
    private transient Sprite sprite;
    private Sprite dialogueSprite;


    public NPC(NpcInfo info, NpcDialog dialogs, List<Character> characters, int x, int y) {
        this.info = info;
        this.dialogs = dialogs;
        for (Character character : characters) {
            friendships.add(new NPCFriendships(character.getUserId()));
        }
        this.x = x;
        this.y = y;
        this.sprite = new Sprite(info.getTexture());
        this.sprite.setPosition(x * AssetManager.getTileSize(), y * AssetManager.getTileSize());
        this.sprite.setSize(64 , 128);
        this.dialogueSprite = new Sprite(new Texture(Gdx.files.internal("images/Sprite/chatIcon.png")));
        this.dialogueSprite.setSize(chatIconWidth,chatIconHeight);
        this.dialogueSprite.setPosition(x*AssetManager.getTileSize()+ (float) (64 - chatIconWidth) /2,y*AssetManager.getTileSize()+ 110);
    }

    public Sprite getSprite() {
        if(sprite == null){
            this.sprite = new Sprite(info.getTexture());
            this.sprite.setPosition(x * AssetManager.getTileSize() + 32, y * AssetManager.getTileSize());
            this.sprite.setSize(64 , 128);
        }
        return sprite;
    }
    public void draw(){
        getSprite().draw(Main.getBatch());
        this.dialogueSprite.draw(Main.getBatch());
    }
    public String getDialog() {
        Character currentCharacter= App.getCurrentGame().getCurrentCharacter();
        NPCFriendships friendship=getFriendships(currentCharacter);
        if(friendship==null) return "";
        WeatherState currentWeather=App.getCurrentGame().getMap().getWeather().getState();
        Season season=App.getCurrentGame().getDate().getSeason();
        FriendshipLevel level=friendship.getLvl();
        return this.dialogs.getDialogue(season,level,currentWeather);
    }
    public boolean isFirstGiftOfDay() {
        return firstGiftOfDay;
    }
    public void setFirstGiftOfDay(boolean firstGiftOfDay) {
        this.firstGiftOfDay = firstGiftOfDay;
    }
    public static NPC getNPC(String name) {
        for(NPC npc : App.getCurrentGame().getNpcList()) {
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
        for(int i=0;i< App.getCurrentGame().getNpcList().size();i++) {
            NPC npc =  App.getCurrentGame().getNpcList().get(i);
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
                    if(i!= App.getCurrentGame().getNpcList().size()-1) builder.append("\n");
                }
            }
        }
        return builder.toString();
    }
    public static void resetFirstTimeInDay(){
        for(NPC npc :  App.getCurrentGame().getNpcList()) {
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
        for(NPC npc :  App.getCurrentGame().getNpcList()) {
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
        for(NPC npc :  App.getCurrentGame().getNpcList()) {
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
        return "quest successfully finished!";
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Rectangle getChatIconBounds(){
        return new Rectangle(x*AssetManager.getTileSize()+  (64 - chatIconWidth) /2,
            y*AssetManager.getTileSize()+ 110,
            chatIconWidth,
            chatIconHeight);
    }
}

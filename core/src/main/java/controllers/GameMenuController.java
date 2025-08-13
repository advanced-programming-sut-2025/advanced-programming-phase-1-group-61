package controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.GameControllers.PlayerController;
import controllers.GameControllers.WorldController;
import io.github.camera.Main;
import models.*;
import models.animal.Animal;
import models.character.Character;
import models.NPC.NPC;
import models.character.Inventory;
import models.character.InventorySlot;
import models.enums.*;
import models.map.Weather;
import models.tool.Axe;
import models.tool.Tool;
import models.workBench.ItemKinds;
import models.workBench.WorkBench;
import network.Network;
import views.GameView;
import views.NPCPages.GiftPageView;
import views.NPCPages.QuestsPageView;


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class GameMenuController {

    private Game game;
    private OrthographicCamera camera;
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;

    public GameMenuController(Game game) {
        this.game = game;
    }

    public void setView(GameView view, OrthographicCamera camera) {
        this.view = view;
        this.camera = camera;
        playerController = new PlayerController(camera , game);
        worldController = new WorldController(camera);

    }

    public void updateGame() {
        worldController.update();
        playerController.update();
        for (NPC npc : Main.getApp().getCurrentGame().getNpcList()) {
            npc.draw();
        }
    }
    public void updateServerGame() {
        new Thread(() -> {
            Main.getClient().sendMessage(
                new Network.updateGame(Main.getApp().getCurrentGame(), Main.getApp().getLoggedInUser().getId())
            );
        }).start();
    }


    public void addListenersForNpcTable(NPC npc, TextButton gift, TextButton quests, TextButton friendship, TextButton close, Table table){
        GameMenuController controller = new GameMenuController(game);
        gift.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GiftPageView(npc,controller));
            }
        });
        quests.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new QuestsPageView(npc));
            }
        });
        friendship.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
            }
        });
        close.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                table.remove();
            }
        });
    }


    public Game getGame() {
        return Main.getApp().getCurrentGame();
    }

    public Result giftNPC(Matcher matcher) {
        String name = matcher.group("name").trim();
        String itemName = matcher.group("item").trim();
        ItemType item = ItemType.getItemType(itemName);
        if (item == null) {
            return new Result(false, "please enter a valid item!");
        }
        if (!NpcInfo.checkName(name)) {
            return new Result(false, "please enter a valid npc name!");
        }
        NPC npc = NPC.getNPC(name);
        if (npc == null) return new Result(false, "npc not found");
        List<ItemType> favorites = npc.getInfo().getFavorites();
        boolean found = false;
        for (ItemType favorite : favorites) {
            if (favorite.equals(item)) {
                found = true;
                break;
            }
        }
        Character character =game.getCurrentCharacter();
        game.changeDayActivities();
        Inventory inventory=character.getInventory();
        InventorySlot slot=inventory.getSlotByItem(item);
        if(slot==null){
            return new Result(false,"you don't have this item!");
        }
        slot.setCount(slot.getCount()-1);
        if (npc.isFirstGiftOfDay()) {
            npc.setFirstGiftOfDay(false);
            if (found) {
                npc.getFriendships(character).setFriendshipPoints(200);
                return new Result(true, "You have gained 200 friendship points with " + name);
            }
            npc.getFriendships(character).setFriendshipPoints(50);
            return new Result(true, "You have gained 50 friendship points with " + name);
        }
        return new Result(false, "you have not gained a point because this is not your first time in this day that you are giving " + name + " a gift!");
    }



}

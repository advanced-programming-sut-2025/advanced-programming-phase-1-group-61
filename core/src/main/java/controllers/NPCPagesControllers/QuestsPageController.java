package controllers.NPCPagesControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AlertGenerator;
import models.AssetManager;
import models.NPC.ItemWithCount;
import models.NPC.NPCQuests;
import models.character.Inventory;
import models.character.InventorySlot;
import models.enums.ItemType;
import views.GameView;
import views.NPCPages.QuestsPageView;

import java.util.ArrayList;

public class QuestsPageController {
    private final QuestsPageView view;
    public QuestsPageController(QuestsPageView view) {
        this.view = view;
    }
    public void addListeners(){
        view.getBackToGame().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });
        view.getDoQuests().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.setUpDoQuestsPage();
            }
        });
        view.getTakeQuests().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.setUpTakeQuestsPage();
            }
        });
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                view.show();
            }
        });
        view.getSubmit().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                String itemName=view.getItem().getText();
                String countString=view.getCount().getText();
                int count;
                try {
                    count = Integer.parseInt(countString);
                } catch (NumberFormatException e) {
                    AlertGenerator.showAlert("","Please enter a valid number",view.getStage());
                    return;
                }
                ItemType itemType=ItemType.getItemType(itemName);
                if(itemType==null){
                    AlertGenerator.showAlert("","Please enter a valid item",view.getStage());
                    return;
                }
                NPCQuests quests=view.getNpc().getNpcQuests();
                ArrayList<ItemWithCount> requests=quests.getRequests();
                for(ItemWithCount item:requests){
                    if(item.getItem()==itemType){
                        if(!item.isTaken()){
                            AlertGenerator.showAlert("","quest not found or has not been taken",view.getStage());
                            return;
                        } else if(!item.getTakenBy().equals(Main.getApp().getLoggedInUser().getUsername())){
                            AlertGenerator.showAlert("","quest has already been taken by "+item.getTakenBy(),view.getStage());
                            return;
                        }
                        Inventory inventory=Main.getApp().getCurrentGame().getCurrentCharacter().getInventory();
                        InventorySlot slot=inventory.getSlotByItem(itemType);
                        if(slot==null){
                            AlertGenerator.showAlert("","Sorry! you don't have enough items!",view.getStage());
                            return;
                        }
                        if(slot.getCount()<count){
                            AlertGenerator.showAlert("","Sorry! you don't have enough items!",view.getStage());
                            return;
                        }
                        slot.setCount(slot.getCount()-count);
                        item.setCount(item.getCount()-count);
                        if(item.getCount()<=0){
                            item.setDone(true);
                            item.setDoneBy(Main.getApp().getLoggedInUser().getUsername());
                            AlertGenerator.showAlert("","Congrats! You have done the quest! Here are your rewards!",view.getStage());
                            for(ItemWithCount reward:quests.getRewards()){
                                inventory.addItem(reward.getItem(),reward.getCount());
                            }
                        }
                    }
                }
                AlertGenerator.showAlert("","No quest found!",view.getStage());
            }
        });
    }
    public void addListenerForQuestsButtons(){
        NPCQuests quests=view.getNpc().getNpcQuests();
        ArrayList<ItemWithCount> requests=quests.getRequests();
        int index=0;
        for(TextButton button:view.getQuestButtons()){
            int finalIndex = index;
            button.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    AssetManager.getUiClicks().play();
                    if(requests.get(finalIndex).isDone()){
                        AlertGenerator.showAlert("","the quest is already done by"+requests.get(finalIndex).getDoneBy(),view.getStage());
                        return;
                    }
                    if(requests.get(finalIndex).isTaken()){
                        AlertGenerator.showAlert("","the quest is already taken by"+requests.get(finalIndex).getTakenBy(),view.getStage());
                        return;
                    }
                    requests.get(finalIndex).setTaken(true);
                    requests.get(finalIndex).setTakenBy(Main.getApp().getLoggedInUser().getUsername());
                    AlertGenerator.showAlert("","You have taken the quest",view.getStage());
                }
            });
            index++;
        }
    }
}

package controllers.NPCPagesControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AlertGenerator;
import models.AssetManager;
import models.NPC.NPC;
import models.Result;
import models.enums.Commands.GameMenuCommands;
import views.GameView;
import views.NPCPages.GiftPageView;

import java.util.regex.Matcher;

public class GiftPageController {
    private final GiftPageView view;
    public GiftPageController(GiftPageView view) {
        this.view = view;
    }
    public void addListeners(){
        NPC npc=view.getNpc();
        GameMenuController gameMenuController=view.getGameMenuController();
        view.getBack().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });
        view.getSubmit().addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                String giftCommand="gift NPC "+npc.getInfo().name()+" -i "+view.getItemName().getText();
                Matcher matcher=GameMenuCommands.GIFT_NPC.getMatcher(giftCommand);
                if(matcher==null){
                    AlertGenerator.showAlert("","ridi dash!",view.getStage());
                    return;
                }
                Result result=gameMenuController.giftNPC(matcher);
                AlertGenerator.showAlert("",result.message(),view.getStage());
            }
        });
    }
}

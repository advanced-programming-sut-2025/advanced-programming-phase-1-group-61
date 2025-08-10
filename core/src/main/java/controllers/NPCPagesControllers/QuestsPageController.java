package controllers.NPCPagesControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.GameMenuController;
import io.github.camera.Main;
import views.GameView;
import views.NPCPages.QuestsPageView;

public class QuestsPageController {
    private final QuestsPageView view;
    public QuestsPageController(QuestsPageView view) {
        this.view = view;
    }
    public void addListeners(){
        view.getBackToGame().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });
    }
}

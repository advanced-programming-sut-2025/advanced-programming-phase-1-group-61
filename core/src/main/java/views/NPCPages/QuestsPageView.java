package views.NPCPages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.NPCPagesControllers.QuestsPageController;
import io.github.camera.Main;
import models.AssetManager;
import models.NPC.NPC;

public class QuestsPageView implements Screen {
    private final QuestsPageController controller=new QuestsPageController(this);
    private final NPC npc;
    private final Stage stage;
    private final TextButton backToGame;
    private final Label info;
    public QuestsPageView(NPC npc) {
        this.npc=npc;
        stage=new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        backToGame=new TextButton("BACK", AssetManager.getSkin());
        info=new Label(npc.getQuests(Main.getApp().getCurrentGame().getCurrentCharacter()), AssetManager.getSkin());
    }
    @Override
    public void show() {
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.add(info).row();
        table.add(backToGame).width(300).height(60).row();
        controller.addListeners();
    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public TextButton getBackToGame() {
        return backToGame;
    }
}

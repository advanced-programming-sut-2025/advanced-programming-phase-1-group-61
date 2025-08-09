package views.NPCPages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.NPCPagesControllers.QuestsPageController;
import models.AssetManager;

public class QuestsPageView implements Screen {
    private final QuestsPageController controller=new QuestsPageController(this);
    private final Stage stage;
    private final TextButton backToGame;
    public QuestsPageView() {
        stage=new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        backToGame=new TextButton("BACK", AssetManager.getSkin());
    }
    @Override
    public void show() {

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
}

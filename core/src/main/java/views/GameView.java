package views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.GameMenuController;
import io.github.camera.Main;

public class GameView implements Screen {
    private GameMenuController controller;
    private Stage stage;

    public GameView(GameMenuController controller) {
        this.controller = controller;
        stage = new Stage();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CORAL);
        Main.getBatch().begin();
        stage.act();
        stage.draw();
        Main.getBatch().end();
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

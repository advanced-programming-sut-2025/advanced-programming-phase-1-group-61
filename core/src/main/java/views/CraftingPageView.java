package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.CraftingPageController;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AssetManager;

public class CraftingPageView implements Screen, InputProcessor {
    private final CraftingPageController controller=new CraftingPageController(this);
    private final Stage stage;
    private final Table mainTable;
    private final Table detailsTable;
    private final Label requiredItems;
    private final Label craftName;
    public CraftingPageView() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        mainTable=new Table();
        detailsTable=new Table();
        requiredItems=new Label("", AssetManager.getSkin());
        craftName=new Label("", AssetManager.getSkin());
    }
    @Override
    public void show() {
        mainTable.setFillParent(true);
        mainTable.defaults().center().pad(10);
        detailsTable.setFillParent(true);
        detailsTable.defaults().top().left().pad(10);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().end();
        stage.act(v);
        stage.draw();
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

    @Override
    public boolean keyDown(int i) {
        if(i==Input.Keys.B){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
    public Label getRequiredItems() {
        return requiredItems;
    }
    public Label getCraftName() {
        return craftName;
    }
}

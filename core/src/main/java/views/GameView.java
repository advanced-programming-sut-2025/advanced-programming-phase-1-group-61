package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.shops.BlackSmith;
import views.ShopViews.BlackSmithView;

public class GameView implements Screen{
    private GameMenuController controller;
    private Stage stage;
    private InventoryUI inventoryUI;
    private boolean inventoryVisible = false;
    private OrthographicCamera camera;

    public GameView(GameMenuController controller ) {
        this.controller = controller;
        stage = new Stage();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller.setView(this,camera);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        inventoryUI = new InventoryUI(AssetManager.getSkin(),App.getCurrentGame().getCurrentCharacter().getInventory(),stage);
        inventoryUI.setVisible(false);
        stage.addActor(inventoryUI);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        controller.updateGame();


        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventoryVisible = !inventoryVisible;
            inventoryUI.setVisible(inventoryVisible);
        }

        Main.getBatch().setProjectionMatrix(camera.combined);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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

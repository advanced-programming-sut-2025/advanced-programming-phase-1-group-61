package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.MainMenuController;
import io.github.camera.Main;
import models.AssetManager;


public class MainMenu implements Screen {

    private final MainMenuController controller;
    private Stage stage;
    private Skin skin = AssetManager.getSkin();
    private final TextButton registerButton;
    private final TextButton loginButton;
    private Texture background;
    private Table table;


    public MainMenu(MainMenuController controller) {
        this.controller = controller;
        this.stage = new Stage(new FitViewport(1920,1080));
        loginButton = new TextButton("LOGIN",skin);
        registerButton = new TextButton("REGISTER",skin);
        background = AssetManager.getMainMenuBackground();
    }



    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(loginButton).padBottom(20).row();
        table.add(registerButton).padBottom(20).row();
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        controller.setView(this);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.logIn();
            }
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.register();
            }
        });

    }


    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight());
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
}

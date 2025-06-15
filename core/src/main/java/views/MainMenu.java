package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.MainMenuController;
import io.github.camera.Main;
import models.AssetManager;


public class MainMenu implements Screen {

    private final MainMenuController controller;
    private Stage stage;
    private Skin skin = AssetManager.getSkin();
    private final TextButton registerButton;
    private final TextButton loginButton;
    private Table table;


    public MainMenu(MainMenuController controller) {
        this.controller = controller;
        this.stage = new Stage();
        loginButton = new TextButton("LOGIN",skin);
        registerButton = new TextButton("REGISTER",skin);
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

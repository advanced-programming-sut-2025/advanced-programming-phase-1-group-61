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
    private final TextButton settings;
    private final TextButton exitButton;
    private final Texture background;
    private final Texture logo;
    private Table table;
    private float stateTime;


    public MainMenu(MainMenuController controller) {
        this.controller = controller;
        this.stage = new Stage(new FitViewport(1920,1080));
        loginButton = new TextButton("LOGIN",skin);
        registerButton = new TextButton("REGISTER",skin);
        settings = new TextButton("SETTINGS",skin);
        exitButton = new TextButton("EXIT",skin);
        background = AssetManager.getMainMenuBackground();
        logo=AssetManager.getStardewLogo();
    }



    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        float spacing=10f;
        float width=400f;
        float height=60f;
        table.add(registerButton).width(width).height(height).padBottom(spacing).row();
        table.add(loginButton).width(width).height(height).padBottom(spacing).row();
        table.add(settings).width(width).height(height).padBottom(spacing).row();
        table.add(exitButton).width(width).height(height).padBottom(spacing);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        controller.setView(this);
        controller.addListeners();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight());
        stateTime += delta;
        //set logo x and y
        float logo_x=(stage.getViewport().getWorldWidth()-logo.getWidth()*2.5f)/2 + (float)Math.cos(stateTime*2.5f)*50f;
        float logo_y=750f + (float)Math.sin(stateTime*2.5f)*15f;
        stage.getBatch().draw(logo,logo_x,logo_y,logo.getWidth()*2.5f,logo.getHeight()*2.5f);
        stage.getBatch().end();
        stage.act(delta);
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
    public TextButton getRegisterButton() {
        return registerButton;
    }
    public TextButton getLoginButton() {
        return loginButton;
    }
    public TextButton getSettings() {
        return settings;
    }
    public TextButton getExitButton() {
        return exitButton;
    }
    public Stage getStage() {
        return stage;
    }
}

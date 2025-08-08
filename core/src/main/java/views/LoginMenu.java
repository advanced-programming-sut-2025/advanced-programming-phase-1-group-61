package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.LoginMenuController;
import controllers.MainMenuController;
import controllers.PreGameMenuController;
import controllers.RegisterMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.Result;
import org.w3c.dom.Text;

public class LoginMenu implements Screen {
    private LoginMenuController controller;
    private Stage stage;
    private Skin skin = AssetManager.getSkin();
    private Table table;
    private Table buttonTable;
    private TextButton login;
    private TextButton forgetPassword;
    private TextButton registerMenu;
    private TextButton backToMainMenu;
    private TextField username;
    private TextField password;
    private CheckBox stayLoggedIn;
    private String resultMessage = "";
    private BitmapFont font;
    private Texture background;

    public LoginMenu(LoginMenuController controller) {
        this.controller = controller;
        stage = new Stage(new FitViewport(1920,1080));
        login = new TextButton("LOGIN",skin);
        forgetPassword = new TextButton("FORGET PASSWORD",skin);
        backToMainMenu = new TextButton("MAIN MENU",skin);
        username = new TextField("",skin);
        username.setMessageText("USERNAME");
        password = new TextField("",skin);
        password.setMessageText("PASSWORD");
        stayLoggedIn = new CheckBox("stay logged in",skin);
        registerMenu = new TextButton("REGISTER MENU",skin);
        background = AssetManager.getMainMenuBackground();
        controller.setView(this);
        font = AssetManager.getFont();
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(username).width(480).pad(20);
        table.row();
        table.add(password).width(480).pad(20);
        table.row();
        table.add(stayLoggedIn).pad(20);
        table.row();

        stage.addActor(table);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.add(forgetPassword).width(540).pad(20);
        buttonTable.add(login).width(240).pad(20);
        buttonTable.add(registerMenu).width(480).pad(20);
        buttonTable.add(backToMainMenu).width(480).pad(20);
        buttonTable.bottom().padBottom(20);
        stage.addActor(buttonTable);
        Gdx.input.setInputProcessor(stage);


        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                controller.setStayLoggedIn(stayLoggedIn.isChecked());
                controller.setPassword(password.getText());
                controller.setUsername(username.getText());

                Result result = controller.login();
                resultMessage = result.message();
                if(result.isSuccessful()){
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
                }
            }
        });
        registerMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenu(new RegisterMenuController()));
            }
        });
        forgetPassword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ForgetPasswordMenu());
            }
        });
        backToMainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController()));
            }
        });
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,stage.getViewport().getWorldWidth(),stage.getViewport().getWorldHeight());
        font.draw(stage.getBatch(), resultMessage, 50, stage.getViewport().getWorldHeight() - 50);
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

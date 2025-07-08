package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.LoginMenuController;
import controllers.PreGameMenuController;
import controllers.RegisterMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.Result;

public class LoginMenu implements Screen {
    private LoginMenuController controller;
    private Stage stage;
    private Skin skin = AssetManager.getSkin();
    private Table table;
    private Table buttonTable;
    private TextButton login;
    private TextButton forgetPassword;
    private TextButton registerMenu;
    private TextField username;
    private TextField password;
    private CheckBox stayLoggedIn;
    private String resultMessage = "";
    private BitmapFont font;

    public LoginMenu(LoginMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        login = new TextButton("LOGIN",skin);
        forgetPassword = new TextButton("FORGET PASSWORD",skin);
        username = new TextField("username",skin);
        username.setMessageText("USERNAME");
        password = new TextField("",skin);
        password.setMessageText("PASSWORD");
        stayLoggedIn = new CheckBox("stay logged in",skin);
        registerMenu = new TextButton("REGISTER MENU",skin);
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
        buttonTable.bottom().padBottom(20);
        stage.addActor(buttonTable);
        Gdx.input.setInputProcessor(stage);


        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
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
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenu(new RegisterMenuController()));
            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CORAL);
        Main.getBatch().begin();
        stage.act();
        font.draw(Main.getBatch(), resultMessage, 50, Gdx.graphics.getHeight() - 50);
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

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
import models.enums.Gender;

public class RegisterMenu implements Screen {
    private RegisterMenuController controller;
    private Stage stage;
    private final Skin skin = AssetManager.getSkin();
    private TextField usernameField;
    private TextField password;
    private TextField passwordConfirm;
    private TextField nickName;
    private TextField email;
    private final CheckBox[] genders = new CheckBox[2];
    private Table table;
    private Table buttonTable;
    private Table genderTable;
    private TextButton randomPassword;
    private TextButton register;
    private TextButton login;
    private TextButton back;
    private BitmapFont font;
    private Texture background;
    private String resultMessage = "";


    public RegisterMenu(RegisterMenuController controller) {
        this.controller = controller;
        this.stage = new Stage(new FitViewport(1920,1080));
        randomPassword = new TextButton("random",skin);
        register = new TextButton("register",skin);
        login = new TextButton("Login Menu",skin);
        back = new TextButton("BACK",skin);
        usernameField = new TextField("",skin);
        usernameField.setMessageText("USERNAME");
        password = new TextField("",skin);
        password.setMessageText("PASSWORD");
        passwordConfirm = new TextField("",skin);
        passwordConfirm.setMessageText("CONFIRM PASSWORD");
        nickName = new TextField("",skin);
        nickName.setMessageText("NICKNAME");
        email = new TextField("",skin);
        email.setMessageText("EMAIL");
        font = AssetManager.getFont();
        background = AssetManager.getMainMenuBackground();
        controller.setView(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(usernameField).width(480).padBottom(20);
        table.row();
        table.add(password).width(480).padBottom(20);
        table.row();
        table.add(passwordConfirm).width(480).padBottom(20);
        table.row();
        table.add(email).width(480).padBottom(20);
        table.row();
        table.add(nickName).width(480).padBottom(20);
        table.row();
        table.padBottom(240);

        stage.addActor(table);

        genderTable = new Table();
        genderTable.setFillParent(true);
        genderTable.center().padTop(300);
        genders[0] = new CheckBox("Male" , skin);
        genders[1] = new CheckBox("Female" , skin);
        genders[0].setChecked(true);
        genderTable.add(genders[0]).pad(20,20,20,20);
        controller.setGender(Gender.Male);
        genderTable.add(genders[1]).pad(20,20,20,20);

        stage.addActor(genderTable);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.bottom().padBottom(50);
        buttonTable.add(login).pad(20);
        buttonTable.add(register).pad(20);
        buttonTable.add(randomPassword).pad(20);
        buttonTable.row();
        buttonTable.add(back).colspan(3).pad(20);


        stage.addActor(buttonTable);

        addListenerForGenders(genders);
        addListenerForRegisterButton(register);
        addListenerForRandomPassword(randomPassword);
        addListenerLoginMenu(login);
        addListenerForBackButton(back);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        font.draw(stage.getBatch(), resultMessage, 50, Gdx.graphics.getHeight() - 50);
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
    private void addListenerForGenders(CheckBox[] genders){
        genders[0].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(genders[1].isChecked()){
                    genders[1].setChecked(false);
                    controller.setGender(Gender.Male);
                }
            }
        });
        genders[1].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(genders[0].isChecked()){
                    genders[0].setChecked(false);
                    controller.setGender(Gender.Female);
                }
            }
        });
    }
    private void addListenerForRegisterButton(TextButton register){
        register.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.setUsername(usernameField.getText());
                controller.setPassword(password.getText());
                controller.setConfirmPassword(passwordConfirm.getText());
                controller.setNickname(nickName.getText());
                controller.setEmail(email.getText());
                Result result = controller.register();
                resultMessage = result.message();
                if(result.isSuccessful()){
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
                }
            }
        });
    }
    private void addListenerForRandomPassword(TextButton randomPassword){
        randomPassword.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                String randomPassword = controller.generatePassword();
                password.setText(randomPassword);
            }
        });
    }
    private void addListenerLoginMenu(TextButton login){
        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenu(new LoginMenuController()));
            }
        });
    }
    private void addListenerForBackButton(TextButton back){
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClicks().play();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController()));
            }
        });
    }
    public Stage getStage() {
        return stage;
    }
}

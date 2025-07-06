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
    private BitmapFont font;
    private String resultMessage = "";


    public RegisterMenu(RegisterMenuController controller) {
        this.controller = controller;
        this.stage = new Stage();
        randomPassword = new TextButton("random",skin);
        register = new TextButton("register",skin);
        login = new TextButton("Login Menu",skin);
        usernameField = new TextField("username",skin);
        password = new TextField("password",skin);
        passwordConfirm = new TextField("confirm password",skin);
        nickName = new TextField("nickname",skin);
        email = new TextField("email",skin);
        font = AssetManager.getFont();
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
        buttonTable.bottom().padBottom(20);
        buttonTable.add(login).pad(20);
        buttonTable.add(register).pad(20);
        buttonTable.add(randomPassword).pad(20);


        stage.addActor(buttonTable);

        addListenerForGenders(genders);
        addListenerForRegisterButton(register);
        addListenerForRandomPassword(randomPassword);
        addListenerLoginMenu(login);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CORAL);
        Main.getBatch().begin();


        font.draw(Main.getBatch(), resultMessage, 50, Gdx.graphics.getHeight() - 50);

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
                String randomPassword = controller.generatePassword();
                password.setText(randomPassword);
            }
        });
    }
    private void addListenerLoginMenu(TextButton login){
        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenu(new LoginMenuController()));
            }
        });
    }
}

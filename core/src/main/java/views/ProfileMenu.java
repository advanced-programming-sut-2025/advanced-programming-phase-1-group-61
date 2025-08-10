package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.ProfileMenuController;
import models.AssetManager;

public class ProfileMenu implements Screen {
    private final ProfileMenuController controller=new ProfileMenuController(this);
    private final Stage stage;
    private final TextButton changeUsername;
    private final TextButton changePassword;
    private final TextButton changeEmail;
    private final TextButton changeNickname;
    private final TextButton userInfo;
    private final TextButton back;
    private final TextButton backToProfile;
    private final TextField usernameField;
    private final TextField passwordField;
    private final TextField oldPasswordField;
    private final TextField emailField;
    private final TextField nicknameField;
    private final TextButton submitUsername;
    private final TextButton submitPassword;
    private final TextButton submitEmail;
    private final TextButton submitNickname;
    public ProfileMenu() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        changeUsername=new TextButton("CHANGE USERNAME", AssetManager.getSkin());
        changePassword=new TextButton("CHANGE PASSWORD", AssetManager.getSkin());
        changeEmail=new TextButton("CHANGE EMAIL", AssetManager.getSkin());
        changeNickname=new TextButton("CHANGE NICKNAME", AssetManager.getSkin());
        userInfo=new TextButton("USER INFO", AssetManager.getSkin());
        back=new TextButton("BACK", AssetManager.getSkin());
        backToProfile=new TextButton("BACK", AssetManager.getSkin());
        usernameField=new TextField("", AssetManager.getSkin());
        usernameField.setMessageText("Enter your new username");
        passwordField=new TextField("", AssetManager.getSkin());
        passwordField.setMessageText("Enter your new password");
        oldPasswordField=new TextField("", AssetManager.getSkin());
        oldPasswordField.setMessageText("Enter your old password");
        emailField=new TextField("", AssetManager.getSkin());
        emailField.setMessageText("Enter your new email");
        nicknameField=new TextField("", AssetManager.getSkin());
        nicknameField.setMessageText("Enter your new nickname");
        submitUsername=new TextButton("SUBMIT", AssetManager.getSkin());
        submitPassword=new TextButton("SUBMIT", AssetManager.getSkin());
        submitEmail=new TextButton("SUBMIT", AssetManager.getSkin());
        submitNickname=new TextButton("SUBMIT", AssetManager.getSkin());

    }

    @Override
    public void show() {
        setUpMainUI();
        controller.handle();
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
    public void setUpMainUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        int width=300;
        int height=60;
        table.add(userInfo).width(width).height(height).row();
        table.add(changeUsername).width(width).height(height).row();
        table.add(changePassword).width(width).height(height).row();
        table.add(changeEmail).width(width).height(height).row();
        table.add(changeNickname).width(width).height(height).row();
        table.add(back).width(width).height(height).row();
        stage.addActor(table);
    }
    public void setUpUsernameUI(){
        extract(usernameField, submitUsername);
    }
    public void setUpPasswordUI(){
        extract(passwordField, submitPassword);
    }
    public void setUpEmailUI(){
        extract(emailField, submitEmail);
    }
    public void setUpNicknameUI(){
        extract(nicknameField, submitNickname);
    }
    public Stage getStage() {
        return stage;
    }
    public TextButton getChangeUsername() {
        return changeUsername;
    }
    public TextButton getChangePassword() {
        return changePassword;
    }
    public TextButton getChangeEmail() {
        return changeEmail;
    }
    public TextButton getChangeNickname() {
        return changeNickname;
    }
    public TextButton getUserInfo() {
        return userInfo;
    }
    public TextButton getBack() {
        return back;
    }
    public TextButton getBackToProfile() {
        return backToProfile;
    }
    public TextField getUsernameField() {
        return usernameField;
    }
    public TextField getPasswordField() {
        return passwordField;
    }
    public TextField getOldPasswordField() {
        return oldPasswordField;
    }
    public TextField getEmailField() {
        return emailField;
    }
    public TextField getNicknameField() {
        return nicknameField;
    }
    public TextButton getSubmitUsername() {
        return submitUsername;
    }
    public TextButton getSubmitPassword() {
        return submitPassword;
    }
    public TextButton getSubmitEmail() {
        return submitEmail;
    }
    public TextButton getSubmitNickname() {
        return submitNickname;
    }
    private void extract(TextField passwordField, TextButton submitPassword) {
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        int width=300;
        int height=60;
        table.add(passwordField).width(500).height(80).row();
        table.add(submitPassword).width(width).height(height).row();
    }
}

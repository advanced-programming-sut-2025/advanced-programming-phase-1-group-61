package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.ForgetPasswordMenuController;
import models.AssetManager;

public class ForgetPasswordMenu implements Screen {
    private final ForgetPasswordMenuController controller=new ForgetPasswordMenuController(this);
    private final Stage stage;
    private final Skin skin = AssetManager.getSkin();
    private final TextField username;
    private final TextField email;
    private final TextField securityQuestion;
    private final TextField securityAnswer;
    private final TextButton getPasswordByEmail;
    private final TextButton getPasswordBySecurityQuestion;
    private final TextButton submitEmail;
    private final TextButton submitSecurityQuestion;
    private final TextButton back;
    private final TextButton cancel;
    public ForgetPasswordMenu() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        username = new TextField("",skin);
        username.setMessageText("USERNAME");
        email = new TextField("",skin);
        email.setMessageText("EMAIL");
        securityQuestion = new TextField("",skin);
        securityQuestion.setMessageText("QUESTION");
        securityAnswer = new TextField("",skin);
        securityAnswer.setMessageText("ANSWER");
        getPasswordByEmail = new TextButton("GET PASSWORD BY EMAIL",skin);
        getPasswordBySecurityQuestion = new TextButton("GET PASSWORD BY SECURITY QUESTION",skin);
        submitEmail = new TextButton("SUBMIT",skin);
        submitSecurityQuestion = new TextButton("SUBMIT",skin);
        back = new TextButton("BACK",skin);
        cancel = new TextButton("CANCEL",skin);
    }

    @Override
    public void show() {
        setUpUI();
        controller.addListeners();
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
    public void setUpUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        int buttonWidth=1000;
        table.add(getPasswordByEmail).width(buttonWidth).height(60).row();
        table.add(getPasswordBySecurityQuestion).width(buttonWidth).height(60).row();
        table.add(back).width(buttonWidth).height(60).row();
        stage.addActor(table);
    }
    public void setUpEmailUI(){
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(username).width(480).pad(20).colspan(2).row();
        table.add(email).width(480).pad(20).colspan(2).row();
        table.add(submitEmail).width(300).height(60).row();
        table.add(cancel).width(300).height(60).row();
        stage.addActor(table);
    }
    public void setUpSecurityQuestionUI(){
        stage.clear();
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(username).width(480).pad(20).colspan(2).row();
        table.add(securityQuestion).width(480).pad(20).colspan(2).row();
        table.add(securityAnswer).width(300).height(60).row();
        table.add(submitSecurityQuestion).width(300).height(60).row();
        table.add(cancel).width(300).height(60).row();
        stage.addActor(table);
    }
    public Stage getStage() {
        return stage;
    }
    public TextField getUsername(){
        return username;
    }
    public TextField getEmail(){
        return email;
    }
    public TextField getSecurityQuestion(){
        return securityQuestion;
    }
    public TextField getSecurityAnswer(){
        return securityAnswer;
    }
    public TextButton submitEmail(){
        return submitEmail;
    }
    public TextButton submitSecurityQuestion(){
        return submitSecurityQuestion;
    }
    public TextButton getBack(){
        return back;
    }
    public TextButton getGetPasswordByEmail(){
        return getPasswordByEmail;
    }
    public TextButton getGetPasswordBySecurityQuestion(){
        return getPasswordBySecurityQuestion;
    }
    public TextButton getCancel(){
        return cancel;
    }
}

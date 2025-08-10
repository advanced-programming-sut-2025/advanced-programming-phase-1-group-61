package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.CreateLobbyController;
import controllers.LobbyController;
import controllers.PreLobbyController;
import io.github.camera.Main;
import models.AssetManager;
import network.Lobby.Lobby;
import network.Lobby.LobbyPrivacy;
import network.Lobby.LobbyType;

public class CreateLobbyView implements Screen {
    private final CreateLobbyController controller;
    private final Stage stage;
    private final Skin skin;
    private Table table;
    private Table buttonTable;
    private TextButton create;
    private TextButton exit;
    private TextField lobbyName;
    private TextField password;
    private CheckBox privateCheckBox;

    private SelectBox<LobbyType> lobbyTypeSelectBox;

    public CreateLobbyView(CreateLobbyController controller) {
        this.controller = controller;
        this.stage = new Stage();
        this.skin = AssetManager.getSkin();
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.center().padBottom(50);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.bottom().padBottom(50);

        create = new TextButton("Create", skin);
        exit = new TextButton("Exit", skin);

        lobbyName = new TextField("", skin);
        lobbyName.setMessageText("Lobby Name");
        lobbyName.setWidth(200);
        table.add(lobbyName).pad(20);
        table.row();




        privateCheckBox = new CheckBox("Private",skin);
        table.add(privateCheckBox).pad(20).row();

        lobbyTypeSelectBox = new SelectBox<>(skin);
        lobbyTypeSelectBox.setItems(LobbyType.values());
        table.add(lobbyTypeSelectBox).pad(20).row();


        password = new TextField("", skin);
        password.setMessageText("Password");
        password.setVisible(false);
        password.setWidth(200);
        table.add(password).pad(20).row();

        privateCheckBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                password.setVisible(privateCheckBox.isChecked());
            }
        });

        buttonTable.add(create).pad(20);
        buttonTable.add(exit).pad(20);

        stage.addActor(table);
        stage.addActor(buttonTable);
        Gdx.input.setInputProcessor(stage);

        create.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String name = lobbyName.getText().trim();
                String pass = password.isVisible() ? password.getText().trim() : "1";
                LobbyPrivacy privacy;
                if(privateCheckBox.isChecked()){
                   privacy = LobbyPrivacy.Private;
                }else {
                    privacy = LobbyPrivacy.Public;
                }
                LobbyType type = lobbyTypeSelectBox.getSelected();
                    Lobby lobby = new Lobby(name, pass, privacy, type);
                    lobby.addUser(Main.getApp().getLoggedInUser());
                Main.getClient().sendMessage(lobby);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LobbyView(new LobbyController(lobby)));
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new PreLobbyView(new PreLobbyController()));
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}

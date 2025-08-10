package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import controllers.AllPlayersController;
import controllers.PreGameMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.User;
import network.Lobby.Lobby;

import java.util.List;

public class AllPlayersView implements Screen {

    private Table table;
    private Stage stage;
    private Skin skin;
    private AllPlayersController controller;
    private TextButton leave;
    private Table buttonTable;


    public AllPlayersView(AllPlayersController controller) {
        this.controller = controller;
        stage = new Stage();
        skin = AssetManager.getSkin();
    }

    public AllPlayersController getController() {
        return controller;
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.bottom().padBottom(50);
        leave = new TextButton("Leave",skin);
        buttonTable.add(leave);
        stage.addActor(table);
        stage.addActor(buttonTable);
        Gdx.input.setInputProcessor(stage);
        leave.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenu(new PreGameMenuController()));
            }
        });
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                updateOnlinePlayersList();
            }
        }, 0, 0.5f);
    }

    private void updateOnlinePlayersList() {
        table.clear();

        List<User> onlineUsers = controller.getAllOnlineUsers();
        List<Lobby> lobbies = controller.getLobbies();

        for (User user : onlineUsers) {
            String lobbyName = "";

            for (Lobby lobby : lobbies) {
                for (User lobbyUser : lobby.getUsers()) {
                    if(lobbyUser.getUsername().equals(user.getUsername())){
                        lobbyName = " - Lobby: " + lobby.getName();
                        break;
                    }
                }
            }

            Label playerLabel = new Label(user.getUsername() + lobbyName, skin);
            playerLabel.setAlignment(Align.left);
            table.add(playerLabel).pad(5).left().row();
        }
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}

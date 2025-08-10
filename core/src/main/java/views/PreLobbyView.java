package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.PreLobbyController;
import models.AssetManager;
import network.Lobby.Lobby;
import network.Lobby.LobbyPrivacy;
import network.Lobby.LobbyType;

import java.util.List;

public class PreLobbyView implements Screen {
    private PreLobbyController controller;
    private Stage stage;
    private Table table;
    private Table buttonTable;
    private Skin skin;

    private TextButton refresh;
    private TextButton createLobby;
    private TextButton joinLobby;
    private TextField searchForLobby;
    private TextField passwordField;

    public PreLobbyView(PreLobbyController controller) {
        this.controller = controller;
        this.skin = AssetManager.getSkin();
        this.stage = new Stage();
    }

    public PreLobbyController getController() {
        return controller;
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.top().padTop(50);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.bottom().padBottom(50);

        refresh = new TextButton("Refresh", skin);
        createLobby = new TextButton("Create Lobby", skin);
        joinLobby = new TextButton("Join", skin);

        searchForLobby = new TextField("", skin);
        searchForLobby.setMessageText("Lobby ID");
        searchForLobby.setWidth(200);

        passwordField = new TextField("", skin);
        passwordField.setMessageText("Password");
        passwordField.setVisible(false);
        passwordField.setWidth(200);

        buttonTable.add(searchForLobby).pad(5).row();
        buttonTable.add(passwordField).pad(5).row();
        buttonTable.add(refresh).pad(10);
        buttonTable.add(createLobby).pad(10);
        buttonTable.add(joinLobby).pad(10);

        stage.addActor(table);
        stage.addActor(buttonTable);

        controller.setView(this);
        Gdx.input.setInputProcessor(stage);

        refresh.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.refreshLobbyRequest();
            }
        });

        createLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.openCreateLobby();
            }
        });

        joinLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String id = searchForLobby.getText().trim();
                String password = passwordField.isVisible() ? passwordField.getText().trim() : "";
                controller.tryJoinLobbyById(id, password);
            }
        });
    }

    public void updateLobbyList(List<Lobby> lobbies) {
        table.clear();
        table.top().padTop(50);

        for (Lobby lobby : lobbies) {
            if (lobby.getType() == LobbyType.Invisible) continue;

            TextButton lobbyButton = new TextButton(
                lobby.getName() + " (" + lobby.getUsers().size() + " players)", skin
            );

            lobbyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (lobby.getPrivacy() == LobbyPrivacy.Private) {
                        passwordField.setVisible(true);
                        searchForLobby.setText(lobby.getId());
                    } else {
                        passwordField.setVisible(false);
                        controller.tryJoinLobby(lobby, "");
                    }
                }
            });

            table.add(lobbyButton).pad(5).row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}

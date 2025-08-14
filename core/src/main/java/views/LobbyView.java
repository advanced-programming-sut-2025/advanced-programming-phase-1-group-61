package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import controllers.LobbyController;
import io.github.camera.Main;
import models.AssetManager;
import models.User;


import java.util.List;

public class LobbyView implements Screen {
    private Stage stage;
    private Skin skin;
    private LobbyController controller;
    private Table playerTable;
    private Table table;
    private int[] selectedMaps;
    private Label lobbyId ;
    private TextButton leaveButton;

    public LobbyView(LobbyController controller) {
        this.controller = controller;
        controller.setView(this);
        stage = new Stage();
        skin = AssetManager.getSkin();
        Gdx.input.setInputProcessor(stage);

        playerTable = new Table(skin);
        playerTable.setFillParent(true);

        table = new Table();
        table.setFillParent(true);
        table.bottom().padBottom(50);

        lobbyId = new Label("Lobby ID: " + controller.getLobby().getId(), skin);
        lobbyId.setAlignment(Align.center);
        table.add(lobbyId).colspan(2).padTop(10);
        table.row();


        leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.addListener(event -> {
            if (leaveButton.isPressed()) {
                controller.leave();
            }
            return false;
        });
        table.add(leaveButton).colspan(2).padTop(10);
        table.row();

        stage.addActor(playerTable);
        stage.addActor(table);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                controller.sendUpdateRequest();
            }
        }, 0, 0.5f);
    }


    public void refreshPlayerList() {
        playerTable.clear();
        List<User> players = controller.getLobby().getUsers();

        if (players == null) return;


        if (selectedMaps == null || selectedMaps.length != players.size()) {
            selectedMaps = new int[players.size()];
            for (int k = 0; k < selectedMaps.length; k++) {
                selectedMaps[k] = 1;
            }
        }

        boolean isAdmin = !players.isEmpty() && players.get(0).getId() == Main.getApp().getLoggedInUser().getId();

        for (int i = 0; i < players.size(); i++) {
            User p = players.get(i);
            if(p.getGameId() != 0){
//                controller.startGameByGameId(p.getGameId());
            }

            Label nameLabel = new Label(p.getUsername(), skin);
            nameLabel.setAlignment(Align.left);
            playerTable.add(nameLabel).pad(5);

            if (isAdmin) {
                SelectBox<Integer> selectBox = new SelectBox<>(skin);
                selectBox.setItems(1, 2);
                selectBox.setSelected(selectedMaps[i]);

                final int index = i;
                selectBox.addListener(event -> {
                    selectedMaps[index] = selectBox.getSelected();
                    return false;
                });

                playerTable.add(selectBox).pad(5);
            } else {
                playerTable.add(new Label("", skin)).pad(5);
            }

            playerTable.row();
        }

        if (isAdmin) {
            TextButton startButton = new TextButton("Start Game", skin);
            startButton.addListener(event -> {
                if (startButton.isPressed() && controller.getLobby().getUsers().size() >=2) {
                    controller.startGame();
                }
                return false;
            });
            playerTable.add(startButton).colspan(2).padTop(20);
        }

    }
    @Override
    public void show() {
        refreshPlayerList();

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

    public int[] getSelectedMaps() {
        return selectedMaps;
    }

    public LobbyController getController() {
        return controller;
    }
}

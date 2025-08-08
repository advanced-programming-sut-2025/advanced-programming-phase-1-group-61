package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.GameMenuController;
import controllers.NewGameController;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewGameView implements Screen {
    private Stage stage;
    private Table fieldTable;
    private Table buttonTable;
    private NewGameController controller;
    private BitmapFont font;
    private TextField player2;
    private TextField player3;
    private TextField player4;
    private TextButton addPlayer3;
    private TextButton addPlayer4;
    private TextButton startNewGame;
    private Skin skin = AssetManager.getSkin();
    private TextButton removePlayer;
    private String resultMessage = "";
    private List<String> userNames = new ArrayList<>();
    private int numOfPlayersChosenMap = 0;


    private Table mapNumber;
    private SelectBox<Integer> mapNumberSelectBox;
    private TextButton next;

    public NewGameView(NewGameController controller) {
        this.controller = controller;
        stage = new Stage();
        font = AssetManager.getFont();
        player2 = new TextField("PLAYER2",skin);
        player3 = new TextField("PLAYER3",skin);
        player4 = new TextField("PLAYER4",skin);
        addPlayer4 = new TextButton("+",skin);
        addPlayer3 = new TextButton("+",skin);
        startNewGame = new TextButton("START",skin);
        removePlayer = new TextButton("REMOVE PLAYER",skin);
        mapNumberSelectBox = new SelectBox<>(skin);
        mapNumberSelectBox.setItems(1,2);
        mapNumberSelectBox.setSelected(1);
        next = new TextButton("NEXT",skin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        fieldTable = new Table();
        fieldTable.setFillParent(true);
        fieldTable.center();
        fieldTable.add(player2).width(480).padBottom(120);
        fieldTable.row();
        fieldTable.add(player3).width(480).padBottom(120);
        fieldTable.row();
        fieldTable.add(player4).width(480).padBottom(120);
        fieldTable.row();
        fieldTable.add(startNewGame).padBottom(20);
        fieldTable.row();
        fieldTable.add(removePlayer).padBottom(20);
        player3.setVisible(false);
        player4.setVisible(false);

        stage.addActor(fieldTable);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.center();
        buttonTable.add(addPlayer3).padBottom(120);
        buttonTable.row();
        buttonTable.add(addPlayer4).padBottom(180);
        addPlayer4.setVisible(false);

        stage.addActor(buttonTable);

        mapNumber = new Table();
        mapNumber.setFillParent(true);
        mapNumber.center();
        mapNumber.add(mapNumberSelectBox).width(240).padBottom(20);
        mapNumber.row();
        mapNumber.add(next);
        mapNumber.setVisible(false);
        stage.addActor(mapNumber);


        addListener();
        controller.setView(this);
    }

    @Override
    public void render(float v) {
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
    private void addListener(){
        addPlayer3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addPlayer3.setVisible(false);
                addPlayer4.setVisible(true);
                player3.setVisible(true);
                controller.setNumOfPlayers(3);
            }
        });
        addPlayer4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addPlayer4.setVisible(false);
                player4.setVisible(true);
                controller.setNumOfPlayers(4);
            }
        });
        removePlayer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(player4.isVisible()){
                    addPlayer4.setVisible(true);
                    player4.setVisible(false);
                    controller.setNumOfPlayers(3);
                    return;
                }
                if(player3.isVisible()){
                    addPlayer3.setVisible(true);
                    player3.setVisible(false);
                    controller.setNumOfPlayers(2);
                    addPlayer4.setVisible(false);
                }
            }
        });
        startNewGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.startGameErrors();
                if(!result.isSuccessful()){
                    resultMessage =result.message();
                }
            }
        });
        next.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(numOfPlayersChosenMap < controller.getNumOfPlayers()){
                    controller.getMapNumbers()[numOfPlayersChosenMap] = mapNumberSelectBox.getSelected();
                    numOfPlayersChosenMap++;
                    if(numOfPlayersChosenMap != controller.getNumOfPlayers()){
                        String username = controller.getUserList().get(numOfPlayersChosenMap).getUsername();
                        resultMessage = "choosing map for "+username;
                    }else {
                        controller.startGame();
                        Main.getMain().getScreen().dispose();
                        Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
                    }
                }
            }
        });

    }

    public TextField getPlayer2() {
        return player2;
    }

    public TextField getPlayer3() {
        return player3;
    }

    public TextField getPlayer4() {
        return player4;
    }

    public Table getFieldTable() {
        return fieldTable;
    }

    public Table getButtonTable() {
        return buttonTable;
    }

    public Table getMapNumber() {
        return mapNumber;
    }
    public void addUserName(String username){
        userNames.add(username);
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}

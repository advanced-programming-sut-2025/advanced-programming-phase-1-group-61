package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controllers.*;
import io.github.camera.Main;
import models.*;

public class PreGameMenu implements Screen {
    private PreGameMenuController controller;
    private Stage stage;
    private Table table;
    private TextButton logout;
    private TextButton newGame;
    private TextButton loadGame;
    private TextButton profileMenu;
    private TextButton playerList;
    private TextButton setting;
    private Skin skin = AssetManager.getSkin();


    public PreGameMenu(PreGameMenuController controller) {
        this.controller = controller;
        stage = new Stage();
        logout  = new TextButton("LOGOUT",skin);
        newGame =  new TextButton("NEW GAME",skin);
        loadGame = new TextButton("LOAD GAME",skin);
        profileMenu = new TextButton("PROFILE",skin);
        setting = new TextButton("SETTING",skin);
        playerList = new TextButton("Player List",skin);
        controller.setView(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(newGame).padBottom(30);
        table.row();
        table.add(loadGame).padBottom(20);
        table.row();
        table.add(playerList.padBottom(20));
        table.row();
        table.add(profileMenu).padBottom(20);
        table.row();
        table.add(setting).padBottom(20);
        table.row();
        table.add(logout).padBottom(20);
        table.row();


        stage.addActor(table);


        addListener();
    }

    @Override
    public void render(float v) {
        Main.getBatch().begin();
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
    private void addListener(){
        newGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreLobbyView(new PreLobbyController()));
            }
        });
        loadGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User user = Main.getApp().getLoggedInUser();
                if (user.getGameId() == 0) {
                   Main.getMain().getScreen().dispose();
                   Main.getMain().setScreen(new NewGameView(new NewGameController()));
                   return;
                }
                Game game = Main.getApp().getGameByID(user.getGameId() ,user.getId());
                if (game == null) {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new NewGameView(new NewGameController()));
                    return;
                }
                Main.getApp().setCurrentGame(game);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));

            }
        });
        profileMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO
            }
        });
        profileMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        logout.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
             Main.getApp().clearApp();
             Main.getMain().getScreen().dispose();
             Main.getMain().setScreen(new MainMenu(new MainMenuController()));
            }
        });
        playerList.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new AllPlayersView(new AllPlayersController()));
            }
        });
    }
}

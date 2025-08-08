package io.github.camera;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.MainMenuController;
import models.App;
import models.AssetManager;
import network.NetworkClient;
import views.MainMenu;

import java.io.IOException;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private static SpriteBatch batch;
    private static Main main;
    private static NetworkClient client;
    private static App app;



    @Override
    public void create() {
        batch = new SpriteBatch();
        AssetManager.load();
        main = this;
        main.setScreen(new MainMenu(new MainMenuController()));
        client = new NetworkClient();
        client.start();
        app = new App();



    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.end();
        super.render();
    }

    @Override
    public void dispose() {

        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static App getApp() {
        return app;
    }

    public static void setApp(App app) {
        Main.app = app;
    }

    public static NetworkClient getClient() {
        return client;
    }
}

package io.github.camera;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.MainMenuController;
import models.App;
import models.AssetManager;
import views.MainMenu;

import java.io.IOException;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    private static SpriteBatch batch;
    private static Main main;



    @Override
    public void create() {
        batch = new SpriteBatch();
        AssetManager.load();
        main = this;
        main.setScreen(new MainMenu(new MainMenuController()));
        App.loadApp();
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
       try {
           App.saveApp();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}

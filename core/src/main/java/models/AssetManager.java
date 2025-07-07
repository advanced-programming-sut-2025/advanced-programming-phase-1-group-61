package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private  static Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private static BitmapFont font;
    private static Texture playerTexture = new Texture("T_Raven_Idle_0.png");
    private static int tileSize = 128;
    private static Music uiClicks=Gdx.audio.newMusic(Gdx.files.internal("SFX/others/UI Click 36.wav"));


    public static int getTileSize() {
        return tileSize;
    }

    public static Skin getSkin() {
        return skin;
    }

    public static Texture getPlayerTexture() {
        return playerTexture;
    }

    public static BitmapFont getFont() {
        return font;
    }

    public static void load() {
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));
    }
    public static Texture getMainMenuBackground() {
        return new Texture(Gdx.files.internal("images/backgrounds/Panorama.png"));
    }
    public static Texture getStardewLogo(){
        return new Texture("images/backgrounds/logo.png");
    }
    public static Music getUiClicks() {
        return uiClicks;
    }
}

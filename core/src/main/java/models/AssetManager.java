package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private  static Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private static BitmapFont font;

    public static Skin getSkin() {
        return skin;
    }

    public static BitmapFont getFont() {
        return font;
    }

    public static void load() {
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));
    }
}

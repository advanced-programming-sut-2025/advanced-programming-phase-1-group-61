package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import models.enums.Direction;

public class AssetManager {
    private static Skin skin;
    private static BitmapFont font;
    private static Texture playerTexture;
    private static int tileSize = 128;
    private static Music uiClicks;

    private static Texture playerWalkSheet;

    private static TextureRegion[] playerWalkLeftFrames;
    private static TextureRegion[] playerWlakRightFrames;
    private static TextureRegion[] playerWlakUpFrames;
    private static TextureRegion[] playerWlakDownFrames;

    private static Animation<TextureRegion> walkDownAnimation;
    private static Animation<TextureRegion> walkLeftAnimation;
    private static Animation<TextureRegion> walkRightAnimation;
    private static Animation<TextureRegion> walkUpAnimation;

    public static void load() {
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));
        playerTexture = new Texture("T_Raven_Idle_0.png");
        uiClicks = Gdx.audio.newMusic(Gdx.files.internal("SFX/others/UI Click 36.wav"));
        playerWalkSheet = new Texture("char_a_p1_0bas_humn_v00.png");

        playerWalkLeftFrames = new TextureRegion[6];
        playerWlakRightFrames = new TextureRegion[6];
        playerWlakUpFrames = new TextureRegion[6];
        playerWlakDownFrames = new TextureRegion[6];

        for (int i = 0; i < 6; i++) {
            playerWalkLeftFrames[i] = new TextureRegion(playerWalkSheet, i * 64, 448, 64, 64);
            playerWlakRightFrames[i] = new TextureRegion(playerWalkSheet, i * 64, 384, 64, 64);
            playerWlakUpFrames[i] = new TextureRegion(playerWalkSheet, i * 64, 320, 64, 64);
            playerWlakDownFrames[i] = new TextureRegion(playerWalkSheet, i * 64, 256, 64, 64);
        }

        walkDownAnimation = new Animation<>(0.1f, playerWlakDownFrames);
        walkLeftAnimation = new Animation<>(0.1f, playerWalkLeftFrames);
        walkRightAnimation = new Animation<>(0.1f, playerWlakRightFrames);
        walkUpAnimation = new Animation<>(0.1f, playerWlakUpFrames);
    }

    // Getterها بدون تغییر باقی می‌مانند

    public static Animation<TextureRegion> getWalkAnimation(Direction dir) {
        return switch (dir) {
            case UP -> walkUpAnimation;
            case BOTTOM -> walkDownAnimation;
            case LEFT -> walkLeftAnimation;
            case RIGHT -> walkRightAnimation;
        };
    }



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

    public static TextureRegion[] getPlayerWalkLeftFrames() {
        return playerWalkLeftFrames;
    }

    public static TextureRegion[] getPlayerWlakRightFrames() {
        return playerWlakRightFrames;
    }

    public static TextureRegion[] getPlayerWlakUpFrames() {
        return playerWlakUpFrames;
    }

    public static TextureRegion[] getPlayerWlakDownFrames() {
        return playerWlakDownFrames;
    }

    public static Texture getMainMenuBackground() {
        return new Texture(Gdx.files.internal("images/backgrounds/Panorama.png"));
    }

    public static Texture getStardewLogo() {
        return new Texture("images/backgrounds/logo.png");
    }

    public static Music getUiClicks() {
        return uiClicks;
    }

    public static Texture getSelectorBubbleDefault() {
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_0.png"));
    }

    public static Texture getSelectorBubbleHover() {
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_1.png"));
    }
}


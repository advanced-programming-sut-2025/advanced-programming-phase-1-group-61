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
    private  static Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private static BitmapFont font;
    private static Texture playerTexture = new Texture("T_Raven_Idle_0.png");
    private static int tileSize = 128;
    private static Music uiClicks=Gdx.audio.newMusic(Gdx.files.internal("SFX/others/UI Click 36.wav"));
    private static Texture SebastianTexture = new Texture("NPC/sebastian.png");
    private static Texture playerWalkSheet = new Texture("char_a_p1_0bas_humn_v00.png");
    private static TextureRegion[] playerWalkLeftFrames = new TextureRegion[6];
    private static TextureRegion[] playerWlakRightFrames = new TextureRegion[6];
    private static TextureRegion[] playerWlakUpFrames = new TextureRegion[6];
    private static TextureRegion[] playerWlakDownFrames = new TextureRegion[6];

    private static Animation<TextureRegion> walkDownAnimation;
    private static Animation<TextureRegion> walkLeftAnimation;
    private static Animation<TextureRegion> walkRightAnimation;
    private static Animation<TextureRegion> walkUpAnimation;


    public static Animation<TextureRegion> getWalkAnimation(Direction dir) {
        return switch (dir) {
            case UP -> walkUpAnimation;
            case BOTTOM -> walkDownAnimation;
            case LEFT -> walkLeftAnimation;
            case RIGHT -> walkRightAnimation;
        };
    }

    public static Texture getSebastianTexture() {
        return SebastianTexture;
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

    public static void load() {
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));
        for (int i = 0; i < 6; i++) {
            playerWalkLeftFrames[i] = new TextureRegion(playerWalkSheet, i * 64, 448, 64, 64);
        }
        for (int i = 0; i < 6; i++) {
            playerWlakRightFrames[i] = new TextureRegion(playerWalkSheet , i*64 , 384 , 64 , 64);
        }
        for (int i = 0; i < 6; i++) {
            playerWlakUpFrames[i] = new TextureRegion(playerWalkSheet , i*64 , 320 , 64 ,64 );
        }
        for (int i = 0; i < 6; i++) {
            playerWlakDownFrames[i] = new TextureRegion(playerWalkSheet , i*64 , 256 , 64 ,64 );
        }
        font = new BitmapFont(Gdx.files.internal("skin/bitmap_font.fnt"));



        walkDownAnimation = new Animation<>(0.1f,playerWlakDownFrames[0] ,playerWlakDownFrames[1],playerWlakDownFrames[2],playerWlakDownFrames[3],playerWlakDownFrames[4],playerWlakDownFrames[5] );
        walkLeftAnimation = new Animation<>(0.1f,playerWalkLeftFrames[0] ,playerWalkLeftFrames[1],playerWalkLeftFrames[2],playerWalkLeftFrames[3],playerWalkLeftFrames[4],playerWalkLeftFrames[5] );
        walkRightAnimation = new Animation<>(0.1f,playerWlakRightFrames[0] ,playerWlakRightFrames[1],playerWlakRightFrames[2],playerWlakRightFrames[3],playerWlakRightFrames[4],playerWlakRightFrames[5]);
        walkUpAnimation = new Animation<>(0.1f,playerWlakUpFrames[0] ,playerWlakUpFrames[1],playerWlakUpFrames[2],playerWlakUpFrames[3],playerWlakUpFrames[4],playerWlakUpFrames[5] );
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
    public static Texture getStardewLogo(){
        return new Texture("images/backgrounds/logo.png");
    }
    public static Music getUiClicks() {
        return uiClicks;
    }
    public static Texture getSelectorBubbleDefault(){
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_0.png"));
    }
    public static Texture getSelectorBubbleHover(){
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_1.png"));
    }
}

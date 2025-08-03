package controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.Game;
import models.character.Character;
import models.enums.ShopType;
import models.enums.TileType;
import models.map.Map;
import models.map.Tile;
import models.shops.*;
import views.GameView;
import views.ShopViews.*;

public class PlayerController {
    private Character player;
    public OrthographicCamera camera;
    private float shopCooldown;
    private Texture[] thunderFrames;
    private float thunderTimer = 0;
    private int thunderFrameIndex = 0;
    private boolean thunderActive = false;
    private float thunderX, thunderY;







    public PlayerController(OrthographicCamera camera ) {
        this.player = App.getCurrentGame().getCurrentCharacter();
        this.camera = camera;
        thunderFrames = new Texture[7];
        for (int i = 0; i < 7; i++) {
            thunderFrames[i] = new Texture("Thunder/Thunder_" + i + ".png");
        }


    }

    public void update() {
        camera.position.set(player.getSpriteX() + player.getPlayerSprite().getWidth() / 2f,
            player.getSpriteY() + player.getPlayerSprite().getHeight() / 2f,
            0);
        camera.update();

        player.getPlayerSprite().draw(Main.getBatch());
        handlePlayerInput();
        shopCooldown += Gdx.graphics.getDeltaTime();
        if (thunderActive) {
            thunderTimer += Gdx.graphics.getDeltaTime();

            if (thunderTimer >= 0.1f) {
                thunderFrameIndex++;
                thunderTimer = 0;
            }

            if (thunderFrameIndex < thunderFrames.length) {
                int tileSize =AssetManager.getTileSize();
                Main.getBatch().draw(thunderFrames[thunderFrameIndex], thunderX*tileSize, thunderY*tileSize ,96 ,640);
            } else {
                thunderActive = false;
            }
        }

    }
    public void handlePlayerInput() {
        float dx = 0;
        float dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx -= 1;
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            App.getCurrentGame().getDate().increaseTime(1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.B)){
            App.getCurrentGame().getMap().getWeather().lightning(player.getX() + 5, player.getY());

            thunderX = player.getX() + 5;
            thunderY = player.getY();
            thunderActive = true;
            thunderTimer = 0;
            thunderFrameIndex = 0;
        }




        if (dx != 0 && dy != 0) {
            dx *= 0.7071f;
            dy *= 0.7071f;
        }

        dx *= player.getSpeed();
        dy *= player.getSpeed();

        float newSpriteX = player.getSpriteX() + dx;
        float newSpriteY = player.getSpriteY() + dy;

        float offsetX = 32;
        float offsetY = 0;

        CollisionRect futureRect = new CollisionRect(
            newSpriteX + offsetX,
            newSpriteY + offsetY,
            player.getCollisionRect().getWidth(),
            player.getCollisionRect().getHeight()
        );

        float tileSize = AssetManager.getTileSize();
        int startX = (int)(futureRect.getX() / tileSize);
        int endX = (int)((futureRect.getX() + futureRect.getWidth()) / tileSize);
        int startY = (int)(futureRect.getY() / tileSize);
        int endY = (int)((futureRect.getY() + futureRect.getHeight()) / tileSize);

        boolean collisionDetected = false;
        boolean shopDetected = false;
        Game game = App.getCurrentGame();
        Map map = game.getMap();
        TileType shopTile = null;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Tile tile = map.getTileByCordinate(x, y);
                if (tile == null) continue;
                switch (tile.getType()){
                    case TileType.BlackSmith, TileType.Carpenter,TileType.FishShop,TileType.JojaMart,TileType.StarDrop,TileType.Marnie,TileType.Pierre ->  shopDetected = true;
                }

                shopTile = tile.getType();


                if ((tile.isCollisionOn() || tile.getResource() != null)
                    && tile.getCollisionRect().collidesWith(futureRect)) {
                    collisionDetected = true;
                }
            }
        }
        if (shopDetected && shopCooldown>=5) {
            shopCooldown=0;
            App.setLastScreenBeforeShop(Main.getMain().getScreen());
            switch (shopTile){
                case TileType.BlackSmith -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new BlackSmithView((BlackSmith) App.getCurrentGame().getShopByShopType(ShopType.BlackSmith)));
                    return;
                }case TileType.Carpenter -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new CarpenterView((Carpenter) App.getCurrentGame().getShopByShopType(ShopType.Carpenter)));
                }case TileType.FishShop -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new FishShopView((FishShop) App.getCurrentGame().getShopByShopType(ShopType.FishShop)));
                }case TileType.JojaMart -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new JojaMartView((JojaMart) App.getCurrentGame().getShopByShopType(ShopType.JojaMart)));
                }case TileType.StarDrop -> {
                    //TODO
                }case TileType.Marnie -> {
                    //TODO
                }case TileType.Pierre -> {
                    //TODO
                }
            }
        }

        if (!collisionDetected) {
            player.setSpriteX((int)newSpriteX);
            player.setSpriteY((int)newSpriteY);
            player.getPlayerSprite().setPosition(newSpriteX, newSpriteY);
        }

    }

}

package controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.Game;
import models.character.Character;
import models.character.InventorySlot;
import models.enums.Direction;
import models.enums.ShopType;
import models.enums.TileType;
import models.map.Map;
import models.map.Tile;
import models.shops.*;
import views.GameView;
import views.InventoryUI;
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
    private InventoryUI inventoryUI;







    public PlayerController(OrthographicCamera camera) {
        this.player = App.getCurrentGame().getCurrentCharacter();
        this.camera = camera;
        thunderFrames = new Texture[7];
        for (int i = 0; i < 7; i++) {
            thunderFrames[i] = new Texture("Thunder/Thunder_" + i + ".png");
        }
    }

    public void setInventoryUI(InventoryUI inventoryUI) {
        this.inventoryUI = inventoryUI;
    }

    public void update() {
        camera.position.set(
            player.getSpriteX() + 96 / 2f,
            player.getSpriteY() + 128 / 2f,
            0);
        camera.update();


        TextureRegion currentFrame = player.getCurrentFrame();
        Main.getBatch().draw(currentFrame, player.getSpriteX()-56, player.getSpriteY()-48, 224, 224);


        handlePlayerInput();


        shopCooldown += Gdx.graphics.getDeltaTime();
        if (thunderActive) {
            thunderTimer += Gdx.graphics.getDeltaTime();

            if (thunderTimer >= 0.1f) {
                thunderFrameIndex++;
                thunderTimer = 0;
            }

            if (thunderFrameIndex < thunderFrames.length) {
                int tileSize = AssetManager.getTileSize();
                Main.getBatch().draw(thunderFrames[thunderFrameIndex], thunderX * tileSize, thunderY * tileSize, 96, 640);
            } else {
                thunderActive = false;
            }
        }
    }

    public void handlePlayerInput() {
        float dx = 0;
        float dy = 0;


        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.setDirection(Direction.UP);
            dy += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            player.setDirection(Direction.BOTTOM);
            dy -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            player.setDirection(Direction.RIGHT);
            dx += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            player.setDirection(Direction.LEFT);
            dx -= 1;
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (player.getCurrentTool() != null) {
                player.getCurrentTool().use(player.getDirection());
            }
        }

        boolean isActuallyMoving = dx != 0 || dy != 0;
        player.setMoving(isActuallyMoving);
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

        player.updateAnimation(player.getDirection(), Gdx.graphics.getDeltaTime());
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
               //todo
                }case TileType.Marnie -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MarnieView((Marnie) App.getCurrentGame().getShopByShopType(ShopType.Marnie)));
                    //TODO
                }case TileType.Pierre -> {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new PierreView((Pierre) App.getCurrentGame().getShopByShopType(ShopType.Pierre)));
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

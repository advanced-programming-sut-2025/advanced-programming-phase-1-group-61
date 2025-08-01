package controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.Game;
import models.building.Shop;
import models.character.Character;
import models.enums.ShopType;
import models.enums.TileType;
import models.map.Map;
import models.map.Tile;
import models.shops.*;
import views.ShopViews.*;

public class PlayerController {
    private Character player;
    public OrthographicCamera camera;
    private float shopCooldown;


    public PlayerController(OrthographicCamera camera) {
        this.player = App.getCurrentGame().getCurrentCharacter();
        this.camera = camera;
    }

    public void update() {
        camera.position.set(player.getSpriteX() + player.getPlayerSprite().getWidth() / 2f,
            player.getSpriteY() + player.getPlayerSprite().getHeight() / 2f,
            0);
        camera.update();

        player.getPlayerSprite().draw(Main.getBatch());
        handlePlayerInput();
        shopCooldown += Gdx.graphics.getDeltaTime();
    }
    public void handlePlayerInput() {
        float dx = 0;
        float dy = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx -= 1;


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

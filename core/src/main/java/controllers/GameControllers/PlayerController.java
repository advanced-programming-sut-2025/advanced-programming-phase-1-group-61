package controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.CollisionRect;
import models.character.Character;
import models.map.Tile;

public class PlayerController {
    private Character player;
    public OrthographicCamera camera;


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

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Tile tile = App.getCurrentGame().getMap().getTileByCordinate(x, y);
                if (tile != null && (tile.isCollisionOn() || tile.getResource() != null)) {
                    if (tile.getCollisionRect().collidesWith(futureRect)) {
                        collisionDetected = true;
                        break;
                    }
                }
            }
            if (collisionDetected) break;
        }

        if (!collisionDetected) {
            player.setSpriteX((int)newSpriteX);
            player.setSpriteY((int)newSpriteY);
            player.getPlayerSprite().setPosition(newSpriteX, newSpriteY);
        }
    }

}

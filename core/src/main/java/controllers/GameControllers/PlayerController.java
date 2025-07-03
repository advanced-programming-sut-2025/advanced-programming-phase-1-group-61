package controllers.GameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.camera.Main;
import models.App;
import models.character.Character;

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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy += player.getSpeed();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy -= player.getSpeed();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx += player.getSpeed();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx -= player.getSpeed();

        }

        player.setSpriteX((int) (player.getSpriteX() + dx));
        player.setSpriteY((int) (player.getSpriteY()+ dy));


        float playerX = player.getSpriteX();
        float playerY = player.getSpriteY();
        player.getPlayerSprite().setPosition(playerX, playerY);

    }
}

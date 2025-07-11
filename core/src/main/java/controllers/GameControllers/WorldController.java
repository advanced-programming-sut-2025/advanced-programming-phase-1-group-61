package controllers.GameControllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.building.Shop;
import models.character.Character;
import models.map.Map;
import models.map.Tile;
import models.resource.Stone;
import models.resource.Tree;

public class WorldController {
    private Map map;
    private OrthographicCamera camera;
    private Character character;


    private static final int VIEW_RADIUS = 12;

    public WorldController(OrthographicCamera camera) {
        this.map = App.getCurrentGame().getMap();
        this.camera = camera;
        this.character = App.getCurrentGame().getCurrentCharacter();
    }


    public void update() {
        int charX = character.getX();
        int charY = character.getY();

        Main.getBatch().setProjectionMatrix(camera.combined);

        for (int y = charY - VIEW_RADIUS; y <= charY + VIEW_RADIUS; y++) {
            for (int x = charX - VIEW_RADIUS; x <= charX + VIEW_RADIUS; x++) {
                Tile tile = map.getTileByCordinate(x, y);
                if (tile != null) {
                    int TILE_SIZE = AssetManager.getTileSize();
                    Sprite sprite = tile.getSprite();
                    sprite.setSize(TILE_SIZE, TILE_SIZE);
                    Main.getBatch().draw(sprite, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        for (int y = charY + VIEW_RADIUS; y >= charY - VIEW_RADIUS; y--){
            for (int x = charX - VIEW_RADIUS; x <= charX + VIEW_RADIUS; x++) {
                Tile tile = map.getTileByCordinate(x, y);
                if (tile != null && tile.getResource() != null) {
                    if (tile.getResource() instanceof Tree) {
                        ((Tree) tile.getResource()).draw();
                    } else if (tile.getResource() instanceof Stone) {
                        Stone stone = (Stone) tile.getResource();
                        int TILE_SIZE = AssetManager.getTileSize();
                        Main.getBatch().draw(
                            stone.getType().getTexture(),
                            x * TILE_SIZE + TILE_SIZE / 4,
                            y * TILE_SIZE + TILE_SIZE / 4,
                            TILE_SIZE / 2,
                            TILE_SIZE / 2
                        );
                    }
                }
            }
        }


        for (Shop shop : App.getCurrentGame().getShops()) {
            shop.draw();
        }
    }
}

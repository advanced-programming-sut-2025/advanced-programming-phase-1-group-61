package views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
import io.github.camera.Main;
import models.character.Character;
import models.map.Tile;
import models.enums.TileType;

public class MiniMap extends Actor {

    private final int tileSize = 4;
    private final Character character;
    private Texture mapTexture;

    public MiniMap(Character character) {
        this.character = character;
        rebuildMapTexture();
        setSize(mapTexture.getWidth(), mapTexture.getHeight());
        setPosition(10, 10);
    }

    public void rebuildMapTexture() {
        Tile[][] tiles = Main.getApp().getCurrentGame().getMap().getTiles();
        int width = tiles.length;
        int height = tiles[0].length;

        Pixmap pixmap = new Pixmap(width * tileSize, height * tileSize, Pixmap.Format.RGBA8888);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TileType type = tiles[x][y].getType();
                Color color = getColorForTileType(type);
                pixmap.setColor(color);
                pixmap.fillRectangle(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }

        int px = character.getX();
        int py = character.getY();
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(px * tileSize, py * tileSize, tileSize, tileSize);

        if (mapTexture != null) mapTexture.dispose();
        mapTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    private Color getColorForTileType(TileType type) {
        return switch (type) {
            case Grass -> Color.GREEN;
            case Water -> Color.BLUE;
            case CabinWall -> Color.BROWN;
            case CabinFloor -> Color.GRAY;
            case Shop -> Color.OLIVE;
            case GreenHouse, BrokenGreenHouse, BrokenGreenHouseWall -> Color.LIME;
            case Soil -> Color.LIGHT_GRAY;
            default -> Color.DARK_GRAY;
        };
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(mapTexture, getX(), getY());
    }

    public void update() {
        rebuildMapTexture();
    }

    public void dispose() {
        if (mapTexture != null) mapTexture.dispose();
    }
}

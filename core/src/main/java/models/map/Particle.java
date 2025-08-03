package models.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Particle {
    float x, y;
    float vy;
    float size;

    public Particle(float x, float y) {
        this.x = x;
        this.y = y;
        this.vy = 100 + (float)(Math.random() * 100);
        this.size = 2 + (float)(Math.random() * 2);
    }

    public void update(float delta) {
        y -= vy * delta;
        if (y < 0) {
            y = Gdx.graphics.getHeight();
            x = (float)(Math.random() * Gdx.graphics.getWidth());
        }
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, x, y, size, size * 5);
    }
}

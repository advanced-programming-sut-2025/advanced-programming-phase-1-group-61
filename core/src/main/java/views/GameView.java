package views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AlertGenerator;
import models.App;
import models.AssetManager;
import models.NPC.NPC;
import models.enums.WeatherState;
import models.map.Particle;


import java.util.ArrayList;
import java.util.List;

public class GameView implements Screen, InputProcessor{
    private GameMenuController controller;
    private Stage stage;
    private InventoryUI inventoryUI;
    private boolean inventoryVisible = false;
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private List<Particle> particles;
    private Texture rainTexture;
    private Texture snowTexture;
    private MiniMap miniMap;
    private boolean miniMapVisible = false;




    public GameView(GameMenuController controller ) {
        this.controller = controller;
        stage = new Stage();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller.setView(this,camera);

    }

    @Override
    public void show() {
        stage = new Stage();
        inventoryUI = new InventoryUI(AssetManager.getSkin(),
            App.getCurrentGame().getCurrentCharacter(),
            stage);
        inventoryUI.setVisible(false);
        stage.addActor(inventoryUI);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(inventoryUI);
        multiplexer.addProcessor(stage);

        Gdx.input.setInputProcessor(multiplexer);

        controller.setView(this, camera);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        rainTexture = new Texture(Gdx.files.internal("Particle/rain_drop.png"));
        snowTexture = new Texture(Gdx.files.internal("Particle/Snow_particle.png"));
        particles = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            float x = (float)(Math.random() * Gdx.graphics.getWidth());
            float y = (float)(Math.random() * Gdx.graphics.getHeight());
            particles.add(new Particle(x, y));
        }
        miniMap = new MiniMap(App.getCurrentGame().getCurrentCharacter());
        miniMap.setVisible(false);
        stage.addActor(miniMap);



    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        controller.updateGame();
        float delta = Gdx.graphics.getDeltaTime();
        WeatherState weatherState = App.getCurrentGame().getMap().getWeather().getState();





        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventoryVisible = !inventoryVisible;
            inventoryUI.setVisible(inventoryVisible);
            inventoryUI.setInventoryVisible(inventoryVisible);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            miniMapVisible = !miniMapVisible;
            miniMap.setVisible(miniMapVisible);
            if (miniMapVisible){
                miniMap.update();
            }
        }


        Main.getBatch().setProjectionMatrix(camera.combined);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Main.getBatch().end();
        if (weatherState == WeatherState.Rain) {
            spriteBatch.begin();
            for (Particle p : particles) {
                p.update(delta);
                p.draw(spriteBatch, rainTexture);
            }
            spriteBatch.end();
        } else if ( weatherState == WeatherState.Snow) {
            spriteBatch.begin();
            for (Particle p : particles) {
                p.update(delta);
                p.draw(spriteBatch,snowTexture);
            }
            spriteBatch.end();
        }
        models.date.Date date = App.getCurrentGame().getDate();
        int hour = date.getHour();

        if (hour >= 20 || hour < 10) {
            float alpha;

            if (hour >= 20) {
                alpha = (hour - 20) / 4f * 0.5f;
            } else {
                alpha = (1 - (hour / 10f)) * 0.5f;
            }

            alpha = Math.min(alpha, 0.5f);

            Gdx.gl.glEnable(GL20.GL_BLEND);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, alpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }


        String timeText = "Hour: " + date.getHour();
        String dayText = "Day: " + date.getDay();
        String seasonText = "Season: " + date.getSeason();
        String energy = "Energy: "+App.getCurrentGame().getCurrentCharacter().getEnergy();

        spriteBatch.begin();
        font.draw(spriteBatch, timeText, 20, Gdx.graphics.getHeight() - 20);
        font.draw(spriteBatch, dayText, 20, Gdx.graphics.getHeight() - 50);
        font.draw(spriteBatch, seasonText, 20, Gdx.graphics.getHeight() - 80);
        font.draw(spriteBatch , energy , 20 ,Gdx.graphics.getHeight() -110 );
        spriteBatch.end();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public boolean isMiniMapVisible() {
        return miniMapVisible;
    }

    public void setMiniMapVisible(boolean miniMapVisible) {
        this.miniMapVisible = miniMapVisible;
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        Vector3 worldClick=new Vector3(i,i1,0);
        camera.unproject(worldClick);
        for(NPC npc:App.getCurrentGame().getNpcList()){
            if(npc.getChatIconBounds().contains(worldClick.x,worldClick.y)){
                AlertGenerator.showAlert("",npc.getDialog(),stage);
                return true;
            }
            if(i3==Input.Buttons.RIGHT && npc.getBounds().contains(worldClick.x,worldClick.y)){
                Table table=new Table();
                table.setFillParent(true);
                table.center();
                TextButton gift=new TextButton("GIFT",AssetManager.getSkin());
                TextButton quests=new TextButton("QUESTS",AssetManager.getSkin());
                TextButton friendship=new TextButton("FRIENDSHIP",AssetManager.getSkin());
                TextButton close=new TextButton("CLOSE",AssetManager.getSkin());
                table.add(gift).width(300).height(60).row();
                table.add(quests).width(300).height(60).row();
                table.add(friendship).width(300).height(60).row();
                table.add(close).width(300).height(60).row();
                stage.addActor(table);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
    public Stage getStage() {
        return stage;
    }
}

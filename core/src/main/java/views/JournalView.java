package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.enums.ItemType;

public class JournalView implements Screen {

    private Stage stage;
    private Skin skin;
    private Label descriptionLabel;
    private TextButton leave;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = AssetManager.getSkin();

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

        Table itemTable = new Table();

        for (ItemType item : ItemType.values()) {
            Texture texture = item.getTexture();
            Image itemImage = new Image(texture);

            Label itemLabel = new Label(item.name(), skin);

            Table itemRow = new Table();
            itemRow.add(itemImage).size(32, 32).padRight(10);
            itemRow.add(itemLabel).left().expandX();

            itemRow.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    descriptionLabel.setText("Item: " + item.name() + "\nDescription: \n" +item.getDescription()+"\nWorth: "+item.getPrice());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    descriptionLabel.setText("");
                }
            });

            itemTable.row();
            itemTable.add(itemRow).expandX().fillX();
        }

        ScrollPane scrollPane = new ScrollPane(itemTable, skin);
        scrollPane.setFadeScrollBars(false);

        descriptionLabel = new Label("", skin);
        descriptionLabel.setWrap(true);

        leave = new TextButton("Exit", skin);
        leave.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(App.getCurrentGame())));
                System.out.println("Exit button clicked!");
                return true;
            }
        });

        rootTable.top().left().pad(20);
        rootTable.add(scrollPane).width(300).height(400).row();
        rootTable.add(descriptionLabel).width(300).padTop(10).row();
        rootTable.add(leave).padTop(20).center();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
    }
}

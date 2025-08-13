package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.CookingPageController;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.enums.CookingRecipes;

public class CookingPageView implements Screen, InputProcessor {
    private final CookingPageController controller = new CookingPageController(this);
    private final Stage stage;
    private final Table mainTable;
    private final Table detailsTable;
    private final Label requiredItems;
    private final Label recipeName;
    private final Label price;
    private final Label locked;
    private final ImageButton[] cookingButtons;
    private final TextButton back;
    private Table buttonTable;

    public CookingPageView() {
        stage = new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        mainTable = new Table();
        detailsTable = new Table();
        requiredItems = new Label("", AssetManager.getSkin());
        price = new Label("", AssetManager.getSkin());
        recipeName = new Label("", AssetManager.getSkin());
        locked = new Label("", AssetManager.getSkin());
        cookingButtons = new ImageButton[CookingRecipes.values().length];
        back = new TextButton("Leave", AssetManager.getSkin());
    }

    @Override
    public void show() {
        mainTable.setFillParent(true);
        mainTable.defaults().center().pad(10);

        detailsTable.setFillParent(true);
        detailsTable.defaults().top().left().pad(10);

        buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.bottom().padBottom(50);
        buttonTable.add(back);
        stage.addActor(buttonTable);

        for (int i = 0; i < CookingRecipes.values().length; i++) {
            Main.getApp().ExtractCooking(i, cookingButtons);
        }

        setUpUI();

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.C) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            return true;
        }
        return false;
    }

    @Override public boolean keyUp(int i) { return false; }
    @Override public boolean keyTyped(char c) { return false; }
    @Override public boolean touchDown(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchUp(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchCancelled(int i, int i1, int i2, int i3) { return false; }
    @Override public boolean touchDragged(int i, int i1, int i2) { return false; }
    @Override public boolean mouseMoved(int i, int i1) { return false; }
    @Override public boolean scrolled(float v, float v1) { return false; }

    public void setUpUI() {
        for (int i = 0; i < CookingRecipes.values().length; i++) {
            if (i % 5 == 0) mainTable.row();
            mainTable.add(cookingButtons[i]);
            controller.addHoverClickListener(cookingButtons[i], CookingRecipes.values()[i]);
        }

        detailsTable.add(recipeName).width(500).height(60).row();
        detailsTable.add(price).width(500).height(60).row();
        detailsTable.add(requiredItems).width(500).height(60).row();
        detailsTable.add(locked).width(500).height(60).row();

        stage.addActor(mainTable);
        stage.addActor(detailsTable);
    }

    public Label getRequiredItems() { return requiredItems; }
    public Label getRecipeName() { return recipeName; }
    public Label getLocked() { return locked; }
    public Label getPrice() { return price; }
    public Stage getStage() { return stage; }
}

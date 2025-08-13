package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.character.Character;
import models.date.Date;
import models.map.Tile;
import models.workBench.Inprocess;
import models.workBench.WorkBench;
import models.enums.ItemType;
import models.workBench.RecipeInfo;

import java.util.Map;

public class WorkBenchView implements Screen {

    private Stage stage;
    private Skin skin;
    private OrthographicCamera camera;

    public WorkBenchView() {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = AssetManager.getSkin();

        Table root = new Table();
        root.setFillParent(true);
        root.top().left();
        stage.addActor(root);

        Label title = new Label("WorkBench: " + getWorkBench().getType().name(), skin, "title");
        root.add(title).colspan(2).pad(10);
        root.row();

        Table recipesTable = new Table();
        recipesTable.defaults().pad(10);

        for (Map.Entry<ItemType, RecipeInfo> entry : getWorkBench().getType().getRecipes().entrySet()) {
            ItemType output = entry.getKey();
            RecipeInfo recipe = entry.getValue();

            Table recipeRow = new Table(skin);
            recipeRow.setBackground(skin.newDrawable("white", Color.DARK_GRAY));

            Image icon = new Image(output.getTexture());
            recipeRow.add(icon).size(48).padRight(10);

            Label nameLabel = new Label(output.name(), skin);
            Label descLabel = new Label(recipe.getDescription(), skin);
            descLabel.setWrap(true);

            Table textCol = new Table();
            textCol.add(nameLabel).left().row();
            textCol.add(descLabel).width(300).left();
            recipeRow.add(textCol).expandX().left();


            Table ingredientsTable = new Table();
            for (Map.Entry<ItemType, Integer> ing : recipe.getIngredients().entrySet()) {
                Image ingIcon = new Image(new TextureRegionDrawable(new TextureRegion(ing.getKey().getTexture())));
                Label qtyLabel = new Label("x" + ing.getValue(), skin);
                ingredientsTable.add(ingIcon).size(32);
                ingredientsTable.add(qtyLabel).padRight(10);
            }

            recipeRow.add(ingredientsTable).padRight(20);


            TextButton startBtn = new TextButton("Start", skin);
            startBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int hours = recipe.getProcessingTime();
                    Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
                    for (Map.Entry<ItemType, Integer> ingredient : recipe.getIngredients().entrySet()) {
                        System.out.println(ingredient.getKey() +" "+ ingredient.getValue());
                        if(character.getInventory().getCountOfItem(ingredient.getKey()) < ingredient.getValue()){
                            return;
                        }
                    }
                    for (Map.Entry<ItemType, Integer> ingredient : recipe.getIngredients().entrySet()) {
                        character.getInventory().removeItem(ingredient.getKey() , ingredient.getValue());
                    }
                   getWorkBench().addprocess(output, hours);
                }
            });
            recipeRow.add(startBtn).size(80, 40);

            recipesTable.add(recipeRow).growX().row();
        }

        ScrollPane scrollPane = new ScrollPane(recipesTable, skin);
        scrollPane.setFadeScrollBars(false);

        root.add(scrollPane).grow().colspan(2);
        root.row();


        TextButton collectBtn = new TextButton("Collect Finished Items", skin);
        collectBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean collected = getWorkBench().Collect();
                if (collected) {
                    System.out.println("Items collected!");
                } else {
                    System.out.println("Nothing ready yet!");
                }
            }
        });

        root.add(collectBtn).pad(10).left();


        TextButton backBtn = new TextButton("Back", skin);
        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });
        root.add(backBtn).pad(10).right();

        Table activeProcessesTable = new Table(skin);
        activeProcessesTable.defaults().pad(10);

        Date date = Main.getApp().getCurrentGame().getDate();
        for (Inprocess inprocess : getWorkBench().getInprocesses()) {
            Table processRow = new Table(skin);
            processRow.setBackground(skin.newDrawable("white", Color.GRAY));

            Image icon = new Image(inprocess.getType().getTexture());
            processRow.add(icon).size(48).padRight(10);

            Label nameLabel = new Label(inprocess.getType().name(), skin);
            processRow.add(nameLabel).width(200).left();

            ProgressBar progressBar = new ProgressBar(0f, 1f, 0.01f, false, skin);
            progressBar.setValue(inprocess.getProgressPercent(date.getDayCounter(), date.getHour()));
            processRow.add(progressBar).width(200).padRight(10);


            TextButton cancelBtn = new TextButton("Cancel", skin);
            cancelBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    getWorkBench().cancelProcess(inprocess);
                }
            });
            processRow.add(cancelBtn).size(80, 40);

            activeProcessesTable.add(processRow).growX().row();
        }

        ScrollPane activeScroll = new ScrollPane(activeProcessesTable, skin);
        root.add(new Label("Active Processes:", skin)).colspan(2).padTop(20);
        root.row();
        root.add(activeScroll).growX().colspan(2).pad(10);
        root.row();

    }


    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    private WorkBench getWorkBench(){
        Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
        Tile tile = Main.getApp().getCurrentGame().getMap().getTileByCordinate(character.getX() , character.getY());
        if(tile.getResource() != null){
            if(tile.getResource() instanceof WorkBench workBench){
                return workBench;
            }
        }
        return null;
    }
}

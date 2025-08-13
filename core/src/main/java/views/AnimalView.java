package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.animal.Animal;
import models.building.Building;
import models.character.Character;
import models.enums.AnimalType;
import models.enums.ItemType;
import models.map.Tile;

public class AnimalView implements Screen {

    private Stage stage;
    private Skin skin;
    private Table rootTable;

    public AnimalView() {
        stage = new Stage(new ScreenViewport());
        skin = AssetManager.getSkin();


        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.top().pad(20);
        stage.addActor(rootTable);

        updateAnimalList();
    }

    private void updateAnimalList() {
        rootTable.clear();

        Label title = new Label("Animal List", skin);
        title.setColor(Color.YELLOW);
        title.setFontScale(1.5f);
        rootTable.add(title).colspan(5).padBottom(20);
        rootTable.row();
        Character player = Main.getApp().getCurrentGame().getCurrentCharacter();
        Tile tile = Main.getApp().getCurrentGame().getMap().getTileByCordinate(player.getX() , player.getY());
        Building building = (Building) tile.getResource();
        for (Animal animal : building.getAnimalList()) {
            Image img = new Image(animal.getType().getTexture());
            Label nameLabel = new Label(animal.getName(), skin);

            StringBuilder productText = new StringBuilder("Products: ");
            if (animal.getProducts().isEmpty()) {
                productText.append("None");
            } else {
                productText.append("Product: "+animal.getProducts().get(0).getDisPlayName()+" Count: "+animal.getProducts().size());

            }
            Label productsLabel = new Label(productText.toString(), skin);

            TextButton sellButton = new TextButton("Sell", skin);
            sellButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Character character = Main.getApp().getCurrentGame().getCurrentCharacter();
                    character.setMoney(character.getMoney() + animal.getPrice());
                    building.getAnimalList().remove(animal);
                    updateAnimalList();
                }
            });

            TextButton productButton = new TextButton("Get Product", skin);
            productButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for (ItemType product : animal.getProduct()) {
                        Main.getApp().getCurrentGame().getCurrentCharacter().getInventory().addItem(product, 1);
                    }
                    updateAnimalList();
                }
            });

            TextButton releaseButton = new TextButton("Go out", skin);
            releaseButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Released " + animal.getName());
                }
            });

            TextButton feed = new TextButton("Feed", skin);
            feed.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    animal.feed();
                }
            });

            rootTable.add(img).size(64).pad(5);
            rootTable.add(nameLabel).pad(5);
            rootTable.add(productsLabel).pad(5);
            rootTable.add(sellButton).pad(5);
            rootTable.add(productButton).pad(5);
            rootTable.add(releaseButton).pad(5);
            rootTable.add(feed).pad(5);
            rootTable.row();
        }


        TextButton buyButton = new TextButton("Buy Animal", skin);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openBuyDialog();
            }
        });

        TextButton backButton = new TextButton("Back to Game", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });

        rootTable.row().padTop(30);
        rootTable.add(buyButton).colspan(2).padRight(20);
        rootTable.add(backButton).colspan(3).padLeft(20);
    }

    private void openBuyDialog() {
        Dialog dialog = new Dialog("Select Animal", skin);
        Table content = dialog.getContentTable();

        int counter = 0;
        for (AnimalType type : AnimalType.values()) {
            TextButton typeButton = new TextButton(type.getDisplayName() + " - " + type.getPrice() + "g", skin);
            typeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Character player = Main.getApp().getCurrentGame().getCurrentCharacter();
                    Tile tile = Main.getApp().getCurrentGame().getMap().getTileByCordinate(player.getX() , player.getY());
                    Building building = (Building) tile.getResource();
                    String newName = type.getDisplayName() + "_" + (building.getAnimalList().size() + 1);
                    Animal animal = new Animal(type, newName);
                    Main.getApp().getCurrentGame().getCurrentCharacter().setMoney(
                        Main.getApp().getCurrentGame().getCurrentCharacter().getMoney() - animal.getPrice()
                    );
                    building.addAnimal(animal);
                    dialog.hide();
                    updateAnimalList();
                }
            });

            content.add(typeButton).pad(5).width(500);

            counter++;
            if (counter % 2 == 0) {
                content.row();
            }
        }

        dialog.button("Cancel", false);
        dialog.show(stage);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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

    }
}

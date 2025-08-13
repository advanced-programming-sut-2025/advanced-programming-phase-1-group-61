package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AssetManager;
import models.character.Character;
import models.character.FriendShip;
import models.character.InventorySlot;
import models.enums.ItemType;
import network.GiftSent;

public class FriendShipView implements Screen {

    private Stage stage;
    private Table table;
    private Skin skin;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = AssetManager.getSkin();

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Character player = Main.getApp().getCurrentGame().getCurrentCharacter();

        Label titleLabel = new Label("Friends List", skin);
        table.add(titleLabel).colspan(4).pad(10);
        table.row();

        for (FriendShip friendShip : player.getFriendShipList()) {
            int friendId = friendShip.getUserID();
            int level = friendShip.getFriendShipXp();

            Label friendLabel = new Label("ID: " + friendId + " | Lvl: " + level, skin);
            TextButton giftButton = new TextButton("Gift", skin);
            TextButton giftsReceivedButton = new TextButton("Gift History", skin);

            giftButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Character player = Main.getApp().getCurrentGame().getCurrentCharacter();

                    Dialog giftDialog = new Dialog("Choose Gift", skin);
                    giftDialog.setModal(true);
                    giftDialog.setMovable(false);
                    giftDialog.setResizable(false);

                    for (int i = 0; i < player.getInventory().getSlots().size(); i++) {
                        InventorySlot slotData = player.getInventory().getSlots().get(i);

                        if (slotData.getObjectInSlot() instanceof ItemType) {
                            TextButton selectBtn = new TextButton(slotData.getObjectInSlot().toString(), skin);
                            selectBtn.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    ItemType selectedItem = (ItemType) slotData.getObjectInSlot();

                                    System.out.println("Chosen Gift: " + selectedItem + " -> Sent to Friend ID: " + friendId);

                                    Main.getApp().getCurrentGame().getCurrentCharacter()
                                        .getInventory().removeItem(selectedItem , 1);
                                    for (FriendShip friendShip : Main.getApp().getCurrentGame().getCurrentCharacter().getFriendShipList()) {
                                        if(friendShip.getUserID() == friendId){
                                            friendShip.setFriendShipXp(friendShip.getFriendShipXp() + 10);
                                        }
                                    }
                                    Main.getClient().sendMessage(
                                        new GiftSent(
                                        selectedItem
                                        , Main.getApp().getCurrentGame().getCurrentCharacter().getUserId()
                                        , Main.getApp().getCurrentGame().getId()
                                        ,friendId)
                                    );

                                    giftDialog.hide();
                                }
                            });
                            giftDialog.getContentTable().add(selectBtn).pad(5).row();
                        }
                    }

                    giftDialog.button("Close", false);
                    giftDialog.show(stage);
                }
            });


            giftsReceivedButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Dialog giftsDialog = new Dialog("Gifts Received", skin);
                    giftsDialog.setModal(true);
                    giftsDialog.setMovable(false);
                    giftsDialog.setResizable(false);

                    if (friendShip.getGiftsReceived().isEmpty()) {
                        giftsDialog.getContentTable().add(new Label("No gifts received yet.", skin)).pad(5);
                    } else {
                        for (ItemType gift : friendShip.getGiftsReceived()) {
                            giftsDialog.getContentTable().add(new Label(gift.toString(), skin)).pad(5).row();
                        }
                    }

                    giftsDialog.button("Close", false);
                    giftsDialog.show(stage);
                }
            });


            table.add(friendLabel).pad(5);
            table.add(giftButton).pad(5);
            table.add(giftsReceivedButton).pad(5);
            table.row();
        }

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameMenuController(Main.getApp().getCurrentGame())));
            }
        });

        table.row();
        table.add(backButton).colspan(3).padTop(20);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {

    }
}

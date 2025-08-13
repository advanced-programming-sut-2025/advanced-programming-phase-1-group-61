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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import controllers.GameMenuController;
import io.github.camera.Main;
import models.AlertGenerator;
import models.App;
import models.AssetManager;
import models.Game;
import models.NPC.NPC;
import models.character.Buff;
import models.enums.ItemType;
import models.enums.WeatherState;
import models.map.Map;
import models.map.Particle;
import network.Lobby.Chat;
import network.Lobby.Vote;
import network.Lobby.VoteType;
import network.Lobby.VotingRequest;
import network.MapUpdate;
import network.NetworkRequest;
import network.Requsets;


import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
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
    private ToolbarUI toolbarUI;
    private boolean voting = false;
    private Table votingTable;
    private SelectBox<String> votes;
    private Skin skin;
    private Label label;
    private String voteLabel;
    private TextButton sendVote;
    private List<String> chatMessages = new ArrayList<>();





    public GameView(GameMenuController controller ) {
        this.controller = controller;
        stage = new Stage();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller.setView(this,camera);
        skin = AssetManager.getSkin();
        voteLabel ="";
        sendVote = new TextButton("Send",skin);
    }


    @Override
    public void show() {
        votingTable = new Table();
        votingTable.setFillParent(true);
        votes = new SelectBox<>(skin);
        votes.setItems("Yes","No");
        votingTable.add(votes).row();
        votingTable.setVisible(voting);
        label = new Label(voteLabel,skin);
        votingTable.add(label).row();
        votingTable.add(sendVote);
        stage = new Stage();

        inventoryUI = new InventoryUI(AssetManager.getSkin(),
            Main.getApp().getCurrentGame().getCurrentCharacter(),
            stage);
        inventoryUI.setVisible(false);
        stage.addActor(inventoryUI);
        stage.addActor(votingTable);

        toolbarUI = new ToolbarUI(
            AssetManager.getSkin(),
            Main.getApp().getCurrentGame().getCurrentCharacter()
        );
        toolbarUI.setVisible(true);
        stage.addActor(toolbarUI);
        inventoryUI.setToolbarUI(toolbarUI);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(inventoryUI);
        multiplexer.addProcessor(this);


        Gdx.input.setInputProcessor(multiplexer);

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
        miniMap = new MiniMap(Main.getApp().getCurrentGame().getCurrentCharacter());
        miniMap.setVisible(false);
        stage.addActor(miniMap);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.postRunnable(() -> runEveryFiveTenths());
            }
        }, 0, 0.5f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                new Thread(() -> {
                    Main.getClient().sendMessage(
                        new MapUpdate(Main.getApp().getCurrentGame().getMap(), Main.getApp().getCurrentGame().getId())
                    );
                }).start();
            }
        }, 0, 1.0f);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                new Thread(() -> {
                    Main.getClient().sendMessage(
                        new Requsets(NetworkRequest.MapUpdateRequest, Main.getApp().getCurrentGame().getId(), 0)
                    );
                }).start();
            }
        }, 0, 2.0f);
        sendVote.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                voting = false;
                Main.getClient().sendMessage(new Vote(Main.getApp().getCurrentGame().getId(),votes.getSelected()));
                votingTable.setVisible(voting);
            }
        });
    }


    private void runEveryFiveTenths() {
       controller.updateServerGame();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        controller.updateGame();
        float delta = Gdx.graphics.getDeltaTime();
        WeatherState weatherState = controller.getGame().getMap().getWeather().getState();





        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            inventoryVisible = !inventoryVisible;
            inventoryUI.setVisible(inventoryVisible);
            inventoryUI.refreshUI();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            miniMapVisible = !miniMapVisible;
            miniMap.setVisible(miniMapVisible);
            if (miniMapVisible){
                miniMap.update();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            openVotingCreationPanel();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            openChatDialog();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.B)){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new CraftingPageView());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingsMenu());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C)){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new CookingPageView());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F)){
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new FriendShipView());
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
        models.date.Date date = controller.getGame().getDate();
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
        String energy = "Energy: "+Main.getApp().getCurrentGame().getCurrentCharacter().getEnergy();
        Buff buff = Main.getApp().getCurrentGame().getCurrentCharacter().getBuff();
        String buffString = "";
        if(buff != null){
           buffString  = "Buff: " + buff.getEnergyIncrease();
        }


        spriteBatch.begin();
        font.draw(spriteBatch, timeText, 20, Gdx.graphics.getHeight() - 20);
        font.draw(spriteBatch, dayText, 20, Gdx.graphics.getHeight() - 50);
        font.draw(spriteBatch, seasonText, 20, Gdx.graphics.getHeight() - 80);
        font.draw(spriteBatch , energy , 20 ,Gdx.graphics.getHeight() -110 );
        font.draw(spriteBatch , buffString, 20 ,Gdx.graphics.getHeight() -140 );
        int yOffset = 150;
        for (String msg : chatMessages) {
            if (msg.startsWith("[PRIVATE]")) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(spriteBatch, msg, 20, yOffset);
            yOffset += 20;
        }
        font.setColor(Color.WHITE);
        spriteBatch.end();

    }
    private void openVotingCreationPanel() {

        Dialog dialog = new Dialog("Start Voting", skin);


        SelectBox<String> voteTypeSelect = new SelectBox<>(skin);
        voteTypeSelect.setItems("ForceTerminate", "KickPlayer");


        SelectBox<String> playerSelect = new SelectBox<>(skin);
        List<String> playerNames = new ArrayList<>();
        Main.getApp().getCurrentGame().getAllCharacters().forEach(p -> {
            playerNames.add(" (ID:" + p.getUserId() + ")");
        });
        playerSelect.setItems(playerNames.toArray(new String[0]));
        playerSelect.setVisible(false);

        voteTypeSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean isKick = voteTypeSelect.getSelected().equals("KickPlayer");
                playerSelect.setVisible(isKick);
            }
        });


        TextButton sendButton = new TextButton("Send Vote", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VoteType type = VoteType.valueOf(voteTypeSelect.getSelected());
                int userId = 0;

                if (type == VoteType.KickPlayer) {
                    String selectedPlayer = playerSelect.getSelected();
                    userId = extractIdFromName(selectedPlayer);
                }

                Main.getClient().sendMessage(
                    new VotingRequest(Main.getApp().getCurrentGame().getId(), type, userId)
                );

                dialog.hide();
            }
        });


        dialog.getContentTable().add(new Label("Vote Type:", skin)).row();
        dialog.getContentTable().add(voteTypeSelect).row();
        dialog.getContentTable().add(new Label("Select Player:", skin)).row();
        dialog.getContentTable().add(playerSelect).row();

        dialog.getButtonTable().add(sendButton);

        dialog.show(stage);
    }


    private int extractIdFromName(String text) {
        try {
            int start = text.indexOf("ID:") + 3;
            int end = text.indexOf(")", start);
            return Integer.parseInt(text.substring(start, end));
        } catch (Exception e) {
            return 0;
        }
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
        for(NPC npc:Main.getApp().getCurrentGame().getNpcList()){
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
                controller.addListenersForNpcTable(npc,gift,quests,friendship,close,table);
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

    public void setVoting(boolean voting , String votingLabel) {
        this.voting = voting;
        votingTable.setVisible(voting);
        label.setText(votingLabel);
    }
    private void openChatDialog() {
        Dialog dialog = new Dialog("Send Chat", skin);

        final TextField messageField = new TextField("", skin);
        final CheckBox privateCheck = new CheckBox("Private", skin);

        final SelectBox<String> playerSelect = new SelectBox<>(skin);
        List<String> playerNames = new ArrayList<>();
        Main.getApp().getCurrentGame().getAllCharacters().forEach(p -> {
            playerNames.add(p.getUserId() + " - ");
        });
        playerSelect.setItems(playerNames.toArray(new String[0]));
        playerSelect.setVisible(false);

        privateCheck.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerSelect.setVisible(privateCheck.isChecked());
            }
        });

        TextButton sendButton = new TextButton("Send", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String text = messageField.getText();
                boolean isPrivate = privateCheck.isChecked();
                Chat chat = new Chat(text, isPrivate);

                if (isPrivate) {
                    String selectedPlayer = playerSelect.getSelected();
                    int userId = Integer.parseInt(selectedPlayer.split(" - ")[0]);
                    chat.addUserId(userId);
                } else {
                    Main.getApp().getCurrentGame().getAllCharacters().forEach(p -> {
                        chat.addUserId(p.getUserId());
                    });
                }

                Main.getClient().sendMessage(chat);
                dialog.hide();
            }
        });

        dialog.getContentTable().add(new Label("Message:", skin)).row();
        dialog.getContentTable().add(messageField).width(300).row();
        dialog.getContentTable().add(privateCheck).row();
        dialog.getContentTable().add(playerSelect).width(300).row();
        dialog.getButtonTable().add(sendButton);

        dialog.show(stage);
    }
    public void addChatMessage(String message, boolean isPrivate) {
        if (isPrivate) {
            message = "[PRIVATE] " + message;
        }
        chatMessages.add(message);
        if (chatMessages.size() > 10) {
            chatMessages.remove(0);
        }
    }
    public void showGiftNotification(String text , ItemType gift) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = AssetManager.getFont();
        if(gift.equals(ItemType.BlueJazz)){
            style.fontColor = Color.BLUE;
        }else {
            style.fontColor = com.badlogic.gdx.graphics.Color.YELLOW;
        }

        final Label giftLabel = new Label(text, style);
        giftLabel.setPosition(
            (stage.getWidth() - giftLabel.getWidth()) / 2,
            stage.getHeight() - 100
        );

        stage.addActor(giftLabel);


        giftLabel.addAction(
            sequence(
                delay(5f),
                fadeOut(1f),
                removeActor()
            )
        );
    }



}

package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.SettingsMenuController;
import io.github.camera.Main;
import models.App;
import models.AssetManager;
import models.Game;
import models.character.Character;
import models.enums.graphic.BackgroundMusic;

public class SettingsMenu implements Screen {
    private final SettingsMenuController controller=new SettingsMenuController(this);
    private final Stage stage;
    private Slider musicSlider;
    private Slider sfxSlider;
    private final Label musicVolumeLabel=new Label("", AssetManager.getSkin());
    private final Label sfxVolumeLabel=new Label("",AssetManager.getSkin());
    private TextButton back;
    private SelectBox<String> musicPicker;
    private Texture background;
    public SettingsMenu() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        setUpUI();
        controller.handleMusicSlider();
        controller.setupListeners();
        controller.handleSelectBox();
    }

    @Override
    public void show() {
        background=AssetManager.getMainMenuBackground();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        musicVolumeLabel.setText("MUSIC: "+ (int) (Main.getApp().getMusicVolume() * 100));
        sfxVolumeLabel.setText("SFX: "+ (int) (AssetManager.getUiClicks().getVolume() * 100));
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 1920, 1080);
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
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

    private void setUpUI(){
        back=new TextButton("BACK", AssetManager.getSkin());
        musicPicker=new SelectBox<>(AssetManager.getSkin());
        musicPicker.setItems(BackgroundMusic.getItems());
        Game game=Main.getApp().getCurrentGame();
        if(game!=null) musicPicker.setSelected(game.getCurrentCharacter().getBackgroundMusic().getDisplayName());
        else musicPicker.setSelected(BackgroundMusic.KHARMALE.getDisplayName());
        Table table=new Table();
        table.setFillParent(true);
        table.center().pad(10);
        sfxSlider=new Slider(0f,1f,0.01f,false,AssetManager.getSkin());
        sfxSlider.setValue(AssetManager.getUiClicks().getVolume());
        musicSlider=new Slider(0f,1f,0.01f,false,AssetManager.getSkin());
        musicSlider.setValue(Main.getApp().getMusicVolume());
        table.row();
        table.add(musicVolumeLabel).width(150);
        table.add(musicSlider).width(300);
        table.row();
        table.add(sfxVolumeLabel).width(150);
        table.add(sfxSlider).width(300);
        table.row();
        table.add(musicPicker).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(back).width(300).height(60).colspan(2).padTop(10);
        stage.addActor(table);
    }

    public Stage getStage() {
        return stage;
    }

    public Slider getMusicSlider() {
        return musicSlider;
    }

    public Slider getSfxSlider() {
        return sfxSlider;
    }
    public Label getMusicVolumeLabel() {
        return musicVolumeLabel;
    }
    public Label getSfxVolumeLabel() {
        return sfxVolumeLabel;
    }
    public TextButton getBack() {
        return back;
    }
}

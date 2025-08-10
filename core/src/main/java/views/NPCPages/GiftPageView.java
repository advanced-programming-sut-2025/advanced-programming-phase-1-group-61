package views.NPCPages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import controllers.GameMenuController;
import controllers.NPCPagesControllers.GiftPageController;
import models.AssetManager;
import models.NPC.NPC;

public class GiftPageView implements Screen {
    private final GiftPageController controller=new GiftPageController(this);
    private final Stage stage;
    private final TextField itemName;
    private final TextButton submit;
    private final TextButton back;
    private final NPC npc;
    private final GameMenuController gameMenuController;
    public GiftPageView(NPC npc,GameMenuController gameMenuController) {
        this.npc=npc;
        this.gameMenuController=gameMenuController;
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        itemName=new TextField("",AssetManager.getSkin());
        itemName.setMessageText("Please enter the item you want to gift");
        submit=new TextButton("SUBMIT",AssetManager.getSkin());
        back=new TextButton("BACK",AssetManager.getSkin());
        controller.addListeners();
    }
    @Override
    public void show() {
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.add(itemName).width(500).height(80).row();
        table.add(submit).width(300).height(60);
        table.add(back).width(300).height(60);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().end();
        stage.act(v);
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
    public Stage getStage() {
        return stage;
    }
    public TextField getItemName() {
        return itemName;
    }
    public TextButton getSubmit() {
        return submit;
    }
    public TextButton getBack() {
        return back;
    }
    public NPC getNpc() {
        return npc;
    }
    public GameMenuController getGameMenuController() {
        return gameMenuController;
    }
}
